/*
 * Copyright © 2024, Ozone HIS <info@ozone-his.com>
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package com.ozonehis.fhir;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ERPNextFhirRestfulServletConfiguration {

    private final String ERPNEXT_FHIR_SERVLET_NAME = "ERPNextFhirServlet";

    @Autowired
    private ERPNextFhirRestfulServlet ERPNextFhirRestfulServlet;

    @Bean
    public ServletRegistrationBean<ERPNextFhirRestfulServlet> ERPNextFhirServletRegistration() {
        var servletRegistrationBean = new ServletRegistrationBean<>(ERPNextFhirRestfulServlet, "/erpnext/R4/*");
        servletRegistrationBean.setName(ERPNEXT_FHIR_SERVLET_NAME);
        servletRegistrationBean.setLoadOnStartup(1);
        return servletRegistrationBean;
    }
}
