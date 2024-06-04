package com.capstone.CapstoneServer.exception;

public class InvoiceNotFoundException extends RuntimeException{
    public InvoiceNotFoundException(int id) {
        super("Invoice with ID: " + id + " not found");
    }
}