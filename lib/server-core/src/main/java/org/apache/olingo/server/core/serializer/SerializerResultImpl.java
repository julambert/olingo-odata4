/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements. See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership. The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.apache.olingo.server.core.serializer;

import java.io.InputStream;
import java.nio.channels.Channel;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

import org.apache.olingo.server.api.serializer.SerializerResult;

public class SerializerResultImpl implements SerializerResult {
  private InputStream content;

  @Override
  public InputStream getContent() {
    return content;
  }

  @Override
  public ReadableByteChannel getChannel() {
    return Channels.newChannel(getContent());
  }

  @Override
  public boolean isNioSupported() {
    return false;
  }

  public static SerializerResultBuilder with() {
    return new SerializerResultBuilder();
  }

  public static class SerializerResultBuilder {
    private InputStream content;

    public SerializerResultBuilder content(final InputStream input) {
      content = input;

      return this;
    }

    public SerializerResult build() {
      SerializerResultImpl result = new SerializerResultImpl();
      result.content = content;

      return result;
    }
  }
}
