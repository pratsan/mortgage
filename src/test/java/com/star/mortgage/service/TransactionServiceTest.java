package com.star.mortgage.service;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

import com.star.mortgage.dto.TransactionDto;
import com.star.mortgage.dto.UserTransactionDto;
import com.star.mortgage.entity.Account;
import com.star.mortgage.entity.Transaction;
import com.star.mortgage.exception.InvalidAccountException;
import com.star.mortgage.exception.NoLoanFoundException;
import com.star.mortgage.repository.AccountRepository;
import com.star.mortgage.repository.TransactionRepository;

@RunWith(MockitoJUnitRunner.Silent.class)
public class TransactionServiceTest {
@InjectMocks
TransactionServiceImpl transactionServiceImpl;
@Mock

TransactionRepository transactionRepository;
@Mock
AccountRepository accountRepository;

Account account=new Account();
Transaction transaction=new Transaction();
TransactionDto transactionDto =new TransactionDto();
List<Transaction> transactions=new ArrayList<Transaction>();
List<Transaction> transactionList=new ArrayList<Transaction>();
UserTransactionDto userTransactionDto=new UserTransactionDto();
List<TransactionDto> list;
@Before
public void init()
{
	account.setAccountId(1L);
	account.setAccountNumber("123");
	account.setAccountType("saving");
	account.setBalance(20000.0);
	account.setCreatedDate(new Date());
	account.setCustomerId(1L);
	
	transaction.setAccountNumber("123");
	transaction.setAmount(5000.0);
	transaction.setDescription("loan");
	transaction.setTransactionDate(new Date());
	transaction.setTransactionId(1L);
	transaction.setTransactionType("saving");
	transactions.add(transaction);

	transactionDto.setAmount(5000.0);
	transactionDto.setDescription("loan");
	transactionDto.setTransactionDate(new Date());

	transactionDto.setTransactionType("saving");
	
	
	transactionDto.setAmount(5000.0);
	transactionDto.setDescription("loan");
	transactionDto.setTransactionDate(new Date());
	transactionDto.setTransactionType("debit");
		userTransactionDto.setAccountNumber("123");
	list=Arrays.asList(transactionDto);
		userTransactionDto.setTransactionDtoList(list);
  
}
@Test
public void displayLessThan10RecordTest() throws InvalidAccountException, NoLoanFoundException
{
	Mockito.when(accountRepository.findByAccountNumber("123")).thenReturn(Optional.of(account));
   Mockito.when(transactionRepository.findByAccountNumber("123")).thenReturn(transactions);
   
   UserTransactionDto actual=transactionServiceImpl.getTransaction("123");
   assertEquals(1, actual.getTransactionDtoList().size());

}


@Test
public void displayMoreThan10RecordTest() throws InvalidAccountException, NoLoanFoundException
{
	for(int index=0;index<11;index++)
	{
		transaction.setAccountNumber("123");
		transaction.setAmount(5000.0);
		transaction.setDescription("loan");
		transaction.setTransactionDate(new Date());
		transaction.setTransactionId(1L);
		transaction.setTransactionType("saving");
		transactions.add(transaction);
		transactionList.add(transaction);
	}
	
	Mockito.when(accountRepository.findByAccountNumber("123")).thenReturn(Optional.of(account));
   Mockito.when(transactionRepository.findByAccountNumber("123")).thenReturn(transactionList);
   
   UserTransactionDto actual=transactionServiceImpl.getTransaction("123");
   assertEquals(10, actual.getTransactionDtoList().size());

}

@Test(expected = InvalidAccountException.class)
public void InvalidAccountTest() throws InvalidAccountException, NoLoanFoundException
{
	Mockito.when(accountRepository.findByAccountNumber("123")).thenReturn(Optional.of(account));
	   Mockito.when(transactionRepository.findByAccountNumber("123")).thenReturn(transactions);
	   
	   UserTransactionDto actual=transactionServiceImpl.getTransaction("1234");
	 
}


@Test(expected = NoLoanFoundException.class)
public void NoLoanTest() throws InvalidAccountException, NoLoanFoundException
{
	Mockito.when(accountRepository.findByAccountNumber("123")).thenReturn(Optional.of(account));
	   Mockito.when(transactionRepository.findByAccountNumber("123")).thenReturn(transactionList);
	   
	   UserTransactionDto actual=transactionServiceImpl.getTransaction("123");
	 
}







}
