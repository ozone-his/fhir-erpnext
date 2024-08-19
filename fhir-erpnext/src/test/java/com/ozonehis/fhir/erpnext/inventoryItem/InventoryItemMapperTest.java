/*
 * Copyright © 2024, Ozone HIS <info@ozone-his.com>
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package com.ozonehis.fhir.erpnext.inventoryItem;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import com.ozonehis.fhir.InventoryItem;
import com.ozonehis.fhir.erpnext.mappers.CodeMapper;
import com.ozonehis.fhir.erpnext.model.Item;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = {InventoryItemMapper.class, CodeMapper.class})
class InventoryItemMapperTest {

    @Autowired
    private InventoryItemMapper inventoryItemMapper;

    @Test
    @DisplayName("toFhir maps ERPNext Item to FHIR InventoryItem")
    void toFhir_MapsERPNextItemToFHIRInventoryItem() {
        // Arrange
        Item item = new Item();
        item.setStockItem(true);
        item.setName("item1");
        item.setDisabled(false);
        item.setItemCode("code1");
        item.setItemName("Item Name");
        item.setDescription("Description");
        item.setOpeningStock(10);
        item.setStockUom("units");

        // Act
        InventoryItem inventoryItem = inventoryItemMapper.toFhir(item);

        // Assert
        assertEquals("item1", inventoryItem.getId());
        assertEquals(InventoryItem.InventoryItemStatusCodes.ACTIVE, inventoryItem.getStatus());
        assertEquals(
                "item1", inventoryItem.getCodeFirstRep().getCodingFirstRep().getCode());
        assertEquals("Item Name", inventoryItem.getNameFirstRep().getName());
        assertEquals("Description", inventoryItem.getDescription().getDescription());
        assertEquals(10, inventoryItem.getNetContent().getValue().intValue());
        assertEquals("units", inventoryItem.getNetContent().getUnit());
    }

    @Test
    @DisplayName("toFhir should return null if item is null")
    void toFhir_shouldReturnNullIfItemIsNull() {
        // Act
        InventoryItem inventoryItem = inventoryItemMapper.toFhir(null);
        // Assert
        assertNull(inventoryItem);
    }

    @Test
    @DisplayName("toFhir should return null for non-stock item")
    void toFhir_shouldReturnNullIfNonStockItem() {
        // Arrange
        Item item = new Item();
        item.setStockItem(false);

        // Act
        InventoryItem inventoryItem = inventoryItemMapper.toFhir(item);

        // Assert
        assertNull(inventoryItem);
    }
}
