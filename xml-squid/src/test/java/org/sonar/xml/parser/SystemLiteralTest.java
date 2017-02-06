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

public class SystemLiteralTest {

  Grammar g = XmlGrammar.createGrammarBuilder().build();

  @Test
  public void ok() {
    assertThat(g.rule(XmlGrammar.SYSTEM_LITERAL))
        .matches("\"\"")
        .matches("\"foo\"")
        .matches("\"foo<bar\"")
        .matches("\"foo'bar\"")

        .matches("''")
        .matches("'foo'")
        .matches("'foo<bar'")
        .matches("'foo\"bar'")

        .notMatches("'foo'bar'")
        .notMatches("\"foo\"bar\"")

        .notMatches("'\"")
        .notMatches("\"'");
  }

}
