package com.green.osworks.domain.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.green.osworks.domain.model.WorkOrder;


@Repository
public interface WorkOrderRepository extends JpaRepository<WorkOrder, Long>{

}
