/*
 * Copyright (C) 2010-2017 SonarSource SA
 * All rights reserved
 * mailto:info AT sonarsource DOT com
 */
package org.sonar.plugins.xml.checks;

import static org.fest.assertions.Assertions.assertThat;

import java.io.File;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.junit.Test;

public class CheckRepositoryTest {

  @Test
  public void properties() {
    assertThat(CheckRepository.REPOSITORY_KEY).isEqualTo("xml");
    assertThat(CheckRepository.REPOSITORY_NAME).isEqualTo("SonarQube");
    assertThat(CheckRepository.SONAR_WAY_PROFILE_NAME).isEqualTo("Sonar way");
  }

  @Test
  public void getCheckClasses() {
    assertThat(CheckRepository.getCheckClasses().size()).isEqualTo(CheckRepository.getChecks().size());
  }

  /**
   * Enforces that each check declared in list.
   */
  @Test
  public void getChecksEachCheckShouldBeDeclared() {
    int count = 0;
    List<File> files = (List<File>) FileUtils.listFiles(new File("src/main/java/org/sonar/plugins/xml/checks/"), new String[] {"java"}, false);
    for (File file : files) {
      if (file.getName().endsWith("Check.java") && !file.getName().endsWith("AbstractXmlCheck.java")) {
        count++;
      }
    }
    assertThat(CheckRepository.getChecks().size()).isEqualTo(count);
  }

  /**
   * Enforces that each check has test
   */
  @Test
  public void test() {
    List<Class> checks = CheckRepository.getCheckClasses();

    for (Class cls : checks) {
      String testName = '/' + cls.getName().replace('.', '/') + "Test.class";
      assertThat(getClass().getResource(testName))
        .overridingErrorMessage("No test for " + cls.getSimpleName())
        .isNotNull();
    }
  }
}
