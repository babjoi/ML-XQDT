/*
 * Copyright 2008 Martin Probst
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.martinprobst.xqpretty;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.ext.Attributes2Impl;
import org.xml.sax.helpers.XMLFilterImpl;

public class IndentationFilter extends XMLFilterImpl {

  private static final boolean DEBUG = false;
  
  private static final char[] ONE_LINE_BREAK = new char[] { '\n' };
  private static final char ONE_WS = ' ';
  private static final int MAX_CONSECUTIVE_BREAKS = 2;
  private final boolean filterTags;
  private int consecutiveBreaks = 0;
  private boolean lastBreakWasOptional;

  private ArrayList<Event> buffer = new ArrayList<Event>(5);
  private ArrayList<Integer> tabMarks = new ArrayList<Integer>(2);
  private static final BreakMarker BREAK_MARKER = new BreakMarker();
  private static final int MINOVERFLOW = 20;

  public IndentationFilter(boolean filterTags) {
    this.filterTags = filterTags;
  }

  @Override
  public void characters(char[] ch, int start, int length) throws SAXException {
    String text = new String(ch, start, length);
    if (text.startsWith("(:") && text.endsWith(":)")) {
      comment(text);
      return;
    }
    String[] lines = text.split("\n");
    if (lines.length == 0) {
      return;
    }
    // write the first n lines
    for (int i = 0; i < lines.length - 1; i++) {
      indent();
      write(lines[i]);
      atStart = false;
      newline(false);
    }
    // write the last line
    indent();
    write(lines[lines.length - 1]);
    if (ch[start + length - 1] == '\n') {
      newline(false);
    } else {
      atStart = false;
    }
  }

  /**
   * talk about ugly hacks. I see no proper way of formatting comments here,
   * except maybe for a dedicated comment lexer - which is a bit much.
   * 
   * @param text
   * @throws SAXException
   */
  private void comment(String text) throws SAXException {
    String[] lines = text.split("\n");
    for (int i = 0; i < lines.length; i++) {
      String line = lines[i];
      if (line.startsWith("(:")) continue;
      line = trimLeft(line);
      if (!line.startsWith(":")) {
        lines[i] = " : " + line;
      } else {
        lines[i] = " " + line;
      }
    }
    for (String line: lines) {
      indent();
      write(line, false);
      atStart = false;
      newline(false);
    }
  }

  public void superCharacters(String str) throws SAXException {
    if (DEBUG) System.out.print(str);
    actualPosInLine += str.length();
    super.characters(str.toCharArray(), 0, str.length());
  }

  private void write(String text) throws SAXException {
    write(text, true);
  }

  private void write(String text, boolean trim) throws SAXException {
    if (atStart && trim) {
      // cut off leading whitespace
      text = trimLeft(text);
    }
    consecutiveBreaks = 0;
    buffer.add(new StringEvent(text));
    posInLine += text.length();
    checkBreakLine();
  }

  private String trimLeft(String text) {
    int startOfText = 0;
    while (text.length() > startOfText && text.charAt(startOfText) == ' ') {
      startOfText++;
    }
    text = text.substring(startOfText);
    return text;
  }

  private void checkBreakLine() throws SAXException {
    if (posInLine > MAXLINELENGTH) {
      int resultLength = posInLine;
      int breakPosition = buffer.size() - 1;
      // find a previous break marker that will actually bring us under
      // MAXLINELENGTH.
      while (breakPosition >= 0) {
        Event event = buffer.get(breakPosition);
        resultLength -= event.length();
        if (event == BREAK_MARKER && resultLength <= MAXLINELENGTH) {
          buffer.remove(breakPosition);
          break;
        }
        breakPosition--;
      }
      if (breakPosition < 0) {
        // no suitable break marker found, simply find a spot where we can
        // break.
        resultLength = posInLine;
        breakPosition = buffer.size() - 1;
        while (breakPosition > 0) {
          Event event = buffer.get(breakPosition);
          resultLength -= event.length();
          if (resultLength <= MAXLINELENGTH) {
            break;
          }
          breakPosition--;
        }
      }
      int newPosInLine = posInLine - resultLength;
      // make sure we're really writing something
      int actualBreak = 0;
      int evLength = 0;
      for (; actualBreak < buffer.size(); actualBreak++) {
        Event event = buffer.get(actualBreak);
        if (event instanceof StringEvent) {
          evLength = ((StringEvent) event).str.trim().length();
          if (evLength > 0) break;
        }
      }
      actualBreak++;
      if (actualBreak > breakPosition) {
        newPosInLine -= evLength;
        breakPosition = actualBreak;
      }
      // copy the stuff that should go on the next line into overflow
      List<Event> nextLinePart = buffer.subList(breakPosition, buffer.size());
      ArrayList<Event> overflow = new ArrayList<Event>(nextLinePart);
      // clear the next line part from our buffer
      nextLinePart.clear();

      // break the line, adding all stuff left in buffer
      newline(false);
      // indent the next line as an overflow line
      overflowLine = true;
      indent();

      posInLine += newPosInLine;

      // a bit hackish, but we have to remove the leading whitespace
      // from the next line part
      for (int i = 0; i < overflow.size(); i++) {
        Event ev = overflow.get(i);
        if (ev instanceof StringEvent) {
          StringEvent sev = (StringEvent) ev;
          if (sev.str.trim().length() == 0) {
            posInLine -= sev.str.length();
            overflow.remove(i);
          }
          break;
        }
      }
      // re-add the remainder into the buffer
      buffer.addAll(overflow);
    }
  }

  private void newline(boolean forced) throws SAXException {
    if (atStart && lastBreakWasOptional) {
      lastBreakWasOptional = !forced;
      return;
    }
    lastBreakWasOptional = !forced;
    if (atStart) {
      if (consecutiveBreaks >= MAX_CONSECUTIVE_BREAKS) {
        return;
      }
      if (!forced) return;
    }
    atStart = true;
    overflowLine = false;
    posInLine = 0;
    for (Event event: buffer) {
      event.play();
    }
    buffer.clear();
    if (DEBUG) System.out.println();
    // no duplicate line breaks please
    consecutiveBreaks++;
    actualPosInLine = 0;
    super.characters(ONE_LINE_BREAK, 0, 1);
  }

  private void indent() throws SAXException {
    if (atStart) {
      IndentOp indentOp = new IndentOp();
      buffer.add(indentOp);
      posInLine += indentOp.length();
    }
  }

  private static final int HTAB = 2;
  private static final int MAXLINELENGTH = 80;
  private int indent = 0;
  private int posInLine = 0;
  private boolean atStart = true;
  private boolean overflowLine = false;
  public int actualPosInLine;

  @Override
  public void startElement(String uri, String localName, String name, Attributes atts)
      throws SAXException {
    if ("indent".equals(name)) {
      buffer.add(new IndentEvent());
    } else if ("br".equals(name)) {
      // no-op
      String brClass = atts.getValue("class");
      if (brClass != null) {
        if ("user".equals(brClass)) {
          newline(true);
        } else if ("marker".equals(brClass)) {
          buffer.add(BREAK_MARKER);
        } else {
          newline(false);
        }
      } else {
        newline(false);
      }
    } else if ("tabmarker".equals(name)) {
      buffer.add(new TabMarker());
    } else if ("tabunmarker".equals(name)) {
      buffer.add(new TabUnmarker());
    } else if (!filterTags) {
      buffer.add(new ElementStartEvent(uri, localName, name, atts));
    }

  }

  public void superStartElement(String uri, String localName, String name, Attributes atts)
      throws SAXException {
    super.startElement(uri, name, name, atts);
  }

  @Override
  public void endElement(String uri, String localName, String name) throws SAXException {
    // System.out.println(">> End element " + name);
    if ("indent".equals(name)) {
      buffer.add(new UnIndentEvent());
    } else if ("br".equals(name)) {
      // no op
    } else if ("tabmarker".equals(name)) {
      // no-op
    } else if ("tabunmarker".equals(name)) {
      // no-op
    } else if (!filterTags) {
      buffer.add(new ElementEndEvent(uri, localName, name));
    }
  }

  public void superEndElement(String uri, String localName, String name) throws SAXException {
    super.endElement(uri, name, name);
  }

  @Override
  public void endDocument() throws SAXException {
    for (Event ev: buffer) {
      ev.play();
    }
    super.endDocument();
  }

  private static abstract class Event {
    public abstract void play() throws SAXException;

    public int length() {
      return 0;
    }
  };

  private static class BreakMarker extends Event {
    @Override
    public void play() throws SAXException {
      // no-op
    }

    @Override
    public String toString() {
      return "$BR$";
    }
  }

  private class IndentOp extends Event {

    private final boolean wasOverflow = overflowLine;

    private String getIndentString() {
      if (atStart) {
        StringBuilder res = new StringBuilder();
        int indents = indent;
        if (wasOverflow) {
          if (tabMarks.size() > 0) {
            // find a tabmark that leaves MINOVERFLOW space in line
            int tabmark = 0;
            int index;
            for (index = tabMarks.size() - 1; index >= 0; index--) {
              tabmark = tabMarks.get(index);
              if (posInLine + tabmark < MAXLINELENGTH - MINOVERFLOW) {
                break;
              }
            }
            if (index >= 0) {
              // we found a fitting tabmark
              for (int k = 0; k < tabmark; k++) {
                res.append(ONE_WS);
              }
              // posInLine += tabmark;
              return res.toString();
            }
          }
          // we don't have a tabmark, fall through by indenting 2 * HTAB
          indents += 2 * HTAB;
        }
        for (int k = 0; k < indents; k++) {
          res.append(ONE_WS);
        }
        return res.toString();
      } else {
        return "";
      }
    }

    @Override
    public void play() throws SAXException {
      superCharacters(getIndentString());
    }

    @Override
    public int length() {
      return getIndentString().length();
    }

  }

  private class IndentEvent extends Event {
    @Override
    public void play() throws SAXException {
      indent += HTAB;
    }
  }

  private class UnIndentEvent extends Event {
    @Override
    public void play() throws SAXException {
      indent -= HTAB;
    }
  }

  private class TabMarker extends Event {
    private final int tabPos = posInLine;

    @Override
    public void play() {
      tabMarks.add(tabPos);
    }

    @Override
    public String toString() {
      return "$->$";
    }
  }

  private class TabUnmarker extends Event {
    @Override
    public void play() throws SAXException {
      tabMarks.remove(tabMarks.size() - 1);
    }

    @Override
    public String toString() {
      return "$<-$";
    }
  }

  private class StringEvent extends Event {
    private final String str;

    public StringEvent(String str) {
      this.str = str;
    }

    @Override
    public void play() throws SAXException {
      IndentationFilter.this.superCharacters(str);
    }

    @Override
    public String toString() {
      return str;
    }

    @Override
    public int length() {
      return str.length();
    }
  }

  private class ElementStartEvent extends Event {
    private final String uri;
    private final String localName;
    private final String name;
    private final Attributes atts;

    private ElementStartEvent(String uri, String localName, String name, Attributes atts) {
      this.uri = uri;
      this.localName = localName;
      this.name = name;
      // make a copy of the attributes so that they don't get overwritten
      this.atts = new Attributes2Impl(atts);
    }

    @Override
    public void play() throws SAXException {
      IndentationFilter.this.superStartElement(uri, localName, name, atts);
    }
  }

  private class ElementEndEvent extends Event {
    private final String uri;
    private final String localName;
    private final String name;

    private ElementEndEvent(String uri, String localName, String name) {
      this.uri = uri;
      this.localName = localName;
      this.name = name;
    }

    @Override
    public void play() throws SAXException {
      IndentationFilter.this.superEndElement(uri, localName, name);
    }
  }
}
