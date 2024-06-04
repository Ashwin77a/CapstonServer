package com.capstone.CapstoneServer.entities;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RepoInvoice extends JpaRepository<Invoice, Integer> {
    List<Invoice> findByUserUserId(int userId);


}