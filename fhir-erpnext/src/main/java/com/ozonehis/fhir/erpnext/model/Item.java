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
import java.util.Date;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Item extends DocType {

    @JsonProperty("item_code")
    private String itemCode;

    @JsonProperty("item_name")
    private String itemName;

    @JsonProperty("description")
    private String description;

    @JsonProperty("item_group")
    private String itemGroup;

    @JsonFormat(shape = JsonFormat.Shape.NUMBER_INT)
    @JsonProperty("is_stock_item")
    private boolean isStockItem;

    @JsonFormat(shape = JsonFormat.Shape.NUMBER_INT)
    @JsonProperty("is_purchase_item")
    private boolean isPurchaseItem;

    @JsonFormat(shape = JsonFormat.Shape.NUMBER_INT)
    @JsonProperty("is_sales_item")
    private boolean isSalesItem;

    @JsonProperty("stock_uom")
    private String stockUom;

    @JsonProperty("opening_stock")
    private int openingStock;

    @Override
    public String getDoctype() {
        return "Item";
    }

    public Date getCreationDate() {
        return getDate(creation);
    }

    public Date getModifiedDate() {
        return getDate(modified);
    }
}
