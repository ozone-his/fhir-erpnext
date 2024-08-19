package com.ozonehis.fhir;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

@SpringBootTest
class ApplicationTest {

    @Autowired
    ApplicationContext context;

    @Test
    @DisplayName("Should load ApplicationContext")
    void contextLoads() {
        assertNotNull(context);
    }
}
