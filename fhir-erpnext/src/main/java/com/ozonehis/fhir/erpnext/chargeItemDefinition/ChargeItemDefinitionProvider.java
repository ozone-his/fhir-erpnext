/*
 * Copyright © 2024, Ozone HIS <info@ozone-his.com>
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package com.ozonehis.fhir.erpnext.chargeItemDefinition;

import ca.uhn.fhir.rest.annotation.IdParam;
import ca.uhn.fhir.rest.annotation.Read;
import ca.uhn.fhir.rest.server.IResourceProvider;
import ca.uhn.fhir.rest.server.exceptions.ResourceNotFoundException;
import com.ozonehis.fhir.annotations.ERPNextFhirProvider;
import jakarta.annotation.Nonnull;
import org.hl7.fhir.instance.model.api.IBaseResource;
import org.hl7.fhir.r4.model.ChargeItemDefinition;
import org.hl7.fhir.r4.model.IdType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@ERPNextFhirProvider
public class ChargeItemDefinitionProvider implements IResourceProvider {

    private final ChargeItemDefinitionService chargeItemDefinitionService;

    @Autowired
    public ChargeItemDefinitionProvider(ChargeItemDefinitionService chargeItemDefinitionService) {
        this.chargeItemDefinitionService = chargeItemDefinitionService;
    }

    @Override
    public Class<? extends IBaseResource> getResourceType() {
        return ChargeItemDefinition.class;
    }

    @Read
    @SuppressWarnings("unused")
    public ChargeItemDefinition getChargeItemDefinitionById(@IdParam @Nonnull IdType id) {
        return chargeItemDefinitionService
                .getById(id.getIdPart())
                .orElseThrow(() ->
                        new ResourceNotFoundException("ChargeItemDefinition with id " + id.getIdPart() + " not found"));
    }
}
