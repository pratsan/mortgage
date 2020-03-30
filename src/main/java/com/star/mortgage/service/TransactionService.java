package com.star.mortgage.service;

import org.springframework.stereotype.Service;

import com.star.mortgage.dto.UserTransactionDto;
import com.star.mortgage.exception.InvalidAccountException;
import com.star.mortgage.exception.NoLoanFoundException;
@Service
public interface TransactionService {
public UserTransactionDto getTransaction(String accountNumber) throws InvalidAccountException,NoLoanFoundException;
}
