/**
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
package com.msopentech.odatajclient.engine.it;

import static org.junit.Assert.*;

import com.msopentech.odatajclient.engine.communication.ODataClientErrorException;
import com.msopentech.odatajclient.engine.communication.request.retrieve.ODataEntityRequest;
import java.util.List;
import org.junit.Test;

import com.msopentech.odatajclient.engine.communication.request.retrieve.ODataEntitySetRequest;
import com.msopentech.odatajclient.engine.communication.response.ODataRetrieveResponse;
import com.msopentech.odatajclient.engine.data.ODataEntity;
import com.msopentech.odatajclient.engine.data.ODataEntitySet;
import com.msopentech.odatajclient.engine.uri.URIBuilder;
import com.msopentech.odatajclient.engine.format.ODataPubFormat;

public class EntitySetRetrieveTestITCase extends AbstractTestITCase {

    private void retreiveEntityTest(ODataPubFormat reqFormat, String acceptFormat) {
        URIBuilder uriBuilder = client.getURIBuilder(testStaticServiceRootURL).
                appendEntityTypeSegment("Product");
        ODataEntitySetRequest req = client.getRetrieveRequestFactory().getEntitySetRequest(uriBuilder.build());
        req.setFormat(reqFormat);
        req.setAccept(acceptFormat);
        try {
            ODataRetrieveResponse<ODataEntitySet> res = req.execute();
            assertEquals(200, res.getStatusCode());
            ODataEntitySet entitySet = res.getBody();
            List<ODataEntity> entity = entitySet.getEntities();
            for (int i = 0; i < entity.size(); i++) {
                assertNotNull(entity.get(i));
            }
            assertEquals(10, entity.size());
        } catch (ODataClientErrorException e) {
            assertEquals(415, e.getStatusLine().getStatusCode());
        }
    }

    private void retreiveFullMetadataEntityTest(String acceptFormat) {
        URIBuilder uriBuilder = client.getURIBuilder(testStaticServiceRootURL).
                appendEntitySetSegment("Product");
        ODataEntitySetRequest req = client.getRetrieveRequestFactory().getEntitySetRequest(uriBuilder.build());
        req.setAccept(acceptFormat);
        ODataRetrieveResponse<ODataEntitySet> res = req.execute();
        assertEquals(200, res.getStatusCode());
        ODataEntitySet entitySet = res.getBody();
        assertNotNull(entitySet);
        List<ODataEntity> entity = entitySet.getEntities();
        assertEquals(10, entity.size());
        for (int i = 0; i < entity.size(); i++) {
            assertNotNull(entity.get(i));
        }
    }

    private void retreiveNoMetadataEntityTest(final ODataPubFormat reqFormat, final String acceptFormat) {
        final URIBuilder uriBuilder = client.getURIBuilder(testStaticServiceRootURL).
                appendEntitySetSegment("Product");
        final ODataEntitySetRequest req = client.getRetrieveRequestFactory().getEntitySetRequest(uriBuilder.build());
        req.setFormat(reqFormat);
        req.setAccept(acceptFormat);
        final ODataRetrieveResponse<ODataEntitySet> res = req.execute();
        assertEquals(200, res.getStatusCode());
        final ODataEntitySet entitySet = res.getBody();
        assertNotNull(entitySet);
        List<ODataEntity> entity = entitySet.getEntities();
        assertEquals(10, entity.size());
        for (int i = 0; i < entity.size(); i++) {
            assertNotNull(entity.get(i));
        }
    }

    private void retreiveMinimalMetadataEntityTest(final ODataPubFormat reqFormat, final String acceptFormat) {
        final URIBuilder uriBuilder = client.getURIBuilder(testStaticServiceRootURL).
                appendEntitySetSegment("Product");
        final ODataEntitySetRequest req = client.getRetrieveRequestFactory().getEntitySetRequest(uriBuilder.build());
        req.setFormat(reqFormat);
        req.setAccept(acceptFormat);
        final ODataRetrieveResponse<ODataEntitySet> res = req.execute();
        assertEquals(200, res.getStatusCode());
        final ODataEntitySet entitySet = res.getBody();
        assertNotNull(entitySet);
        final List<ODataEntity> entity = entitySet.getEntities();
        assertEquals(10, entity.size());
        for (int i = 0; i < entity.size(); i++) {
            assertNotNull(entity.get(i));
        }
    }

    private void retreiveEntityTestWithExpand(final ODataPubFormat reqFormat, final String acceptFormat,
            final String expandFormat) {
        URIBuilder uriBuilder = client.getURIBuilder(testStaticServiceRootURL).
                appendEntitySetSegment("Customer").expand(expandFormat);
        ODataEntitySetRequest req = client.getRetrieveRequestFactory().getEntitySetRequest(uriBuilder.build());
        req.setFormat(reqFormat);
        req.setAccept(acceptFormat);
        try {
            ODataRetrieveResponse<ODataEntitySet> res = req.execute();
            assertEquals(200, res.getStatusCode());
            ODataEntitySet entitySet = res.getBody();
            assertNotNull(entitySet);
            final List<ODataEntity> entity = entitySet.getEntities();
            assertNotNull(entity);
        } catch (ODataClientErrorException e) {
            assertEquals(415, e.getStatusLine().getStatusCode());
        }
    }

    private void retreiveEntityTestWithSelect(ODataPubFormat reqFormat, String acceptFormat, String selectFormat) {
        URIBuilder uriBuilder = client.getURIBuilder(testStaticServiceRootURL).
                appendEntitySetSegment("Customer").select(selectFormat);
        ODataEntitySetRequest req = client.getRetrieveRequestFactory().getEntitySetRequest(uriBuilder.build());
        req.setFormat(reqFormat);
        req.setAccept(acceptFormat);
        try {
            ODataRetrieveResponse<ODataEntitySet> res = req.execute();
            assertEquals(200, res.getStatusCode());
            ODataEntitySet entitySet = res.getBody();
            assertNotNull(entitySet);
            final List<ODataEntity> entity = entitySet.getEntities();
            assertNotNull(entity);
        } catch (ODataClientErrorException e) {
            assertEquals(415, e.getStatusLine().getStatusCode());
        }
    }

    private void retreiveEntityTestWithSelectAndExpand(
            final ODataPubFormat reqFormat,
            final String acceptFormat,
            final String selectFormat,
            final String expandFormat) {
        URIBuilder uriBuilder = client.getURIBuilder(testStaticServiceRootURL).
                appendEntitySetSegment("Customer").appendKeySegment(-10).select(selectFormat).expand(expandFormat);
        ODataEntityRequest req = client.getRetrieveRequestFactory().getEntityRequest(uriBuilder.build());
        req.setFormat(reqFormat);
        req.setAccept(acceptFormat);
        try {
            ODataRetrieveResponse<ODataEntity> res = req.execute();
            assertEquals(200, res.getStatusCode());
            ODataEntity entity = res.getBody();
            assertNotNull(entity);
        } catch (ODataClientErrorException e) {
            assertEquals(415, e.getStatusLine().getStatusCode());
        }
    }

    private void generalQuery(final ODataPubFormat format, final String acceptHeader, String query) {
        final URIBuilder uriBuilder = client.getURIBuilder(testStaticServiceRootURL).
                appendEntityTypeSegment(query);
        final ODataEntitySetRequest req = client.getRetrieveRequestFactory().getEntitySetRequest(uriBuilder.build());
        req.setFormat(format);
        req.setAccept(acceptHeader);
        final ODataRetrieveResponse<ODataEntitySet> res = req.execute();
        assertEquals(200, res.getStatusCode());
        final ODataEntitySet entitySet = res.getBody();
        assertNotNull(entitySet);
    }

    private void inlineCountTest(final ODataPubFormat format, final String acceptHeader, final String filterValue) {
        final URIBuilder uriBuilder = client.getURIBuilder(testStaticServiceRootURL).
                appendEntityTypeSegment("Customer").inlineCount().filter(filterValue);
        final ODataEntitySetRequest req = client.getRetrieveRequestFactory().getEntitySetRequest(uriBuilder.build());
        req.setFormat(format);
        req.setAccept(acceptHeader);
        try {
            final ODataRetrieveResponse<ODataEntitySet> res = req.execute();
            assertEquals(200, res.getStatusCode());
            final ODataEntitySet entitySet = res.getBody();
            assertNotNull(entitySet);
            List<ODataEntity> entity = entitySet.getEntities();
            for (int i = 0; i < entity.size(); i++) {
                assertNotNull(entity.get(i));
            }
        } catch (ODataClientErrorException e) {
            assertEquals(415, e.getStatusLine().getStatusCode());
        }
    }

    @Test
    public void inlineAndFilterTest() {
        inlineCountTest(ODataPubFormat.ATOM, "application/atom+xml", "CustomerId eq -10");
        inlineCountTest(ODataPubFormat.JSON, "application/json", "CustomerId eq -10");
        inlineCountTest(ODataPubFormat.JSON_FULL_METADATA, "application/json;odata=fullmetadata", "CustomerId eq -10");
        inlineCountTest(ODataPubFormat.JSON_NO_METADATA, "application/json;odata=nometadata", "CustomerId eq -10");

        //xml headers is not a supported media type 
        inlineCountTest(ODataPubFormat.ATOM, "application/xml", "CustomerId eq -10");

        //with different filters
        inlineCountTest(ODataPubFormat.ATOM, "application/atom+xml", "CustomerId lt -10");
        inlineCountTest(ODataPubFormat.ATOM, "application/atom+xml", "CustomerId gt -10");
    }

    @Test
    public void jsonHeader() {
        retreiveEntityTest(ODataPubFormat.JSON, "application/json");
        generalQuery(ODataPubFormat.JSON, "application/json", "Product");
        retreiveEntityTestWithExpand(ODataPubFormat.JSON, "application/json", "Orders");
        retreiveEntityTestWithSelect(ODataPubFormat.JSON, "application/json", "CustomerId,Name,PrimaryContactInfo");
        retreiveEntityTestWithSelectAndExpand(
                ODataPubFormat.JSON, "application/json", "CustomerId,Name,Orders", "Orders");
    }

    @Test
    public void jsonFullMetaDataHeader() {
        retreiveFullMetadataEntityTest("application/json;odata=fullmetadata");
        generalQuery(ODataPubFormat.JSON, "application/json;odata=fullmetadata", "Product");
    }

    @Test
    public void jsonNoMetaDataHeader() {
        retreiveNoMetadataEntityTest(ODataPubFormat.JSON_NO_METADATA, "application/json;odata=nometadata");
    }

    @Test
    public void jsonMinimalMetadataDataHeader() {
        retreiveMinimalMetadataEntityTest(ODataPubFormat.JSON, "application/json;odata=minimalmetadata");
        retreiveEntityTestWithExpand(ODataPubFormat.JSON, "application/json;odata=minimalmetadata", "Orders");
        retreiveEntityTestWithExpand(ODataPubFormat.JSON, "application/json;odata=minimalmetadata", "Orders,Info");
    }

    @Test
    public void atomHeader() {
        retreiveEntityTest(ODataPubFormat.ATOM, "application/atom+xml");
        retreiveEntityTestWithExpand(ODataPubFormat.ATOM, "application/atom+xml", "Orders");
        retreiveEntityTestWithExpand(ODataPubFormat.ATOM, "application/atom+xml", "Orders,Info");
        retreiveEntityTestWithSelect(ODataPubFormat.ATOM, "application/atom+xml", "CustomerId");
        retreiveEntityTestWithSelect(ODataPubFormat.ATOM, "application/atom+xml", "Name");
        retreiveEntityTestWithSelect(ODataPubFormat.ATOM, "application/atom+xml", "CustomerId,Name,PrimaryContactInfo");
        retreiveEntityTestWithSelectAndExpand(
                ODataPubFormat.ATOM, "application/atom+xml", "CustomerId,Name,Orders", "Orders");
    }

    @Test
    public void xmlHeader() {
        retreiveEntityTest(ODataPubFormat.ATOM, "application/xml");
        retreiveEntityTestWithExpand(ODataPubFormat.ATOM, "application/xml", "Orders");
        retreiveEntityTestWithExpand(ODataPubFormat.ATOM, "application/xml", "Orders,Info");
        retreiveEntityTestWithSelect(ODataPubFormat.ATOM, "application/xml", "CustomerId");
        retreiveEntityTestWithSelect(ODataPubFormat.ATOM, "application/xml", "Name");
        retreiveEntityTestWithSelect(ODataPubFormat.ATOM, "application/xml", "CustomerId,Name,PrimaryContactInfo");
        retreiveEntityTestWithSelectAndExpand(
                ODataPubFormat.ATOM, "application/xml", "CustomerId,Name,Orders", "Orders");
    }

    @Test
    public void nullFormat() {
        retreiveEntityTest(null, "application/xml");
        retreiveEntityTestWithExpand(null, "application/xml", "Orders");
        retreiveEntityTestWithExpand(null, "application/xml", "Orders,Info");
        retreiveEntityTestWithSelect(null, "application/xml", "CustomerId");
        retreiveEntityTestWithSelect(null, "application/xml", "Name");
        retreiveEntityTestWithSelect(null, "application/xml", "CustomerId,Name,PrimaryContactInfo");
        retreiveEntityTestWithSelectAndExpand(null, "application/xml", "CustomerId,Name,Orders", "Orders");
    }

    @Test
    public void nullHeaderWithJSONFormat() {
        retreiveEntityTest(ODataPubFormat.JSON, null);
        retreiveEntityTestWithExpand(ODataPubFormat.JSON, null, "Orders");
        retreiveEntityTestWithExpand(ODataPubFormat.JSON, null, "Orders,Info");
        retreiveEntityTestWithSelect(ODataPubFormat.JSON, null, "CustomerId");
        retreiveEntityTestWithSelect(ODataPubFormat.JSON, null, "Name");
        retreiveEntityTestWithSelect(ODataPubFormat.JSON, null, "CustomerId,Name,PrimaryContactInfo");
        retreiveEntityTestWithSelectAndExpand(ODataPubFormat.JSON, null, "CustomerId,Name,Orders", "Orders");
    }

    @Test
    public void nullHeaderWithAtomFormat() {
        retreiveEntityTest(ODataPubFormat.ATOM, null);
        retreiveEntityTestWithExpand(ODataPubFormat.ATOM, null, "Orders");
        retreiveEntityTestWithExpand(ODataPubFormat.ATOM, null, "Orders,Info");
        retreiveEntityTestWithSelect(ODataPubFormat.ATOM, null, "CustomerId");
        retreiveEntityTestWithSelect(ODataPubFormat.ATOM, null, "Name");
        retreiveEntityTestWithSelect(ODataPubFormat.ATOM, null, "CustomerId,Name,PrimaryContactInfo");
        retreiveEntityTestWithSelectAndExpand(ODataPubFormat.ATOM, null, "CustomerId,Name,Orders", "Orders");
    }
}