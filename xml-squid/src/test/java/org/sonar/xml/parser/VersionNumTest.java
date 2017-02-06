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

public class VersionNumTest {

  Grammar g = XmlGrammar.createGrammarBuilder().build();

  @Test
  public void ok() {
    assertThat(g.rule(XmlGrammar.VERSION_NUM))
        .matches("1.0")
        .matches("1.1")
        .matches("1.2")
        .matches("1.12345")

        .notMatches("0.0");
  }

}
