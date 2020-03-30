package com.star.mortgage.entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class Customer {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long customerId;
private String firstName;
private String lastName;
private LocalDate dob;
private String phoneNumber;
private String email;
private String panCard;
private String occupation;
private Double salary;
private String loginId;
private String password;
}
