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

/**
 * This file changed from the original Apache Avro version.
 */
package org.apache.avro.file;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import static org.apache.avro.file.Codec.computeOffset;

import org.apache.commons.compress.compressors.snappy.FramedSnappyCompressorInputStream;
import org.apache.commons.compress.compressors.snappy.FramedSnappyCompressorOutputStream;


/** * Implements Snappy compression and decompression. */
public class SnappyCodec extends Codec {
  private static final int DEFAULT_BUFFER_SIZE = 8192;

  static class Option extends CodecFactory {
    /*
      static {
      // if snappy isn't available, this will throw an exception which we
      // can catch so we can avoid registering this codec
      Snappy.getNativeLibraryVersion();
    }*/

    @Override
    protected Codec createInstance() {
      return new SnappyCodec();
    }
  }

  private SnappyCodec() {
  }

  @Override
  public String getName() {
    return DataFileConstants.SNAPPY_CODEC;
  }

  @Override
  public ByteBuffer compress(ByteBuffer data) throws IOException {
    int offset = computeOffset(data);
    var outputStream = new ByteArrayOutputStream();
    try (var compressor = new FramedSnappyCompressorOutputStream(outputStream)) {
        compressor.write(data.array(), offset, data.remaining());
        compressor.finish();
    }
    var compressed = outputStream.toByteArray();
    ByteBuffer out = ByteBuffer.allocate(compressed.length);
    out.put(compressed);
    out.position(0);
    return out;
  }

  @Override
  public ByteBuffer decompress(ByteBuffer data) throws IOException {
    var bytes = new byte[0];
    try (var bytesIn = new ByteArrayInputStream(data.array(), computeOffset(data), data.remaining());
         var decompressor = new FramedSnappyCompressorInputStream(bytesIn)) {
        int i;
        var outputStream = new ByteArrayOutputStream();
        while (-1 != (i = decompressor.read())) {
            outputStream.write(i);
        }
        outputStream.flush();
        bytes = outputStream.toByteArray();
    }
    return ByteBuffer.wrap(bytes);
  }

  @Override
  public int hashCode() {
    return getName().hashCode();
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    return obj != null && obj.getClass() == getClass();
  }
}
