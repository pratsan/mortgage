package com.star.mortgage.service;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;
import java.util.ArrayList;
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

import com.star.mortgage.dto.PaymentDto;
import com.star.mortgage.dto.ResponseDto;
import com.star.mortgage.entity.Account;
import com.star.mortgage.entity.Customer;
import com.star.mortgage.entity.Mortgage;
import com.star.mortgage.exception.CustomerException;
import com.star.mortgage.repository.AccountRepository;
import com.star.mortgage.repository.CustomerRepository;
import com.star.mortgage.repository.MortgageRepository;
import com.star.mortgage.repository.TransactionRepository;
import com.star.mortgage.utility.ErrorConstant;

@RunWith(MockitoJUnitRunner.Silent.class)
public class PaymentServiceTest {
@InjectMocks
PaymentServiceImpl paymentServiceImpl;


	
	@Mock
	AccountRepository accountRepository;
	@Mock
	TransactionRepository transactionRepository;
	@Mock
	CustomerRepository customerRepository;
	@Mock
	MortgageRepository mortgageRepository;
	PaymentDto paymentDto=new PaymentDto();
	Customer customer=new Customer();
	Account account=new Account();
	Account mortgageAccount=new Account();
	Mortgage mortgage=new Mortgage();
	
	List<Account> accountList=new ArrayList<>();
	
	@Before
	public void init()
	{
		paymentDto.setAccountNumber("123");
		paymentDto.setCustomerId(1L);
		customer.setCustomerId(1L);
		customer.setDob(LocalDate.now());
		customer.setEmail("pal@gmail");
		customer.setFirstName("prateek");
		customer.setLastName("pal");
		customer.setLoginId("lol");
		customer.setOccupation("job");
		customer.setPanCard("A123");
		customer.setPassword("pal");
		customer.setSalary(50000.0);
		
		account.setAccountId(1L);
		account.setAccountNumber("123");
		account.setAccountType("saving");
		account.setBalance(20000.0);
		account.setCreatedDate(new Date());
		account.setCustomerId(1L);
		
		
		mortgageAccount.setAccountId(1L);
		mortgageAccount.setAccountNumber("900");
		mortgageAccount.setAccountType("mortgage");
		mortgageAccount.setBalance(20000.0);
		mortgageAccount.setCreatedDate(new Date());
		mortgageAccount.setCustomerId(1L);
		
		mortgage.setCustomerId(1L);
		mortgage.setDeposit(2000.0);
		mortgage.setEmi(1000.0);
		mortgage.setEmployementType("job");
		mortgage.setMortgageId(1L);
		mortgage.setPropertyCost(5000000.0);
		mortgage.setPropertyType("home");
		mortgage.setTenure(10.0);
		
		accountList.add(account);
		accountList.add(mortgageAccount);
	}
	
	@Test
	public void paymentTest() throws CustomerException
	{
		Mockito.when(customerRepository.findById(1L)).thenReturn(Optional.of(customer));
		Mockito.when(accountRepository.findByAccountNumber("123")).thenReturn(Optional.of(account));
		Mockito.when(mortgageRepository.findByCustomerId(1L)).thenReturn(Optional.of(mortgage));
		Mockito.when(accountRepository.findByCustomerId(1L)).thenReturn(accountList);
		
		ResponseDto actual=paymentServiceImpl.makePayment(paymentDto);
		assertEquals(ErrorConstant.PAID_SUCCESS_CODE, actual.getStatusCode());
	}
	
	@Test(expected = CustomerException.class)
	public void CustomerNotFoundTest() throws CustomerException
	{
		Mockito.when(customerRepository.findById(2L)).thenReturn(Optional.of(customer));
		Mockito.when(accountRepository.findByAccountNumber("123")).thenReturn(Optional.of(account));
		Mockito.when(mortgageRepository.findByCustomerId(1L)).thenReturn(Optional.of(mortgage));
		Mockito.when(accountRepository.findByCustomerId(1L)).thenReturn(accountList);
		
		ResponseDto actual=paymentServiceImpl.makePayment(paymentDto);
	}
	
	
	@Test(expected = CustomerException.class)
	public void MortgageNotFoundTest() throws CustomerException
	{
		Mockito.when(customerRepository.findById(1L)).thenReturn(Optional.of(customer));
		Mockito.when(accountRepository.findByAccountNumber("123")).thenReturn(Optional.of(account));
		Mockito.when(mortgageRepository.findByCustomerId(6L)).thenReturn(Optional.of(mortgage));
		Mockito.when(accountRepository.findByCustomerId(1L)).thenReturn(accountList);
		
		ResponseDto actual=paymentServiceImpl.makePayment(paymentDto);
	}
	
	@Test(expected = CustomerException.class)
	public void MortgageAccountDetailNotFoundTest() throws CustomerException
	{
		account.setAccountType("somethinf");
		List<Account> mortageaccount=new ArrayList<Account>();
		mortageaccount.add(account);
		Mockito.when(customerRepository.findById(1L)).thenReturn(Optional.of(customer));
		Mockito.when(accountRepository.findByAccountNumber("123")).thenReturn(Optional.of(account));
		Mockito.when(mortgageRepository.findByCustomerId(1L)).thenReturn(Optional.of(mortgage));
		Mockito.when(accountRepository.findByCustomerId(1L)).thenReturn(mortageaccount);
		
		ResponseDto actual=paymentServiceImpl.makePayment(paymentDto);
	}
	
	
	
	
	@Test(expected = CustomerException.class)
	public void AccountNotFoundTest() throws CustomerException
	{
		Mockito.when(customerRepository.findById(1L)).thenReturn(Optional.of(customer));
		Mockito.when(accountRepository.findByAccountNumber("1234")).thenReturn(Optional.of(account));
		Mockito.when(mortgageRepository.findByCustomerId(1L)).thenReturn(Optional.of(mortgage));
		Mockito.when(accountRepository.findByCustomerId(1L)).thenReturn(accountList);
		
		ResponseDto actual=paymentServiceImpl.makePayment(paymentDto);
	}
	
	
	
}
