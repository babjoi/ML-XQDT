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

import java.io.InputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Result;
import javax.xml.transform.Templates;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.sax.SAXResult;
import javax.xml.transform.sax.SAXTransformerFactory;
import javax.xml.transform.sax.TransformerHandler;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import net.sf.saxon.TransformerFactoryImpl;

import org.antlr.runtime.ANTLRStringStream;
import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.Token;
import org.antlr.runtime.debug.ParseTreeBuilder;
import org.antlr.runtime.tree.ParseTree;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.ls.DOMImplementationLS;
import org.w3c.dom.ls.LSOutput;
import org.w3c.dom.ls.LSSerializer;

public class Formatter {

  public enum Mode {
    PARSE_TREE, HTML, TEXT, PRE_INDENT
  }

  private static Templates templates;

  static {
    templates = loadTemplate();
  }

  private static Templates loadTemplate() {
    InputStream resource = Formatter.class.getClassLoader().getResourceAsStream(
        "com/martinprobst/xqpretty/xqpretty.xsl");
    Templates templates = null;
    try {
      templates = TransformerFactoryImpl.newInstance().newTemplates(new StreamSource(resource));
    } catch (TransformerConfigurationException e) {
      e.printStackTrace();
    } catch (TransformerFactoryConfigurationError e) {
      e.printStackTrace();
    }
    return templates;
  }

  public static String format(String query, Mode mode) throws RecognitionException {
    ParseTree tree = parse(query);
    Document document = convertToDocument(tree);
    StringWriter writer = new StringWriter();
    switch (mode) {
    case HTML:
      transformAndIndent(document, writer, true, false);
      break;
    case TEXT:
      transformAndIndent(document, writer, true, true);
      break;
    case PRE_INDENT:
      transformAndIndent(document, writer, false, false);
      break;
    case PARSE_TREE:
      serialize(document, writer);
      break;
    }
    return writer.toString();
  }

  private static void serialize(Document document, Writer writer) {
    DOMImplementationLS impl = (DOMImplementationLS) document.getImplementation();
    LSOutput output = impl.createLSOutput();
    LSSerializer serializer = impl.createLSSerializer();
    output.setCharacterStream(writer);

    serializer.write(document, output);
  }

  private static void transformAndIndent(Document document, Writer writer, boolean filterIndents,
      boolean asText) {
    try {
      Result result;
      if (filterIndents) {
        IndentationFilter indentationFilter = new IndentationFilter(asText);
        SAXResult saxResult = new SAXResult(indentationFilter);
        SAXTransformerFactory tFactory = (SAXTransformerFactory) SAXTransformerFactory
            .newInstance();
        TransformerHandler tHandler = tFactory.newTransformerHandler();
        tHandler.getTransformer().setOutputProperty("omit-xml-declaration", "yes");
        saxResult.setHandler(indentationFilter);
        indentationFilter.setContentHandler(tHandler);
        StreamResult actualResult = new StreamResult(writer);
        tHandler.setResult(actualResult);
        result = saxResult;
      } else {
        result = new StreamResult(writer);
      }
      templates.newTransformer().transform(new DOMSource(document), result);
    } catch (TransformerConfigurationException e) {
      throw new RuntimeException("Unable to obtain transformer", e);
    } catch (TransformerException e) {
      throw new RuntimeException("Exception transforming parse tree", e);
    }
  }

  public static void main(String[] args) {
    String test = "declare function foo() { 1 } ; 'helo'";
    try {
      // parse
      ParseTree tree = parse(test);

      // build
      Document document = convertToDocument(tree);

      // transform/dump
      DOMImplementationLS impl = (DOMImplementationLS) document.getImplementation();
      LSOutput output = impl.createLSOutput();
      LSSerializer serializer = impl.createLSSerializer();
      output.setByteStream(System.out);

      Mode mode = Mode.HTML;
      switch (mode) {
      case HTML:
        transformAndIndent(document, new PrintWriter(System.out), true, false);
        break;
      case TEXT:
        transformAndIndent(document, new PrintWriter(System.out), true, true);
        break;
      case PARSE_TREE:
        serializer.write(document, output);
        break;
      }
    } catch (RecognitionException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (TransformerFactoryConfigurationError e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  private static Document convertToDocument(ParseTree tree) {
    DocumentBuilder docBuilder;
    try {
      docBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
      Document document = docBuilder.newDocument();
      dumpTreeAsXML(document, tree);
      return document;
    } catch (ParserConfigurationException e) {
      throw new RuntimeException("Could not obtain document builder", e);
    }
  }

  private static ParseTree parse(String test) throws RecognitionException {
    ANTLRStringStream source = new ANTLRStringStream(test);
    XQueryLexer lexer = new XQueryLexer(source);
    LazyTokenStream tokenStream = new LazyTokenStream(lexer);
    ParseTreeBuilder builder = new DecisionAwareParseTreeBuilder("XQuery");
    XQueryParser parser = new XQueryParser(tokenStream, builder);
    parser.setCharSource(source);
    parser.module();
    ParseTree tree = builder.getTree();
    return tree;
  }

  private static void dumpTreeAsXML(Node node, ParseTree root) {
    for (Object tree: root.getChildren()) {
      ParseTree parseTree = (ParseTree) tree;
      if (parseTree.getChildCount() == 0) {
        // leaf node
        if (parseTree.payload instanceof XQToken) {
          XQToken token = (XQToken) parseTree.payload;
          if (token.getText() != null) {
            addHiddenTokens(node, token);
            Element element = elem(node, "token");
            String tokenName = XQueryParser.tokenNames[token.getType()];
            if (tokenName.startsWith("'") && tokenName.endsWith("'")) {
              tokenName = tokenName.substring(1, tokenName.length() - 1);
            }
            element.setAttribute("type", tokenName);
            element.setTextContent(token.getText());
          }
        }
      } else {
        Element element = elem(node, parseTree.payload.toString());
        dumpTreeAsXML(element, parseTree);
      }
    }
  }

  private static void addHiddenTokens(Node node, XQToken token) {
    if (token.getHiddenPredecessor() != null) {
      XQToken last = token.getHiddenPredecessor();
      addHiddenTokens(node, last);
      if (last.getChannel() == Token.HIDDEN_CHANNEL) {
        if (last.getType() == XQueryParser.XQ_COMMENT) {
          Element element = elem(node, "comment");
          element.setTextContent(last.getText());
        } else {
          Element element = elem(node, "ws");
          element.setTextContent(last.getText());
        }
      }
    }
  }

  private static Element elem(Node node, String name) {
    Document doc = (Document) (node.getOwnerDocument() != null ? node.getOwnerDocument() : node);
    Element element = doc.createElement(name);
    node.appendChild(element);
    return element;
  }

}
