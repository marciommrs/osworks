package com.green.osworks.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.green.osworks.api.model.CommentInputModel;
import com.green.osworks.api.model.CommentModel;
import com.green.osworks.domain.exception.EntityNotFoundException;
import com.green.osworks.domain.model.Comment;
import com.green.osworks.domain.model.WorkOrder;
import com.green.osworks.domain.repository.WorkOrderRepository;
import com.green.osworks.domain.service.ManageWorkOrderService;

@RestController
@RequestMapping("/service-orders/{workOrderId}/comments")
public class CommentController {
	
	@Autowired
	private ManageWorkOrderService manageService;
	
	@Autowired
	private WorkOrderRepository workOrderRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@GetMapping
	public List<CommentModel> list(@PathVariable Long workOrderId) {
		WorkOrder workOrder = workOrderRepository.findById(workOrderId)
				.orElseThrow(() -> new EntityNotFoundException("Entidade não encontrada"));
		
		return toCollectionModel(workOrder.getComments());
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public CommentModel add(@PathVariable Long workOrderId, 
			@Valid @RequestBody CommentInputModel commentInputModel) {
		
		Comment comment = manageService.addComment(workOrderId, 
				commentInputModel.getDescription());
		
		return toModel(comment);
	}
	
	private CommentModel toModel(Comment comment) {
		return modelMapper.map(comment, CommentModel.class);
	}
	

	private List<CommentModel> toCollectionModel(List<Comment> comments) {
		return comments.stream().map(c -> toModel(c)).collect(Collectors.toList());
	}

}
