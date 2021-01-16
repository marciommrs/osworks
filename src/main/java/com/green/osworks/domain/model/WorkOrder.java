package com.green.osworks.domain.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.green.osworks.domain.exception.DomainException;

@Entity
@Table(name = "TB_WORK_ORDER")
public class WorkOrder {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	// ----------------------------------------------------------------------------
	// Validações removidas pois foram incluídas no WorkOrderInputModel.
	// ----------------------------------------------------------------------------

//	@Valid
//	@ConvertGroup(from = Default.class, to=ValidationGroups.ClientId.class)
//	@NotNull
	@ManyToOne
	private Client client;

//	@NotBlank
	private String description;

//	@NotNull
	private BigDecimal price;

//	@JsonProperty(access = Access.READ_ONLY)
	@Enumerated(EnumType.STRING)
	private WorkOrderStatus status;

//	@JsonProperty(access = Access.READ_ONLY)
	private OffsetDateTime openDate;

//	@JsonProperty(access = Access.READ_ONLY)
	private OffsetDateTime endDate;

	@OneToMany(mappedBy = "workOrder")
	private List<Comment> comments = new ArrayList<>();

	public WorkOrder() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

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

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		WorkOrder other = (WorkOrder) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	public boolean canBeFinalized() {
		return WorkOrderStatus.OPEN.equals(getStatus());
	}
	
	public boolean cannotBeFinalized() {
		return !canBeFinalized();
	}

	public void finish() {
		if (cannotBeFinalized()) {
			throw new DomainException("Ordem de serviço não pode ser finalizada");
		}
		setStatus(WorkOrderStatus.FINISHED);
		setEndDate(OffsetDateTime.now());
	}

}
