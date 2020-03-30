package com.star.mortgage.dto;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
@Setter
@Getter
public class TransactionDto {
	private Date transactionDate;
	private String transactionType;
	private Double amount;
	private String description;
}
