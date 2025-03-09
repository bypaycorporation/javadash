<!---
 Licensed to the ByPay Software Foundation (ASF) under one or more
 contributor license agreements.  See the NOTICE file distributed with
 this work for additional information regarding copyright ownership.
 The ASF licenses this file to You under the Apache License, Version 2.0
 (the "License"); you may not use this file except in compliance with
 the License.  You may obtain a copy of the License at

      http://www.apache.org/licenses/LICENSE-2.0

 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.
-->

<img src="https://javadash.bypay.io.vn/assets/javadash.svg" alt="drawing" width="220" align = "right"/>

[![Maven Central](https://img.shields.io/maven-central/v/io.github.bypaycorporation/javadash?label=Maven%20Central)](https://search.maven.org/artifact/io.github.bypaycorporation/javadash)
[![javadoc](https://javadoc.io/badge2/io.github.bypaycorporation/javadash/javadoc.svg)](https://javadoc.io/doc/io.github.bypaycorporation/javadash)

[![MIT License](http://img.shields.io/badge/license-MIT-green.svg)](https://github.com/bypaycorporation/javadash/blob/main/LICENSE)
[![Java CI](https://github.com/bypaycorporation/javadash/actions/workflows/maven.yml/badge.svg)](https://github.com/bypaycorporation/javadash/actions/workflows/maven.yml)
[![CodeQL](https://github.com/bypaycorporation/javadash/actions/workflows/codeql.yml/badge.svg)](https://github.com/bypaycorporation/javadash/actions/workflows/codeql.yml)
[![Semgrep](https://github.com/bypaycorporation/javadash/actions/workflows/semgrep.yml/badge.svg)](https://github.com/bypaycorporation/javadash/actions/workflows/semgrep.yml)
[![Scorecard supply-chain security](https://github.com/bypaycorporation/javadash/actions/workflows/scorecard.yml/badge.svg?branch=main)](https://github.com/bypaycorporation/javadash/actions/workflows/scorecard.yml)
[![OSSAR](https://github.com/bypaycorporation/javadash/actions/workflows/ossar.yml/badge.svg?branch=main)](https://github.com/bypaycorporation/javadash/actions/workflows/ossar.yml)
[![OpenSSF Best Practices](https://bestpractices.coreinfrastructure.org/projects/7019/badge)](https://bestpractices.coreinfrastructure.org/projects/7019)
[![Coverage Status](https://coveralls.io/repos/github/bypaycorporation/javadash/badge.svg?branch=main)](https://coveralls.io/github/bypaycorporation/javadash?branch=main)
[![CircleCI](https://circleci.com/gh/bypaycorporation/javadash.svg?style=svg)](https://circleci.com/gh/bypaycorporation/javadash)
[![Build status](https://ci.appveyor.com/api/projects/status/tx7icv3i08qowv6r?svg=true)](https://ci.appveyor.com/project/vantuan0101/javadash)

[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=bypaycorporation_javadash&metric=alert_status)](https://sonarcloud.io/summary/overall?id=bypaycorporation_javadash)
[![Maintainability Rating](https://sonarcloud.io/api/project_badges/measure?project=bypaycorporation_javadash&metric=sqale_rating)](https://sonarcloud.io/summary/overall?id=bypaycorporation_javadash)

[![Hits-of-Code](https://hitsofcode.com/github/bypaycorporation/javadash?branch=main)](https://hitsofcode.com/github/bypaycorporation/javadash/view?branch=main)
[![codebeat badge](https://codebeat.co/badges/1060a230-7634-4ae1-94bf-bd2d659bb9c3)](https://codebeat.co/projects/github-com-bypaycorporation-javadash-main)
![Java Version](https://img.shields.io/badge/java-%3E%3D%208-success)
[![](https://img.shields.io/github/stars/bypaycorporation/javadash?style=flat-square)](https://github.com/bypaycorporation/javadash)
[![](https://img.shields.io/github/forks/bypaycorporation/javadash?style=flat-square)](https://github.com/bypaycorporation/javadash/fork)

JavaDash
===============

Requirements
============
Java 8 and later

Description
===================

JavaDash, A Java utility library inspired by Lodash, 
providing helpful methods for common operations like manipulation, iteration, and more. 
This library is designed to provide similar functionality to Lodash's JavaScript methods 
but in a Java-friendly way.

Sponsored by
===================

This library is sponsored by **ByPay Corporation**.

Documentation
-------------

Installation
-------------

You can include the library in your Java project by adding the appropriate dependency to your `pom.xml` (if using Maven) or `build.gradle` (if using Gradle).

Maven
-------------

```xml
<dependency>
    <groupId>io.github.bypaycorporation</groupId>
    <artifactId>javadash</artifactId>
    <version>2.0.0</version>
</dependency>
```

Gradle
-------------

```groovy
  implementation 'io.github.bypaycorporation:javadash:2.0.0'
```

### Usage

```java
  import java.util.*;
  import static io.javadash.CollectionUtils.chunk;
  public class Main {
    public static void main(String[] args) {
      List<String> input = Arrays.asList("a", "b", "c", "d");
      int size = 2;
      List<List<String>> result = chunk(input, size);
      System.out.println(result);
    }
  }
```

In addition to porting JavaDash's functionality, JavaDash includes matching unit tests.

For docs, license, tests, and downloads, see:
https://github.com/bypaycorporation/javadash

Thanks to Van Tuan and Jake Moek and all contributors to JavaDash.