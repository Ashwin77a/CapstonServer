package com.capstone.CapstoneServer.entities;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneId;

 class EntityTest {
    private static final Logger log = LoggerFactory.getLogger(EntityTest.class);

    @Test
    void testInvoiceAllFeilds() {
        Invoice invoice = new Invoice();
        invoice.setInvoiceId(1);
        User user = new User();
        user.setUserId(1);
        invoice.setUser(user);
        invoice.setClientName("Test Client");
        invoice.setAmount(100.00);
        invoice.setDate(Date.valueOf("2024-05-13"));
        invoice.setDescription("Test Invoice");

        Assertions.assertThat(invoice)
                .hasFieldOrPropertyWithValue("invoiceId", 1)
                .hasFieldOrPropertyWithValue("clientName", "Test Client")
                .hasFieldOrPropertyWithValue("amount", 100.00)
                .hasFieldOrPropertyWithValue("date", Date.valueOf("2024-05-13"))
                .hasFieldOrPropertyWithValue("description", "Test Invoice");
    }
    @Test
     void testUserAllFields() {
        User user = new User();

        user.setUserId(1);
        user.setUserName("testuser");
        user.setEmail("test@example.com");
        user.setDateOfCreation(Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant()));
        user.setPassword("password123");

        // Use a single assertion object for better readability
        Assertions.assertThat(user)
                .hasFieldOrPropertyWithValue("userId", 1)
                .hasFieldOrPropertyWithValue("userName", "testuser")
                .hasFieldOrPropertyWithValue("email", "test@example.com")
                .hasFieldOrPropertyWithValue("dateOfCreation", user.getDateOfCreation())
                .hasFieldOrPropertyWithValue("password", "password123");
    }

}