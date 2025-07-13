package com.myproject.ecommerce.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.myproject.ecommerce.domain.OrderRequest;
import com.myproject.ecommerce.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping
    public ResponseEntity<String> placeOrder(@RequestBody OrderRequest request) throws JsonProcessingException {
        orderService.placeOrder(request);
        return ResponseEntity.ok("Order placed successfully");
    }
}
