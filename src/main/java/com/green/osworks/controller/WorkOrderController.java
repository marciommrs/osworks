package com.green.osworks.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.green.osworks.api.model.WorkOrderInputModel;
import com.green.osworks.api.model.WorkOrderModel;
import com.green.osworks.domain.model.WorkOrder;
import com.green.osworks.domain.repository.WorkOrderRepository;
import com.green.osworks.domain.service.ManageWorkOrderService;

@RestController
@RequestMapping("/service-orders")
public class WorkOrderController {
	
	@Autowired
	private ManageWorkOrderService manageService;
	
	@Autowired
	private WorkOrderRepository workOrderRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public WorkOrderModel create(@Valid @RequestBody WorkOrderInputModel workOrderInputModel) {
		WorkOrder workOrder = toEntity(workOrderInputModel);
		
		return toModel(manageService.create(workOrder));
	}
	
	@GetMapping
	public List<WorkOrderModel> list() {
		return toCollectionModel(workOrderRepository.findAll());
	}

	@GetMapping("/{workOrderId}")
	public ResponseEntity<WorkOrderModel> find(@PathVariable Long workOrderId) {
		Optional<WorkOrder> workOrder = workOrderRepository.findById(workOrderId);
		if(workOrder.isPresent() ) {
			WorkOrderModel workOrderModel = toModel(workOrder.get());
			return ResponseEntity.ok(workOrderModel);
		}
		return ResponseEntity.notFound().build();
	}
	
	@PutMapping("/{workOrderId}/finish")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void finish(@PathVariable Long workOrderId) {
		manageService.finish(workOrderId);
	}
	
	private WorkOrderModel toModel(WorkOrder workOrder) {
		return modelMapper.map(workOrder, WorkOrderModel.class);
	}
	
	private List<WorkOrderModel> toCollectionModel(List<WorkOrder> workOrder) {
		return workOrder.stream().map(wo -> toModel(wo)).collect(Collectors.toList());
	}
	
	private WorkOrder toEntity(WorkOrderInputModel workOrderInputModel) {
		return modelMapper.map(workOrderInputModel, WorkOrder.class);
	}

}
