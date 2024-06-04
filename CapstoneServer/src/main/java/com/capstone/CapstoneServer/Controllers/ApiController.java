package com.capstone.CapstoneServer.Controllers;

import com.capstone.CapstoneServer.dto.InvoiceDto;
import com.capstone.CapstoneServer.services.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/invoices")
public class ApiController {

    @Autowired
    private InvoiceService invoiceService;

    @PostMapping
    public ResponseEntity<Void> addInvoice(@RequestBody InvoiceDto invoiceDto) {
        invoiceService.addInvoice(invoiceDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<InvoiceDto>> getInvoicesByUserId(@PathVariable int userId) {
        List<InvoiceDto> invoiceDtos = invoiceService.getInvoicesByUserId(userId);
        if (invoiceDtos.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(invoiceDtos);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateInvoice(@PathVariable int id, @RequestBody InvoiceDto invoiceDto) {
        invoiceService.updateInvoice(id, invoiceDto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Integer> deleteInvoice(@PathVariable int id) {
        invoiceService.deleteInvoice(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(id);
    }

    @GetMapping("/{invoiceId}")
    public ResponseEntity<InvoiceDto> getInvoiceByInvoiceId(@PathVariable int invoiceId) {
        InvoiceDto invoiceDto = invoiceService.getInvoiceByInvoiceId(invoiceId);
        return ResponseEntity.ok(invoiceDto);
    }
}
