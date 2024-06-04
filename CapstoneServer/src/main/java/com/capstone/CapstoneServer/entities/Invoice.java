package com.capstone.CapstoneServer.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import java.util.Date;

@Entity
public class Invoice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int invoiceId;

    @ManyToOne
    @JoinColumn(name = "userId", nullable = false,referencedColumnName = "userId")
    private User user;
    

    @NotBlank(message = "Please provide client name")
    private String clientName;

    @Min(value = 3000, message = "Amount should be greater than 3000")
    private double amount;

    private Date date;

    private String description;

    // Default constructor
    public Invoice() {
    }

    // Constructor with parameters
    public Invoice(int invoiceId, User user, String clientName, double amount, Date date, String description) {
        this.invoiceId = invoiceId;
        this.user = user;
        this.clientName = clientName;
        this.amount = amount;
        this.date = date;
        this.description = description;
    }

    // Builder pattern
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private int invoiceId;
        private User user;
        private String clientName;
        private double amount;
        private Date date;
        private String description;

        public Builder() {
        }

        public Builder invoiceId(int invoiceId) {
            this.invoiceId = invoiceId;
            return this;
        }

        public Builder user(User user) {
            this.user = user;
            return this;
        }

        public Builder clientName(String clientName) {
            this.clientName = clientName;
            return this;
        }

        public Builder amount(double amount) {
            this.amount = amount;
            return this;
        }

        public Builder date(Date date) {
            this.date = date;
            return this;
        }

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public Invoice build() {
            return new Invoice(invoiceId, user, clientName, amount, date, description);
        }
    }

    // Getters and setters
    public int getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(int invoiceId) {
        this.invoiceId = invoiceId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
