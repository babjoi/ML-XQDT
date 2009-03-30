<?xml version="1.0" encoding="UTF-8"?>
<!--
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
-->
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="2.0"
  xmlns:xqp="http://www.martin-probst.com/xqpretty">

  <xsl:output method="xml" indent="no" omit-xml-declaration="yes"/>

  <xsl:variable name="no-ws-after-r-tokens" select="'RPAREN', 'RBRACKET', 'SLASH', 'SLASH_SLASH',
    'COMMA', 'SEMICOLON'"/>
  <xsl:variable name="no-ws-before" select="'RPAREN', 'RBRACKET', 'LBRACKET', 'SLASH',
    'SLASH_SLASH', 'SEMICOLON', '?', '*', '+', 'COMMA', 'CLOSE_ANGLE', '::'"/>
  <xsl:variable name="no-ws-after" select="'LPAREN', 'LBRACKET', 'SLASH', 'SLASH_SLASH',
    'OPEN_ANGLE', 'CLOSE_ANGLE', '@', '::' "/>

  <xsl:template match="/">
    <pre>
      <xsl:apply-templates/>
      <!-- add an optional break at the end, so we always end with a 
        line break and we always flush all buffers -->
      <br/>
    </pre>
  </xsl:template>

  <xsl:template match="prolog">
    <xsl:apply-templates/>
    <!-- use a user br, force an additional line break -->
    <br class="user"/>
    <br class="user"/>
  </xsl:template>

  <xsl:template match="fLWORExpr">
    <xsl:for-each select="* except ws">
      <xsl:apply-templates select="."/>
      <br/>
    </xsl:for-each>
  </xsl:template>

  <xsl:template match="forClauseExt | letClauseExt | quantifiedExprExt">
    <xsl:variable name="comma" select="token[. eq ',']"/>
    <xsl:apply-templates select="*[. &lt;&lt; $comma]"/>
    <xsl:apply-templates select="$comma"/>
    <br/>
    <indent>
      <indent>
        <xsl:apply-templates select="* except $comma"/>
      </indent>
    </indent>
  </xsl:template>

  <xsl:template match="typeswitchExpr">
    <xsl:apply-templates select="* except (caseClause | defaultClause)"/>
    <br/>
    <xsl:apply-templates select="caseClause | defaultClause"/>
  </xsl:template>

  <xsl:template match="caseClause">
    <br/>
    <xsl:apply-templates/>
  </xsl:template>

  <xsl:template match="defaultClause">
    <br/>
    <xsl:apply-templates/>
  </xsl:template>

  <xsl:template match="returnClause">
    <xsl:apply-templates select="token | comment"/>
    <br/>
    <xsl:apply-templates select="*[../token &lt;&lt; .]"/>
  </xsl:template>

  <xsl:template match="thenClause">
    <br/>
    <xsl:apply-templates/>
  </xsl:template>

  <xsl:template match="elseClause">
    <br/>
    <xsl:apply-templates/>
  </xsl:template>

  <xsl:template match="separator">
    <xsl:apply-templates select="token"/>
    <br/>
  </xsl:template>

  <!-- we strip space around slashes, but need them before or after absolute paths  -->
  <xsl:template match="pathExpr[token/@type = 'SLASH' or token/@type = 'SLASH_SLASH']">
    <xsl:if test="token[@type = ('SLASH', 'SLASH_SLASH')]/preceding::token">
      <xsl:text> </xsl:text>
    </xsl:if>
    <xsl:apply-templates select="*"/>
    <xsl:if test="count(.//token) = 1 and token/following::token">
      <xsl:text> </xsl:text>
    </xsl:if>
  </xsl:template>

  <xsl:template match="functionDecl/enclosedExpr">
    <br/>
    <xsl:apply-templates/>
  </xsl:template>

  <xsl:template match="functionDecl/enclosedExpr/expr" priority="1">
    <br/>
    <indent>
      <xsl:apply-templates/>
    </indent>
    <br/>
  </xsl:template>

  <xsl:template match="dirAttributeList/token[@type = 'S']" priority="1">
    <br class="marker"/>
    <xsl:text> </xsl:text>
  </xsl:template>

  <xsl:template match="dirElemContent">
    <xsl:if test="count(* except (token | ws | ElementContentChar)) > 0">
      <br/>
    </xsl:if>
    <indent>
      <xsl:apply-templates/>
    </indent>
    <xsl:if test="count(* except (token | ws | ElementContentChar)) > 0">
      <br/>
    </xsl:if>
  </xsl:template>

  <xsl:template match="dirElemContent//elemEnclosedExpr">
    <xsl:apply-templates select="token[1]"/>
    <br/>
    <indent>
      <xsl:apply-templates select="* except (token[1] | token[last()])"/>
      <br/>
    </indent>
    <xsl:apply-templates select="token[last()]"/>
  </xsl:template>

  <xsl:template match="enclosedExpr/expr">
    <indent>
      <xsl:apply-templates/>
    </indent>
  </xsl:template>

  <xsl:template match="exprSingle[not(parent::expr)]">
    <indent>
      <xsl:apply-templates/>
    </indent>
  </xsl:template>

  <xsl:template match="funcName | declFuncName">
    <xsl:if test="self::declFuncName">
      <a id="xqfunc-{string-join(.//token, '')}"/>
    </xsl:if>
    <xsl:apply-templates select=".//(ws | comment)"/>
    <span class="functionname">
      <xsl:value-of select="string-join(.//token, '')"/>
    </span>
  </xsl:template>

  <xsl:template match="sequenceType[token[@type='EMPTY_SEQUENCE']] | itemType[token[@type='ITEM']]">
    <xsl:variable name="firstToken" select="token[1]"/>
    <xsl:apply-templates select="*[. &lt;&lt; $firstToken]"/>
    <xsl:value-of select="string-join(.//token, '')"/>
  </xsl:template>

  <xsl:template match="kindTest//*[token/@type = 'LPAREN']">
    <xsl:variable name="paren" select="token[@type = 'LPAREN'][1]"/>
    <xsl:variable name="before-paren" select="$paren/preceding-sibling::token[1]"/>
    <xsl:apply-templates select="*[. &lt;&lt; $before-paren]"/>
    <xsl:value-of select="string-join(($before-paren, $paren), '')"/>
    <tabmarker/>
    <xsl:apply-templates select="*[. &gt;&gt; $paren]"/>
  </xsl:template>

  <xsl:template match="andExpr[token] | orExpr[token]">
    <xsl:for-each select="*">
      <xsl:if test=".[@type = 'AND' or @type = 'OR']">
        <br class="marker"/>
      </xsl:if>
      <xsl:apply-templates select="."/>
    </xsl:for-each>
  </xsl:template>

  <xsl:template match="variable">
    <xsl:apply-templates select=".//(ws | comment)"/>
    <span class="variable">
      <xsl:value-of select="string-join(.//token, '')"/>
    </span>
  </xsl:template>

  <xsl:template match="literal/numericLiteral">
    <xsl:apply-templates select=".//(ws | comment)"/>
    <span class="literal number">
      <xsl:value-of select="string-join(.//token, '')"/>
    </span>
  </xsl:template>

  <xsl:template match="literal/token">
    <xsl:apply-templates select=".//(ws | comment)"/>
    <span class="literal string">
      <xsl:value-of select="."/>
    </span>
  </xsl:template>

  <xsl:template match="qNameOrIdent | ncName">
    <xsl:apply-templates select=".//(ws | comment)"/>
    <span class="identifier">
      <xsl:value-of select="string-join(.//token, '')"/>
    </span>
  </xsl:template>

  <xsl:template match="ws[ancestor::functionCallPre | ancestor::functionDeclPre]">
    <!-- strip except for leading WS -->
    <xsl:if test=". &lt;&lt; (./(ancestor::functionCallPre |
      ancestor::functionDeclPre)//token)[1]">
      <xsl:call-template name="regular-ws"/>
    </xsl:if>
  </xsl:template>

  <xsl:template match="ws[parent::reverseAxis | parent::forwardAxis]">
    <!-- strip except for leading WS -->
    <xsl:if test=". &lt;&lt; (./(parent::reverseAxis | parent::forwardAxis)//token)[1]">
      <xsl:call-template name="regular-ws"/>
    </xsl:if>
  </xsl:template>

  <xsl:template match="ws" name="regular-ws">
    <xsl:choose>
      <xsl:when test="contains(., '&#xA;')">
        <br class="user"/>
      </xsl:when>
      <xsl:otherwise>
        <xsl:choose>
          <xsl:when test="preceding::token[1]/@type = $no-ws-after"/>
          <xsl:when test="preceding::token[1]/@type = ('RPAREN', 'RBRACKET') and
            following::token[1]/@type = $no-ws-after-r-tokens"/>
          <xsl:when test="following::token[1]/@type = $no-ws-before"/>
          <xsl:otherwise>
            <xsl:text> </xsl:text>
          </xsl:otherwise>
        </xsl:choose>
      </xsl:otherwise>
    </xsl:choose>
  </xsl:template>

  <xsl:template match="comment">
    <span class="comment{ if (starts-with(., '(::')) then ' xqdoc' else () }">
      <xsl:value-of select="."/>
    </span>
    <br/>
  </xsl:template>

  <xsl:template match="token[@type = 'ElementContentChar']">
    <xsl:variable name="content" select="normalize-space()"/>
    <span class="xmlcontent">
      <xsl:choose>
        <xsl:when test="$content = '' and contains(., '&#xA;')">
          <br class="user"/>
        </xsl:when>
        <xsl:otherwise>
          <xsl:value-of select="$content"/>
        </xsl:otherwise>
      </xsl:choose>
    </span>
  </xsl:template>

  <xsl:template match="token[@type = 'LPAREN']">
    <span class="operator">
      <xsl:value-of select="."/>
    </span>
    <tabmarker/>
  </xsl:template>

  <xsl:template match="token[@type = 'RPAREN']">
    <tabunmarker/>
    <span class="operator">
      <xsl:value-of select="."/>
    </span>
    <xsl:value-of select="xqp:insert-optional-blank(.)"/>
  </xsl:template>

  <xsl:template match="token[@type = 'COMMA']">
    <span class="operator">
      <xsl:value-of select="."/>
    </span>
    <br class="marker"/>
    <xsl:value-of select="xqp:insert-optional-blank(.)"/>
  </xsl:template>

  <xsl:template match="token[@type = 'S']">
    <xsl:text> </xsl:text>
  </xsl:template>

  <xsl:template match="token">
    <xsl:choose>
      <xsl:when test="matches(., '^\c+$')">
        <span class="keyword">
          <xsl:value-of select="."/>
        </span>
      </xsl:when>
      <xsl:otherwise>
        <span class="operator">
          <xsl:value-of select="."/>
        </span>
      </xsl:otherwise>
    </xsl:choose>
    <xsl:value-of select="xqp:insert-optional-blank(.)"/>
  </xsl:template>

  <xsl:template match="*">
    <xsl:apply-templates/>
  </xsl:template>

  <!-- Insert a whitespace after a token if it's not followed by a whitespace
    and it's not within a direct constructor, and the next token is not within
    a direct constructor, and they're not one of the "no whitespace before/after" tokens.
    -->
  <xsl:function name="xqp:insert-optional-blank">
    <xsl:param name="token"/>
    <xsl:variable name="enclosing-constructor" select="$token/ancestor::directConstructor[1]"/>
    <xsl:variable name="following-token" select="$token/following::token[1]"/>
    <xsl:variable name="not-beneath-directconstr" select="not($enclosing-constructor) or
      ($token/ancestor::elemEnclosedExpr[1] &gt;&gt; $enclosing-constructor)"/>
    <xsl:variable name="following-not-beneath-directconstr"
      select="$following-token[not(ancestor::directConstructor[1]) or ancestor::elemEnclosedExpr[1]
      &gt;&gt; ancestor::directConstructor[1]]"/>
    <xsl:if test="$not-beneath-directconstr and $following-not-beneath-directconstr">
      <xsl:variable name="should-insert" select="$following-token and not($token/@type =
        $no-ws-after) and not($following-token/@type = $no-ws-before)"/>
      <xsl:if test="$should-insert and ($following-token &lt;&lt; $token/following::ws[1] or
        not($token/following::ws[1]))">
        <xsl:text> </xsl:text>
      </xsl:if>
    </xsl:if>
  </xsl:function>

</xsl:stylesheet>
