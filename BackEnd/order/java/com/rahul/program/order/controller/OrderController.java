package com.rahul.program.order.controller;

import org.springframework.beans.factory.annotation.Autowired;




import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import com.rahul.program.order.service.AddOrderService;
import com.rahul.program.order.service.OrderService;

import com.rahul.program.order.model.Order;


import java.util.Map;

@RestController
@CrossOrigin(origins="http://localhost:3000")
public class OrderController {
    @Autowired
    private AddOrderService addOrderService;
    
    

    @Autowired
    private OrderService orderService;

    //Create order
    @PostMapping("/order")
    public ResponseEntity<?> addOrder(@RequestBody Order order) {
        try {
            return addOrderService.addOrder(order);
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.FORBIDDEN)
                    .body(Map.of("message", e.getMessage()));
        }
    }
    

    //Checkout the customer from hotel
    @PutMapping("/checkout/{_id}")
    public ResponseEntity<?> checkOut(@PathVariable String _id) {
        try {
            return orderService.checkOut(_id);
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.FORBIDDEN)
                    .body(Map.of("message", e.getMessage()));
        }
    }

    

   



    //Delete order by id
    @DeleteMapping("/room/{orderId}")
    public ResponseEntity<?> deleteOrderById(@PathVariable String orderId) {
        try {
            return orderService.deleteOrderById(orderId);
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.FORBIDDEN)
                    .body(Map.of("message", e.getMessage()));
        }
    }
    
  
}
