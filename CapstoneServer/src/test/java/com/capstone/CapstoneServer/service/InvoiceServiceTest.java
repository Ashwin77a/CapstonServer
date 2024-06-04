package com.capstone.CapstoneServer.service;

import com.capstone.CapstoneServer.dto.InvoiceDto;
import com.capstone.CapstoneServer.entities.Invoice;
import com.capstone.CapstoneServer.entities.User;
import com.capstone.CapstoneServer.entities.RepoInvoice;
import com.capstone.CapstoneServer.entities.RepoUser;
import com.capstone.CapstoneServer.services.InvoiceService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class InvoiceServiceTest {

    @Autowired
    private InvoiceService invoiceService;

    @Autowired
    private RepoInvoice invoiceRepository;

    @Autowired
    private RepoUser userRepository;

    private User user;

    @BeforeEach
    public void setUp() {
        user = new User();
        user.setUserName("testuser");
        user.setEmail("testuser@example.com");
        user.setPassword("password123");
        userRepository.save(user);
    }

    @Test
    void testAddInvoice() {
        InvoiceDto invoiceDto = InvoiceDto.builder()
                .clientName("test client")
                .amount(5000.00)
                .invoiceDate(new Date())
                .description("Test invoice")
                .userId(user.getUserId())
                .build();

        Invoice invoice = invoiceService.addInvoice(invoiceDto);
        assertNotNull(invoice);
        assertThat(invoice.getClientName()).isEqualTo("test client");

        List<Invoice> invoices = invoiceRepository.findByUserUserId(user.getUserId());
        assertThat(invoices).isNotEmpty();
    }

    @Test
    void testGetInvoicesByUserId() {
        InvoiceDto invoiceDto = InvoiceDto.builder()
                .clientName("test client")
                .amount(5000.00)
                .invoiceDate(new Date())
                .description("Test invoice")
                .userId(user.getUserId())
                .build();

        invoiceService.addInvoice(invoiceDto);

        List<InvoiceDto> invoiceDtos = invoiceService.getInvoicesByUserId(user.getUserId());
        assertNotNull(invoiceDtos);
        assertThat(invoiceDtos).isNotEmpty();
    }
}
