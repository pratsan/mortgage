package com.star.mortgage.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.star.mortgage.dto.PaymentDto;
import com.star.mortgage.dto.ResponseDto;
import com.star.mortgage.exception.CustomerException;
import com.star.mortgage.service.PaymentService;

@RestController
public class PaymentController {
	@Autowired
	PaymentService paymentService;
@PostMapping("/payment")
public ResponseEntity<ResponseDto> payment(@RequestBody PaymentDto paymentDto) throws CustomerException
{
	return new ResponseEntity<>(paymentService.makePayment(paymentDto),HttpStatus.ACCEPTED);
}
}
