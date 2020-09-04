package com.product.manager.domain.entity;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity(name = "product")
public class Product {

	@Id
	@GeneratedValue
	private Long id;

	private String brand;

	private String name;

	private BigDecimal price;

	private boolean onsale;
}
