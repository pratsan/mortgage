package com.star.mortgage.controller;


import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.star.mortgage.dto.TransactionDto;
import com.star.mortgage.dto.UserTransactionDto;
import com.star.mortgage.exception.InvalidAccountException;
import com.star.mortgage.exception.NoLoanFoundException;
import com.star.mortgage.service.TransactionServiceImpl;

@RunWith(MockitoJUnitRunner.Silent.class)
public class TransactionControllerTest {
@InjectMocks
TransactionController transactionController;
TransactionDto transactionDto=new TransactionDto();


@Mock
TransactionServiceImpl transactionServiceImpl;
UserTransactionDto userTransactionDto=new UserTransactionDto();
List<TransactionDto> list;
@Before
public void  init()
{
transactionDto.setAmount(5000.0);
transactionDto.setDescription("loan");
transactionDto.setTransactionDate(new Date());
transactionDto.setTransactionType("debit");
	userTransactionDto.setAccountNumber("123");
list=Arrays.asList(transactionDto);
	userTransactionDto.setTransactionDtoList(list);
}
@Test
public void testController() throws InvalidAccountException, NoLoanFoundException
{
	Mockito.when(transactionServiceImpl.getTransaction("123")).thenReturn(userTransactionDto);
	ResponseEntity<UserTransactionDto> actual=transactionController.getLastTransaction("123");
	assertEquals( HttpStatus.ACCEPTED, actual.getStatusCode());
}

}
