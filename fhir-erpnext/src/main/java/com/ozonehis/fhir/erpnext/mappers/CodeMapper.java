/*
 * Copyright © 2024, Ozone HIS <info@ozone-his.com>
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package com.ozonehis.fhir.erpnext.mappers;

import com.ozonehis.fhir.erpnext.model.Item;
import org.hl7.fhir.r4.model.CodeableConcept;
import org.hl7.fhir.r4.model.Coding;
import org.springframework.stereotype.Component;

@Component
public class CodeMapper implements ToFhirMapping<Item, CodeableConcept> {

    // TODO: placeholder for the code system.
    private static final String CODE_SYSTEM = "http://erpnext.org/fhir/CodeSystem/Item";

    // TODO: Implement proper handling codes/codeableConcepts.
    @Override
    public CodeableConcept toFhir(Item item) {
        CodeableConcept codeableConcept = new CodeableConcept();
        Coding coding = new Coding();
        coding.setCode(item.getName());
        coding.setDisplay(item.getItemName());
        coding.setSystem(CODE_SYSTEM);
        codeableConcept.setText(item.getItemName());
        codeableConcept.addCoding(coding);
        return codeableConcept;
    }
}
