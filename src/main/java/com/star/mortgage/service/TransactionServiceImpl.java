package com.star.mortgage.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.star.mortgage.dto.TransactionDto;
import com.star.mortgage.dto.UserTransactionDto;
import com.star.mortgage.entity.Account;
import com.star.mortgage.entity.Transaction;
import com.star.mortgage.exception.InvalidAccountException;
import com.star.mortgage.exception.NoLoanFoundException;
import com.star.mortgage.repository.AccountRepository;
import com.star.mortgage.repository.TransactionRepository;
import com.star.mortgage.utility.ErrorConstant;
@Service
public class TransactionServiceImpl  implements TransactionService{
	@Autowired
	TransactionRepository transactionRepository;
	@Autowired
	AccountRepository accountRepository;

	@Override
	public UserTransactionDto getTransaction(String accountNumber) throws InvalidAccountException, NoLoanFoundException {
		UserTransactionDto userTransactionDto;
		Optional<Account> accountDetail=accountRepository.findByAccountNumber(accountNumber);
		if(!accountDetail.isPresent())
		{
			throw new InvalidAccountException(ErrorConstant.INVALID_ACCOUNT);
		}
		List<Transaction> transactions=transactionRepository.findByAccountNumber(accountNumber);
		if(transactions.isEmpty())
		{
			throw new NoLoanFoundException(ErrorConstant.NO_LOAN_RECORD);
		}
		List<TransactionDto> transactionDtoList; 
		if(transactions.size()<=10) {
		transactionDtoList=transactions.stream().map(transaction->{
				TransactionDto transactionDto=new TransactionDto();
				BeanUtils.copyProperties(transaction, transactionDto);
				return transactionDto;
			}).collect(Collectors.toList());
		
			Collections.reverse(transactionDtoList);
		userTransactionDto=new UserTransactionDto();
		userTransactionDto.setAccountNumber(accountNumber);
		userTransactionDto.setTransactionDtoList(transactionDtoList);
		return userTransactionDto;
	}
		else {
		
			transactionDtoList=new ArrayList<>();
			int size=transactions.size();
			for(int index=transactions.size();index>(size-10);index--)
			{
				
				TransactionDto transactionDto=new TransactionDto();
				
				
				BeanUtils.copyProperties(transactions.get(index-1), transactionDto);
				transactionDtoList.add(transactionDto);
			}
			userTransactionDto=new UserTransactionDto();
			userTransactionDto.setAccountNumber(accountNumber);
			userTransactionDto.setTransactionDtoList(transactionDtoList);
			return userTransactionDto;
		}
	

}}
