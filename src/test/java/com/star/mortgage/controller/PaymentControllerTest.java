package com.star.mortgage.controller;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.star.mortgage.dto.PaymentDto;
import com.star.mortgage.dto.ResponseDto;
import com.star.mortgage.exception.CustomerException;
import com.star.mortgage.service.PaymentService;
import com.star.mortgage.utility.ErrorConstant;

@RunWith(MockitoJUnitRunner.Silent.class)
public class PaymentControllerTest {
@InjectMocks
PaymentController paymentController;
@Mock
PaymentService paymentService;
PaymentDto paymentDto=new PaymentDto();

ResponseDto  responseDto=new ResponseDto();
@Before
public void init()
{
	paymentDto.setAccountNumber("123");
	paymentDto.setCustomerId(1L);
	
	responseDto.setStatusMessage(ErrorConstant.PAID_SUCCESS);
	responseDto.setStatusCode(ErrorConstant.PAID_SUCCESS_CODE);
}
@Test
public void PaymentTest() throws CustomerException
{
	Mockito.when(paymentService.makePayment(paymentDto)).thenReturn(responseDto);
	ResponseEntity<ResponseDto> actual=paymentController.payment(paymentDto);
	assertEquals(HttpStatus.ACCEPTED, actual.getStatusCode());
}
}
