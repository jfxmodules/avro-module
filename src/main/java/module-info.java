/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

module org.jfxmodules.avro {
    requires jdk.unsupported;
    requires jdk.management;
    
    requires org.apache.commons.compress;
    requires org.slf4j;
    requires com.fasterxml.jackson.databind;
    requires com.fasterxml.jackson.annotation;
    requires com.fasterxml.jackson.core;
    requires java.management;

    exports org.apache.avro;
    exports org.apache.avro.data;
    exports org.apache.avro.file;
    exports org.apache.avro.generic;
    exports org.apache.avro.io;
    exports org.apache.avro.io.parsing;
    exports org.apache.avro.message;
    exports org.apache.avro.path;
    exports org.apache.avro.reflect;
    exports org.apache.avro.specific;
    exports org.apache.avro.util;
    exports org.apache.avro.util.internal;
    exports org.apache.avro.util.springframework;
}
