package com.capstone.CapstoneServer.dto;

import java.util.Date;

import jakarta.validation.constraints.*;

public class InvoiceDto {
	@NotNull(message = "Please provide user ID")
    private Integer userId;
    private int invoiceId;
    
    @NotBlank(message = "Please provide client name")
    @Size(min = 5, message = "Client name must have at least 5 characters")
    private String clientName;
    
    @Positive(message = "Amount must be positive")
    @Min(value = 3000, message = "Amount should be greater than or equal to 3000")
    
    private double amount;
    
    @NotNull(message = "Please provide invoice date")
    @PastOrPresent(message = "Invoice date must be in the past or current date")
    private Date invoiceDate;
    
    private String description;
    
     // Assuming this field exists

    // Constructor
    public InvoiceDto() {
    }

    // Getters and Setters
    public int getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(int invoiceId) {
        this.invoiceId = invoiceId;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Date getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(Date invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    // Builder pattern
    public static InvoiceDtoBuilder builder() {
        return new InvoiceDtoBuilder();
    }

    public static class InvoiceDtoBuilder {
        private int invoiceId;
        private String clientName;
        private double amount;
        private Date invoiceDate;
        private String description;
        private Integer userId;

        public InvoiceDtoBuilder invoiceId(int invoiceId) {
            this.invoiceId = invoiceId;
            return this;
        }

        public InvoiceDtoBuilder clientName(String clientName) {
            this.clientName = clientName;
            return this;
        }

        public InvoiceDtoBuilder amount(double amount) {
            this.amount = amount;
            return this;
        }

        public InvoiceDtoBuilder invoiceDate(Date invoiceDate) {
            this.invoiceDate = invoiceDate;
            return this;
        }

        public InvoiceDtoBuilder description(String description) {
            this.description = description;
            return this;
        }

        public InvoiceDtoBuilder userId(Integer userId) {
            this.userId = userId;
            return this;
        }

        public InvoiceDto build() {
            InvoiceDto invoiceDto = new InvoiceDto();
            invoiceDto.setInvoiceId(this.invoiceId);
            invoiceDto.setClientName(this.clientName);
            invoiceDto.setAmount(this.amount);
            invoiceDto.setInvoiceDate(this.invoiceDate);
            invoiceDto.setDescription(this.description);
            invoiceDto.setUserId(this.userId);
            return invoiceDto;
        }
    }
}
