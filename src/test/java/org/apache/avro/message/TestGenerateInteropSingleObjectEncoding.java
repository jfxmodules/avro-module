/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.apache.avro.message;

import org.apache.avro.Schema;
import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.GenericRecordBuilder;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.Arrays;
import org.apache.commons.io.FileUtils;

/**
 * Generates <code>test_message.bin</code> - a <a href=
 * "https://avro.apache.org/docs/current/spec.html#single_object_encoding">single
 * object encoded</a> Avro message.
 */
public class TestGenerateInteropSingleObjectEncoding {

  private static Schema SCHEMA;
  private static GenericRecordBuilder BUILDER;

  @BeforeAll
  public static void setup() throws IOException {
    try (InputStream fileInputStream = TestInteropSingleObjectEncoding.class.getResourceAsStream("/messageV1/test_schema.avsc")) { //  new FileInputStream(SCHEMA_FILE)) {
      SCHEMA = new Schema.Parser().parse(fileInputStream);
      BUILDER = new GenericRecordBuilder(SCHEMA);
    }
  }

  @Test
  void generateData() throws IOException {
    MessageEncoder<GenericData.Record> encoder = new BinaryMessageEncoder<>(GenericData.get(), SCHEMA);
    BUILDER.set("id", 42L).set("name", "Bill").set("tags", Arrays.asList("dog_lover", "cat_hater")).build();
    ByteBuffer buffer = encoder.encode(
        BUILDER.set("id", 42L).set("name", "Bill").set("tags", Arrays.asList("dog_lover", "cat_hater")).build());
    ClassLoader classLoader = getClass().getClassLoader();
    File file = new File(classLoader.getResource("messageV1/test_message.bin").getFile());
    new FileOutputStream(file).write(buffer.array());
  }
}
