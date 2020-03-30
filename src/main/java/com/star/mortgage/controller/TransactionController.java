package com.star.mortgage.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.star.mortgage.dto.UserTransactionDto;
import com.star.mortgage.exception.InvalidAccountException;
import com.star.mortgage.exception.NoLoanFoundException;
import com.star.mortgage.service.TransactionService;

@RestController

@RequestMapping("/transactions")
public class TransactionController {
	@Autowired
	TransactionService transactionService;

	@GetMapping("/{accountNumber}")
	public ResponseEntity<UserTransactionDto> getLastTransaction(@PathVariable("accountNumber") String accountNumber) throws InvalidAccountException, NoLoanFoundException {
		return new ResponseEntity<>(transactionService.getTransaction(accountNumber), HttpStatus.ACCEPTED);
	}
}
