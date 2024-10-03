/*
 * Copyright © 2024, Ozone HIS <info@ozone-his.com>
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package com.ozonehis.fhir.erpnext.inventoryItem;

import com.ozonehis.fhir.erpnext.mappers.CodeMapper;
import com.ozonehis.fhir.erpnext.mappers.ToFhirMapping;
import com.ozonehis.fhir.erpnext.model.Item;
import java.util.List;
import org.hl7.fhir.r4.model.Quantity;
import org.openmrs.fhir.InventoryItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class InventoryItemMapper implements ToFhirMapping<Item, InventoryItem> {

    private final CodeMapper codeMapper;

    @Autowired
    public InventoryItemMapper(CodeMapper codeMapper) {
        this.codeMapper = codeMapper;
    }

    @Override
    public InventoryItem toFhir(Item item) {
        var inventoryItem = new InventoryItem();
        if (item == null || !item.isStockItem()) {
            return null;
        }
        inventoryItem.setId(item.getName());
        if (inventoryItem.hasCode()) {
            inventoryItem.addCode(codeMapper.toFhir(item));
        }

        if (item.isDisabled()) inventoryItem.setStatus(InventoryItem.InventoryItemStatusCodes.INACTIVE);
        else inventoryItem.setStatus(InventoryItem.InventoryItemStatusCodes.ACTIVE);

        inventoryItem.setCode(List.of(codeMapper.toFhir(item)));

        InventoryItem.InventoryItemNameComponent nameComponent = new InventoryItem.InventoryItemNameComponent();
        nameComponent.setName(item.getItemName());
        inventoryItem.addName(nameComponent);

        InventoryItem.InventoryItemDescriptionComponent descriptionComponent =
                new InventoryItem.InventoryItemDescriptionComponent();
        descriptionComponent.setDescription(item.getDescription());
        inventoryItem.setDescription(descriptionComponent);

        Quantity quantity = new Quantity();
        quantity.setValue(item.getOpeningStock());
        quantity.setUnit(item.getStockUom());
        inventoryItem.setNetContent(quantity);
        return inventoryItem;
    }
}
