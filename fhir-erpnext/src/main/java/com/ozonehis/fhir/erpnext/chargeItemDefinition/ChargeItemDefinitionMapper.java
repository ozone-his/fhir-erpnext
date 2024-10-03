/*
 * Copyright © 2024, Ozone HIS <info@ozone-his.com>
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package com.ozonehis.fhir.erpnext.chargeItemDefinition;

import com.ozonehis.fhir.erpnext.mappers.CodeMapper;
import com.ozonehis.fhir.erpnext.mappers.ToFhirMapping;
import com.ozonehis.fhir.erpnext.model.Item;
import org.hl7.fhir.r4.model.ChargeItemDefinition;
import org.hl7.fhir.r4.model.Enumerations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ChargeItemDefinitionMapper implements ToFhirMapping<Item, ChargeItemDefinition> {

    private final CodeMapper codeMapper;

    @Autowired
    public ChargeItemDefinitionMapper(CodeMapper codeMapper) {
        this.codeMapper = codeMapper;
    }

    @Override
    public ChargeItemDefinition toFhir(Item item) {
        ChargeItemDefinition chargeItemDefinition = new ChargeItemDefinition();
        if (item == null) {
            return null;
        }
        chargeItemDefinition.setId(item.getName());
        chargeItemDefinition.setName(item.getItemName());
        chargeItemDefinition.setDescription(item.getDescription());

        // TODO: Implement proper handling codes.
        if (item.getItemCode() != null) {
            chargeItemDefinition.setCode(codeMapper.toFhir(item));
        }

        chargeItemDefinition.setPublisher(item.getOwner());
        chargeItemDefinition.setDate(item.getModifiedDate());

        // https://build.fhir.org/codesystem-publication-status.html
        if (item.isDisabled()) chargeItemDefinition.setStatus(Enumerations.PublicationStatus.RETIRED);
        else chargeItemDefinition.setStatus(Enumerations.PublicationStatus.ACTIVE);

        // TODO: Add PriceComponent.

        return chargeItemDefinition;
    }
}
