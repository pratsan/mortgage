package com.star.mortgage.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.star.mortgage.dto.PaymentDto;
import com.star.mortgage.dto.ResponseDto;
import com.star.mortgage.entity.Account;
import com.star.mortgage.entity.Customer;
import com.star.mortgage.entity.Mortgage;
import com.star.mortgage.entity.Transaction;
import com.star.mortgage.exception.CustomerException;
import com.star.mortgage.repository.AccountRepository;
import com.star.mortgage.repository.CustomerRepository;
import com.star.mortgage.repository.MortgageRepository;
import com.star.mortgage.repository.TransactionRepository;
import com.star.mortgage.utility.ErrorConstant;

@Service
public class PaymentServiceImpl implements PaymentService {
	@Autowired
	AccountRepository accountRepository;
	@Autowired
	TransactionRepository transactionRepository;
	@Autowired
	CustomerRepository customerRepository;
	@Autowired
	MortgageRepository mortgageRepository;

	@Override
	public ResponseDto makePayment(PaymentDto paymentDto) throws CustomerException {
		Optional<Customer> customer = customerRepository.findById(paymentDto.getCustomerId());
		Optional<Account> account = accountRepository.findByAccountNumber(paymentDto.getAccountNumber());
		Optional<Mortgage> mortgage = mortgageRepository.findByCustomerId(paymentDto.getCustomerId());
		List<Account> accountList;
		if (!customer.isPresent())
			throw new CustomerException(ErrorConstant.NO_RECORD_FOUND);
		if (!account.isPresent())
			throw new CustomerException(ErrorConstant.INVALID_ACCOUNT);
		if (!account.get().getCustomerId().equals(customer.get().getCustomerId())) {
			throw new CustomerException(ErrorConstant.NOT_APPLIED);
		}

		accountList = accountRepository.findByCustomerId(paymentDto.getCustomerId());

		Optional<Account> mortgageAccount = accountList.stream().filter(p -> p.getAccountType().equals("mortgage"))
				.findFirst();
		if(!mortgage.isPresent())
		{
			throw new CustomerException(ErrorConstant.INVALID_ACCOUNT);
		}
		if(!mortgageAccount.isPresent())
		{
			throw new CustomerException(ErrorConstant.INVALID_ACCOUNT);
		}
		updateAccount(account.get(), mortgage.get(), mortgageAccount.get());
		updateTransaction(paymentDto, mortgage.get(), mortgageAccount.get());

		ResponseDto responseDto = new ResponseDto();
		responseDto.setStatusMessage(ErrorConstant.PAID_SUCCESS);
		responseDto.setStatusCode(ErrorConstant.PAID_SUCCESS_CODE);
		return responseDto;
	}

	public void updateAccount(Account account,Mortgage mortgage,
			Account mortgageAccount) {

		account.setBalance(account.getBalance() - (mortgage.getEmi()));
		mortgageAccount.setBalance(mortgageAccount.getBalance() + (mortgage.getEmi()));

		accountRepository.save(account);
		accountRepository.save(mortgageAccount);
	}

	public void updateTransaction(PaymentDto paymentDto, Mortgage mortgage, Account account) {
		Transaction userTransaction = new Transaction();
		userTransaction.setAccountNumber(paymentDto.getAccountNumber());
		userTransaction.setAmount(mortgage.getEmi());
		userTransaction.setDescription("loan");
		userTransaction.setTransactionDate(new Date());
		userTransaction.setTransactionType("debit");

		Transaction mortgageTransaction = new Transaction();
		mortgageTransaction.setAccountNumber(account.getAccountNumber());
		mortgageTransaction.setAmount(mortgage.getEmi());
		mortgageTransaction.setDescription("loan");
		mortgageTransaction.setTransactionDate(new Date());
		mortgageTransaction.setTransactionType("credit");

		transactionRepository.save(userTransaction);
		transactionRepository.save(mortgageTransaction);

	}

}
