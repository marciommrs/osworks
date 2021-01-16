package com.green.osworks.domain.service;



import java.time.OffsetDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.green.osworks.domain.exception.DomainException;
import com.green.osworks.domain.exception.EntityNotFoundException;
import com.green.osworks.domain.model.Client;
import com.green.osworks.domain.model.Comment;
import com.green.osworks.domain.model.WorkOrder;
import com.green.osworks.domain.model.WorkOrderStatus;
import com.green.osworks.domain.repository.ClientRepository;
import com.green.osworks.domain.repository.CommentRepository;
import com.green.osworks.domain.repository.WorkOrderRepository;

@Service
public class ManageWorkOrderService {
	
	@Autowired
	private WorkOrderRepository workOrderRepository;
	
	@Autowired
	private ClientRepository clientRepository;
	
	@Autowired
	private CommentRepository commentRepository;
	
	public WorkOrder create(WorkOrder workOrder) {
		
		Client client = clientRepository.findById(workOrder.getClient().getId())
				.orElseThrow(() -> new DomainException("Cliente não encontrado"));
		
		workOrder.setClient(client);
		workOrder.setStatus(WorkOrderStatus.OPEN);
		workOrder.setOpenDate(OffsetDateTime.now());
		return workOrderRepository.save(workOrder);
	}
	
	public void finish(Long workOrderId) {
		
		WorkOrder workOrder = load(workOrderId);
		workOrder.finish();
		
		workOrderRepository.save(workOrder);
	}
	
	public Comment addComment(Long workOrderId, String description) {
		WorkOrder workOrder = load(workOrderId);
		
		Comment comment = new Comment();
		comment.setSendDate(OffsetDateTime.now());
		comment.setDescription(description);
		comment.setWorkOrder(workOrder);	
		
		
		return commentRepository.save(comment);		
	}

	private WorkOrder load(Long workOrderId) {
		return workOrderRepository.findById(workOrderId)
				.orElseThrow(() -> new EntityNotFoundException("Ordem de serviço não encontrada"));
	}

}
