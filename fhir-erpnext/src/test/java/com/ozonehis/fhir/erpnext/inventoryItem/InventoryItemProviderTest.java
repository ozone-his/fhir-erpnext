/*
 * Copyright © 2024, Ozone HIS <info@ozone-his.com>
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package com.ozonehis.fhir.erpnext.inventoryItem;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

import ca.uhn.fhir.rest.server.exceptions.ResourceNotFoundException;
import com.ozonehis.fhir.InventoryItem;
import java.util.Optional;
import org.hl7.fhir.r4.model.IdType;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

class InventoryItemProviderTest {

    @Mock
    private InventoryItemService inventoryItemService;

    private InventoryItemProvider inventoryItemProvider;

    private static AutoCloseable mockCloser;

    @BeforeEach
    void setUp() {
        mockCloser = openMocks(this);
        inventoryItemProvider = new InventoryItemProvider(inventoryItemService);
    }

    @AfterAll
    static void closeMocks() throws Exception {
        mockCloser.close();
    }

    @Test
    @DisplayName("getInventoryItemById returns InventoryItem when found")
    void getInventoryItemById_returnsInventoryItemWhenFound() {
        IdType id = new IdType("123");
        InventoryItem inventoryItem = new InventoryItem();
        inventoryItem.setId("123");

        when(inventoryItemService.getById("123")).thenReturn(Optional.of(inventoryItem));

        InventoryItem result = inventoryItemProvider.getInventoryItemById(id);

        assertEquals(inventoryItem, result);
    }

    @Test
    @DisplayName("getInventoryItemById throws ResourceNotFoundException when not found")
    void getInventoryItemById_throwsResourceNotFoundExceptionWhenNotFound() {
        IdType id = new IdType("123");

        when(inventoryItemService.getById("123")).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> inventoryItemProvider.getInventoryItemById(id));
    }
}
