package com.star.mortgage.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.star.mortgage.entity.Transaction;
@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
public List<Transaction> findByAccountNumber(String accountNumber);
}
