/*
 * Copyright (C) 2010-2017 SonarSource SA
 * All rights reserved
 * mailto:info AT sonarsource DOT com
 */
package org.sonar.xml.parser;

import com.sonar.sslr.api.Grammar;
import org.junit.Test;
import org.sonar.xml.api.XmlGrammar;

import static org.sonar.sslr.tests.Assertions.assertThat;

public class ContentTest {

  Grammar g = XmlGrammar.createGrammarBuilder().build();

  @Test
  public void ok() {
    assertThat(g.rule(XmlGrammar.CONTENT))
        .matches("")
        .matches("foo")
        .matches("<foo />")
        .matches("<foo />bar")
        .matches("foo<bar />")
        .matches("foo<!-- bar -->")
        .matches("<?foo?>bar<baz />")
        .matches("<![CDATA[foo]]>bar<baz /><?qux?>&foo;...");
  }

}
