/*
 * Copyright © 2024, Ozone HIS <info@ozone-his.com>
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package com.ozonehis.fhir;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;

@SpringBootTest(classes = ERPNextFhirRestfulServletConfiguration.class)
class ERPNextFhirRestfulServletConfigurationTest {

    @MockBean
    ERPNextFhirRestfulServlet ERPNextFhirRestfulServlet;

    @Autowired
    ServletRegistrationBean<ERPNextFhirRestfulServlet> ERPNextFhirServletRegistrationBean;

    @Test
    @DisplayName("Should register ERPNextFhirRestfulServlet with URL mapping /erpnext/R4/*")
    void ERPNextFhirServletRegistration_shouldReturnERPNextFhirServletRegistrationBean() {
        // Assert
        assertThat(ERPNextFhirServletRegistrationBean).isNotNull();
        assertThat(ERPNextFhirServletRegistrationBean.getServlet()).isInstanceOf(ERPNextFhirRestfulServlet.class);
        assertThat(ERPNextFhirServletRegistrationBean.getUrlMappings()).containsExactly("/erpnext/R4/*");
        assertThat(ERPNextFhirServletRegistrationBean.getServletName()).isEqualTo("ERPNextFhirServlet");
    }
}
