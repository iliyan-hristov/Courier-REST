package com.example.Project_api.repository;

import com.example.Project_api.model.OrderEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public interface OrderEventRepository extends JpaRepository <OrderEvent, UUID> {

}
