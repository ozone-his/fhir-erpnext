/*
 * Copyright © 2024, Ozone HIS <info@ozone-his.com>
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package com.ozonehis.fhir.erpnext.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serial;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public abstract class DocType implements Serializable, ERPNextDocument {

    @Serial
    private static final long serialVersionUID = 1L;

    @JsonProperty("name")
    String name;

    @JsonProperty("doctype")
    String doctype;

    @JsonProperty("creation")
    String creation;

    @JsonProperty("modified")
    String modified;

    @JsonProperty("modified_by")
    String modifiedBy;

    @JsonProperty("disabled")
    @JsonFormat(shape = JsonFormat.Shape.NUMBER_INT)
    boolean disabled;

    @JsonProperty("docstatus")
    boolean docStatus;

    @JsonProperty("owner")
    String owner;

    public List<String> getFields() {
        List<String> jsonPropertyFields = new ArrayList<>();
        for (Field field : getClass().getDeclaredFields()) {
            if (field.isAnnotationPresent(JsonProperty.class)) {
                JsonProperty jsonProperty = field.getAnnotation(JsonProperty.class);
                jsonPropertyFields.add(jsonProperty.value());
            }
        }
        return jsonPropertyFields;
    }
}
