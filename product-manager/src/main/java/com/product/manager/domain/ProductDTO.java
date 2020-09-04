package com.product.manager.domain;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

@Data
@JsonInclude(Include.NON_NULL)
@JsonIgnoreProperties(value = { "brand", "onsale" })
public class ProductDTO {

	private Long id;

	private String brand;

	private String name;

	private BigDecimal price;

	private boolean onsale;

	private String event;

	public String getEvent() {
		if (this.isOnsale()) {
			return "ON SALE";
		}
		return event;
	}
}
