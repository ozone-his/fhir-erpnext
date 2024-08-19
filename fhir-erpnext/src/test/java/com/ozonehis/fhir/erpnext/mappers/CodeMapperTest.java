/*
 * Copyright © 2024, Ozone HIS <info@ozone-his.com>
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package com.ozonehis.fhir.erpnext.mappers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.ozonehis.fhir.erpnext.model.Item;
import org.hl7.fhir.r4.model.CodeableConcept;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = {CodeMapper.class})
class CodeMapperTest {

    @Autowired
    private CodeMapper codeMapper;

    @Test
    @DisplayName("toFhir should map ERPNext Item to CodeableConcept")
    void toFhir_shouldMapCodeableConceptToERPNextItem() {
        // Arrange
        Item item = new Item();
        item.setName("item1");
        item.setItemCode("code1");
        item.setItemName("Code 1");
        item.setDescription("Code 1 description");

        // Act
        CodeableConcept codeableConcept = codeMapper.toFhir(item);

        // Assert
        assertNotNull(codeableConcept);
        assertEquals(1, codeableConcept.getCoding().size());
        assertEquals("item1", codeableConcept.getCodingFirstRep().getCode());
        assertEquals("Code 1", codeableConcept.getText());
    }
}
