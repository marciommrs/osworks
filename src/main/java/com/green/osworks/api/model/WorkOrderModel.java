package com.green.osworks.api.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import com.green.osworks.domain.model.WorkOrderStatus;

public class WorkOrderModel {

	private Long id;
//	private String clientName;
	private ClientResumeModel client;
	private String description;
	private BigDecimal price;
	private WorkOrderStatus status;
	private OffsetDateTime openDate;
	private OffsetDateTime endDate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

//	public String getClientName() {
//		return clientName;
//	}
//
//	public void setClientName(String clientName) {
//		this.clientName = clientName;
//	}

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

	public WorkOrderStatus getStatus() {
		return status;
	}

	public void setStatus(WorkOrderStatus status) {
		this.status = status;
	}

	public OffsetDateTime getOpenDate() {
		return openDate;
	}

	public void setOpenDate(OffsetDateTime openDate) {
		this.openDate = openDate;
	}

	public OffsetDateTime getEndDate() {
		return endDate;
	}

	public void setEndDate(OffsetDateTime endDate) {
		this.endDate = endDate;
	}

	public ClientResumeModel getClient() {
		return client;
	}

	public void setClient(ClientResumeModel client) {
		this.client = client;
	}

}
