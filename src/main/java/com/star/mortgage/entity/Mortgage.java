package com.star.mortgage.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class Mortgage {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long mortgageId;
private String propertyType;
private Double propertyCost;
private Double deposit;
private String employementType;
private Double emi;
private Double tenure;
private Long customerId;
}
