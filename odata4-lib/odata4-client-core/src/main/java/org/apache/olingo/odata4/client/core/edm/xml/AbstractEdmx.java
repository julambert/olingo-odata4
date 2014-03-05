/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.apache.olingo.odata4.client.core.edm.xml;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.apache.olingo.odata4.client.api.edm.xml.DataServices;
import org.apache.olingo.odata4.client.api.edm.xml.Edmx;

@JsonDeserialize(using = EdmxDeserializer.class)
public abstract class AbstractEdmx extends AbstractEdmItem implements Edmx {

  private static final long serialVersionUID = -5480835122183091469L;

  private String version;

  private DataServices dataServices;

  @Override
  public String getVersion() {
    return version;
  }

  @Override
  public void setVersion(final String version) {
    this.version = version;
  }

  @Override
  public DataServices getDataServices() {
    return dataServices;
  }

  @Override
  public void setDataServices(final DataServices dataServices) {
    this.dataServices = dataServices;
  }
}