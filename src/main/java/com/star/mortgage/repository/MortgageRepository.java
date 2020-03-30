package com.star.mortgage.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.star.mortgage.entity.Mortgage;
@Repository
public interface MortgageRepository extends JpaRepository<Mortgage, Long> {
	Optional<Mortgage> findByCustomerId(Long customerId);

}
