package com.capstone.CapstoneServer.services;

import com.capstone.CapstoneServer.entities.Invoice;
import com.capstone.CapstoneServer.entities.RepoInvoice;
import com.capstone.CapstoneServer.entities.User;
import com.capstone.CapstoneServer.entities.RepoUser;
import com.capstone.CapstoneServer.exception.InvoiceNotFoundException;
import com.capstone.CapstoneServer.exception.UserNotFoundException;
import com.capstone.CapstoneServer.dto.InvoiceDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class InvoiceService {

    private static final Logger log = LoggerFactory.getLogger(InvoiceService.class);

    @Autowired
    private RepoInvoice invoiceRepository;

    @Autowired
    private RepoUser userRepository;

    // Add Invoice
    public Invoice addInvoice(InvoiceDto invoiceDto) {
        // Fetch user object before creating invoice
        int userId = invoiceDto.getUserId();
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isEmpty()) {
            throw new UserNotFoundException(userId);
        }
        User user = userOptional.get();

        // Create and save Invoice
        Invoice invoice = new Invoice(0, user, invoiceDto.getClientName(), invoiceDto.getAmount(), invoiceDto.getInvoiceDate(), invoiceDto.getDescription());
        log.info("Invoice added-------------------: ");
        return invoiceRepository.save(invoice);
    }

    // Get Invoices by User ID
    public List<InvoiceDto> getInvoicesByUserId(int userId) {
        log.info("---------------getInvoicesByUserId " + userId);
        List<Invoice> invoices = invoiceRepository.findByUserUserId(userId);
        log.info("@invoice return check");
        if (invoices.isEmpty()) {
            return new ArrayList<>();
        } else {
            List<InvoiceDto> invoiceDtos = new ArrayList<>();
            for (Invoice invoice : invoices) {
                InvoiceDto invoiceDto = InvoiceDto.builder()
                        .invoiceId(invoice.getInvoiceId())
                        .clientName(invoice.getClientName())
                        .amount(invoice.getAmount())
                        .invoiceDate(invoice.getDate())
                        .description(invoice.getDescription())
                        .userId(invoice.getUser() != null ? invoice.getUser().getUserId() : null)
                        .build();
                invoiceDtos.add(invoiceDto);
            }
            return invoiceDtos;
        }
    }

    // Update Invoice
    public Invoice updateInvoice(int id, InvoiceDto invoiceDto) {
        Optional<Invoice> existingInvoiceOptional = invoiceRepository.findById(id);
        if (existingInvoiceOptional.isPresent()) {
            Invoice existingInvoice = existingInvoiceOptional.get();
            existingInvoice.setClientName(invoiceDto.getClientName());
            existingInvoice.setAmount(invoiceDto.getAmount());
            existingInvoice.setDate(invoiceDto.getInvoiceDate());
            existingInvoice.setDescription(invoiceDto.getDescription());
            return invoiceRepository.save(existingInvoice);
        } else {
            throw new InvoiceNotFoundException(id);
        }
    }

    // Delete Invoice
    public void deleteInvoice(int id) {
        if (invoiceRepository.existsById(id)) {
            invoiceRepository.deleteById(id);
            log.info("Invoice deleted: " + id);
        } else {
            throw new InvoiceNotFoundException(id);
        }
    }

    // Get Invoice by Invoice ID
    public InvoiceDto getInvoiceByInvoiceId(int invoiceId) {
        log.info("---------------getInvoicesByInvoiceId " + invoiceId);
        Optional<Invoice> optionalInvoice = invoiceRepository.findById(invoiceId);
        if (optionalInvoice.isEmpty()) {
            throw new InvoiceNotFoundException(invoiceId);
        }
        Invoice invoice = optionalInvoice.get();
        return InvoiceDto.builder()
                .invoiceId(invoice.getInvoiceId())
                .invoiceDate(invoice.getDate())
                .clientName(invoice.getClientName())
                .amount(invoice.getAmount())
                .description(invoice.getDescription())
                .build();
    }
}
