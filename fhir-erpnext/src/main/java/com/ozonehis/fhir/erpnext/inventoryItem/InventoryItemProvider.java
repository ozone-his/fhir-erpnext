/*
 * Copyright © 2024, Ozone HIS <info@ozone-his.com>
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package com.ozonehis.fhir.erpnext.inventoryItem;

import ca.uhn.fhir.rest.annotation.IdParam;
import ca.uhn.fhir.rest.annotation.Read;
import ca.uhn.fhir.rest.server.IResourceProvider;
import ca.uhn.fhir.rest.server.exceptions.ResourceNotFoundException;
import com.ozonehis.fhir.InventoryItem;
import com.ozonehis.fhir.annotations.ERPNextFhirProvider;
import jakarta.annotation.Nonnull;
import org.hl7.fhir.instance.model.api.IBaseResource;
import org.hl7.fhir.r4.model.IdType;
import org.springframework.beans.factory.annotation.Autowired;

@ERPNextFhirProvider
@SuppressWarnings("unused")
public class InventoryItemProvider implements IResourceProvider {

    private final InventoryItemService inventoryItemService;

    @Autowired
    public InventoryItemProvider(InventoryItemService inventoryItemService) {
        this.inventoryItemService = inventoryItemService;
    }

    @Override
    public Class<? extends IBaseResource> getResourceType() {
        return InventoryItem.class;
    }

    @Read
    public InventoryItem getInventoryItemById(@IdParam @Nonnull IdType id) {
        return inventoryItemService
                .getById(id.getIdPart())
                .orElseThrow(
                        () -> new ResourceNotFoundException("InventoryItem with id " + id.getIdPart() + " not found"));
    }
}
