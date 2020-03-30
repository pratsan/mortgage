package com.star.mortgage.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
@Setter
@Getter
public class UserTransactionDto {
private String accountNumber;
List<TransactionDto> transactionDtoList;
}
