package com.star.mortgage.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.star.mortgage.entity.Account;
@Repository
public interface AccountRepository extends JpaRepository<Account,Long> {
	public Optional<Account> findByAccountNumber(String accountNumber);
	public List<Account> findByCustomerId(Long customerId);

}
