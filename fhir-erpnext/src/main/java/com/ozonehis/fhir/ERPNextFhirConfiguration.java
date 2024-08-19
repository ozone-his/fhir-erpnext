/*
 * Copyright © 2024, Ozone HIS <info@ozone-his.com>
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package com.ozonehis.fhir;

import com.ozonehis.camel.frappe.sdk.FrappeClientBuilder;
import com.ozonehis.camel.frappe.sdk.api.FrappeClient;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ERPNextFhirConfiguration {

    @Value("${erpnext.server-url}")
    private String erpnextServerUrl;

    @Value("${erpnext.username}")
    private String erpnextUsername;

    @Value("${erpnext.password}")
    private String erpnextPassword;

    private void validateERPNextConfiguration() {
        if (StringUtils.isAnyEmpty(erpnextServerUrl, erpnextUsername, erpnextPassword)
                || StringUtils.isAnyBlank(erpnextServerUrl, erpnextUsername, erpnextPassword)) {
            throw new IllegalStateException("ERPNext server URL, username and password must be provided");
        }
    }

    // TODO: Read basic auth credentials from headers and pass them to the FrappeClient
    @Bean
    FrappeClient frappeClient() {
        this.validateERPNextConfiguration();
        return FrappeClientBuilder.newClient(erpnextServerUrl, erpnextUsername, erpnextPassword)
                .build();
    }
}
