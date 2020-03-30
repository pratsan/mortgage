package com.star.mortgage.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.star.mortgage.entity.Customer;
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

}



