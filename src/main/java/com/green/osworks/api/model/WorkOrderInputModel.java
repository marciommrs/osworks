package com.green.osworks.api.model;

import java.math.BigDecimal;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class WorkOrderInputModel {

	@NotBlank
	private String description;
	
	@NotNull
	private BigDecimal price;
	
	@Valid
	@NotNull
	private ClentIdInputModel client;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public ClentIdInputModel getClient() {
		return client;
	}

	public void setClient(ClentIdInputModel client) {
		this.client = client;
	}

}
