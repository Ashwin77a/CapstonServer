package com.capstone.CapstoneServer.entities;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DataJpaTest
class RepoInvoiceTest {

    private static final Logger log = LoggerFactory.getLogger(RepoInvoiceTest.class);

    @Autowired
    private RepoInvoice invoiceRepository;

    @Autowired
    private RepoUser userRepository;

    @Test
    @Order(1)
    @Rollback(value = false)
    void saveInvoiceTest() {

        User user = new User();
        user.setUserName("testuser2");
        user.setEmail("testuser@example.com2");
        user.setPassword("password2");
        user.setDateOfCreation(new Date());
        userRepository.save(user);

        Invoice invoice = Invoice.builder()
                .clientName("test client2")
                .user(user)
                .amount(4000.00) // Set amount to a value greater than 3000
                .date(new Date())
                .description("Test Invoice2")
                .build();

        invoiceRepository.save(invoice);

        int invoiceId = invoice.getInvoiceId();
        log.info("Invoice ID: " + invoiceId);
        assertThat(invoice.getUser().getUserId()).isGreaterThan(0);
    }

    @Test
    @Order(2)
    public void findInvoiceByUserIdTest() {
        User user9 = new User();
        user9.setUserName("testuser99");
        user9.setEmail("testuser@example.com");
        user9.setPassword("password");
        user9.setDateOfCreation(new Date());
        userRepository.save(user9);

        Invoice invoice = Invoice.builder()
                .clientName("test client99")
                .user(user9)
                .amount(3400.00)
                .date(new Date())
                .description("Test Invoice99")
                .build();

        invoiceRepository.save(invoice);
        User user = userRepository.findByUserName("testuser99");
        assertThat(user).isNotNull();

        List<Invoice> invoices = invoiceRepository.findByUserUserId(user.getUserId());
        assertThat(invoices.size()).isGreaterThan(0);
    }

    @Test
    @Order(3)
    public void findInvoiceByInvoiceIdTest() {
        User user = userRepository.findById(1).orElse(null);
        assertThat(user).isNotNull();

        Optional<Invoice> invoices = invoiceRepository.findById(1);
        assertThat(invoices.isPresent()).isEqualTo(true);
    }

    @Test
    @Order(4)
    public void editInvoiceByInvoiceIdTest() {

        Invoice invoice = invoiceRepository.findById(1).orElse(null);
        assertThat(invoice).isNotNull();

        invoice.setClientName("updated client");
        invoiceRepository.save(invoice);

        Invoice updatedInvoice = invoiceRepository.findById(invoice.getInvoiceId()).orElse(null);

        assertThat(updatedInvoice.getClientName()).isEqualTo("updated client");
    }

    @Test
    @Order(5)
    public void deleteInvoiceByInvoiceIdTest() {

        invoiceRepository.deleteById(1);

        Invoice deletedInvoice = invoiceRepository.findById(1).orElse(null);
        assertThat(deletedInvoice).isNull();
    }

    @Test
    @Order(6)
    public void findInvoiceByNonExistentUserIdTest() {
        User user = userRepository.findById(999).orElse(null);
        assertThat(user).isNull();

        List<Invoice> invoices = invoiceRepository.findByUserUserId(999);
        assertThat(invoices).isEmpty();
    }

    @Test
    @Order(7)
    public void findInvoiceByNonExistentInvoiceIdTest() {
        Optional<Invoice> invoice = invoiceRepository.findById(999);
        assertThat(invoice.isPresent()).isFalse();
    }

    @Test
    @Order(8)
    public void editNonExistentInvoiceByInvoiceIdTest() {
        Invoice invoice = invoiceRepository.findById(-1).orElse(null);
        assertThat(invoice).isNull();

        Invoice updatedInvoice = invoiceRepository.findById(-1).orElse(null);
        assertThat(updatedInvoice).isNull();
    }

    @Test
    @Order(9)
    public void deleteNonExistentInvoiceByInvoiceIdTest() {
        Invoice invoice = invoiceRepository.findById(999).orElse(null);
        assertThat(invoice).isNull();

        if (invoice != null) {
            invoiceRepository.deleteById(999);
        }

        Invoice deletedInvoice = invoiceRepository.findById(999).orElse(null);
        assertThat(deletedInvoice).isNull();
    }
}
