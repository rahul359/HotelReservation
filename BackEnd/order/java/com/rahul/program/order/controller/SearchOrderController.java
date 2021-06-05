package com.rahul.program.order.controller;

import com.rahul.program.order.model.Guest;
import com.rahul.program.order.service.SearchOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/hotel/book/get")
@CrossOrigin(origins="http://localhost:3000")
public class SearchOrderController {
    @Autowired
    private SearchOrderService searchOrderService;

    //Returns all the active orders
    @GetMapping("/order/active")
    public ResponseEntity<?> getAllActiveOrders() {
        try {
            return searchOrderService.getAllActiveOrders();
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.FORBIDDEN)
                    .body(Map.of("message", e.getMessage()));
        }
    }

    //Return all the orders
    @GetMapping("/order")
    public ResponseEntity<?> getAllOrders() {
        try {
            return searchOrderService.getAllOrders();
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.FORBIDDEN)
                    .body(Map.of("message", e.getMessage()));
        }
    }

    //Returns order by id
    @GetMapping("/order/{_id}")
    public ResponseEntity<?> getOrderById(@PathVariable String _id) {
        try {
            return searchOrderService.getOrderById(_id);
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.FORBIDDEN)
                    .body(Map.of("message", e.getMessage()));
        }
    }

    //Update guest details
    @PostMapping("/guest/{_id}")
    public ResponseEntity<?> updateGuestDetails(@RequestBody Guest guest, @PathVariable String _id) {
        try {
            return searchOrderService.updateGuestDetails(guest, _id);
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.FORBIDDEN)
                    .body(Map.of("message", e.getMessage()));
        }
    }

}
