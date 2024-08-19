/*
 * Copyright © 2024, Ozone HIS <info@ozone-his.com>
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package com.ozonehis.fhir.erpnext.mappers;

import org.hl7.fhir.instance.model.api.IBase;

/**
 * Mapping from ERPNext Object to FHIR Resource
 *
 * @param <E> ERPNext Object
 * @param <R> FHIR Resource
 */
public interface ToFhirMapping<E, R extends IBase> {

    /**
     * Maps an ERPNext Object to a FHIR Resource
     *
     * @param document ERPNext Object
     * @return FHIR Resource
     */
    R toFhir(E document);
}
