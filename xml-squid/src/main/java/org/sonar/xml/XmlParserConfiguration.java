/*
 * SonarQube XML Plugin
 * Copyright (C) 2010-2017 SonarSource SA
 * mailto:info AT sonarsource DOT com
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 3 of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 */
package org.sonar.xml;

import java.nio.charset.Charset;

import static com.google.common.base.Preconditions.checkNotNull;

public class XmlParserConfiguration {

  private final Charset charset;

  private XmlParserConfiguration(Builder builder) {
    this.charset = builder.charset;
  }

  public Charset getCharset() {
    return charset;
  }

  public static Builder builder() {
    return new Builder();
  }

  public static final class Builder {

    private Charset charset = null;

    private Builder() {
    }

    public Builder setCharset(Charset charset) {
      this.charset = charset;
      return this;
    }

    public Charset getCharset() {
      return charset;
    }

    public XmlParserConfiguration build() {
      checkNotNull(charset, "charset is mandatory and cannot be left null");
      return new XmlParserConfiguration(this);
    }

  }

}
