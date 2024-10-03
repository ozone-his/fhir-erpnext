/*
 * Copyright © 2024, Ozone HIS <info@ozone-his.com>
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package com.ozonehis.fhir.erpnext.inventoryItem;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

import com.fasterxml.jackson.core.type.TypeReference;
import com.ozonehis.camel.frappe.sdk.api.FrappeClient;
import com.ozonehis.camel.frappe.sdk.api.FrappeResponse;
import com.ozonehis.camel.frappe.sdk.api.operation.GetOperation;
import com.ozonehis.eip.model.erpnext.FrappeSingularDataWrapper;
import com.ozonehis.fhir.erpnext.model.Item;
import java.util.Optional;
import org.apache.hc.core5.http.HttpStatus;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.openmrs.fhir.InventoryItem;

class InventoryItemServiceTest {

    @Mock
    private FrappeClient client;

    @Mock
    private FrappeResponse response;

    @Mock
    private InventoryItemMapper mapper;

    private InventoryItemService service;

    String id = "123";

    private static AutoCloseable mockCloser;

    @BeforeEach
    void setUp() {
        mockCloser = openMocks(this);
        service = new InventoryItemService(client, mapper);

        FrappeResponse response = mock(FrappeResponse.class);
        GetOperation getOperation = mock(GetOperation.class);

        when(client.get("Item", id)).thenReturn(getOperation);
        when(getOperation.withFields(new Item().getFields())).thenReturn(getOperation);
        when(getOperation.execute()).thenReturn(response);
    }

    @AfterAll
    static void closeMocks() throws Exception {
        mockCloser.close();
    }

    @Test
    @DisplayName("getById returns InventoryItem when found")
    void getById_returnsInventoryItemWhenResponseIsOk() {
        Item item = new Item();
        item.setName("item1");
        TypeReference<FrappeSingularDataWrapper<Item>> typeRef = new TypeReference<>() {};
        FrappeSingularDataWrapper<Item> itemWrapper = new FrappeSingularDataWrapper<>();
        itemWrapper.setData(item);

        InventoryItem inventoryItem = new InventoryItem();
        inventoryItem.setId("item1");

        when(response.code()).thenReturn(HttpStatus.SC_OK);
        when(response.returnAs(typeRef)).thenReturn(itemWrapper);
        when(mapper.toFhir(item)).thenReturn(inventoryItem);

        // Act
        Optional<InventoryItem> result = service.getById(id);

        // Assert
        assertNotNull(result);
    }

    @Test
    @DisplayName("getById returns empty when response is not ok")
    void getById_returnsEmptyWhenResponseIsNotOk() {
        // Arrange
        when(response.code()).thenReturn(HttpStatus.SC_NOT_FOUND);

        // Act
        Optional<InventoryItem> result = service.getById(id);

        // Assert
        assertFalse(result.isPresent());
    }
}
