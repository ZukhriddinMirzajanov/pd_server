package com.example.PD_server.repository;

import com.example.PD_server.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findByUserUsername(String username);
    Optional<Transaction> findByTransactionCode(String transactionCode);
}
