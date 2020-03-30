package com.star.mortgage.service;

import com.star.mortgage.dto.PaymentDto;
import com.star.mortgage.dto.ResponseDto;
import com.star.mortgage.exception.CustomerException;

public interface PaymentService {
	
	public ResponseDto makePayment(PaymentDto paymentDto) throws CustomerException;

}
