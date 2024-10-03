/*
 * Copyright © 2024, Ozone HIS <info@ozone-his.com>
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package com.ozonehis.fhir.erpnext.inventoryItem;

import com.fasterxml.jackson.core.type.TypeReference;
import com.ozonehis.camel.frappe.sdk.api.FrappeClient;
import com.ozonehis.camel.frappe.sdk.api.FrappeResponse;
import com.ozonehis.eip.model.erpnext.FrappeSingularDataWrapper;
import com.ozonehis.fhir.erpnext.model.Item;
import jakarta.validation.constraints.NotNull;
import java.io.IOException;
import java.util.Optional;
import org.apache.hc.core5.http.HttpStatus;
import org.openmrs.fhir.InventoryItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class InventoryItemService {

    private final FrappeClient client;
    private final InventoryItemMapper mapper;

    @Autowired
    public InventoryItemService(FrappeClient client, InventoryItemMapper mapper) {
        this.client = client;
        this.mapper = mapper;
    }

    public Optional<InventoryItem> getById(@NotNull String id) {
        try (FrappeResponse response =
                client.get("Item", id).withFields(new Item().getFields()).execute()) {
            if (response.code() == HttpStatus.SC_OK) {
                TypeReference<FrappeSingularDataWrapper<Item>> typeRef = new TypeReference<>() {};
                return Optional.ofNullable(
                        mapper.toFhir(response.returnAs(typeRef).getData()));
            }
            return Optional.empty();
        } catch (IOException e) {
            throw new RuntimeException("Error while fetching InventoryItem with id " + id, e);
        }
    }
}
