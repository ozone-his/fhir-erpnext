/*
 * Copyright © 2024, Ozone HIS <info@ozone-his.com>
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package com.ozonehis.fhir.erpnext.model;

import jakarta.validation.constraints.NotNull;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * This interface is used to mark all the ERPNext documents.
 */
public interface ERPNextDocument {

    /**
     * Returns the doctype of the ERPNext Document
     *
     * @return doctype
     */
    String getDoctype();

    /**
     * Returns the fields of the ERPNext Document
     *
     * @return fields
     */
    List<String> getFields();

    /**
     * Parses a date string to a @{java.util.Date}
     *
     * @param date String date
     * @return date @{java.util.Date}
     */
    default Date getDate(@NotNull String date) {
        try {
            var dateFormat = new SimpleDateFormat("dd-M-yyyy hh:mm:ss a");
            return dateFormat.parse(date);
        } catch (Exception e) {
            return null;
        }
    }
}
