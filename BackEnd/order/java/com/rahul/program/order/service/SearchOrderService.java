package com.rahul.program.order.service;

import com.rahul.program.order.model.Guest;


import com.rahul.program.order.model.Order;
import com.rahul.program.order.repository.OrderRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;
import java.util.Map;

@Service
@Transactional

public class SearchOrderService {
    @Autowired
    private OrderRepository orderRepository;

    //Returns all the active orders
    public ResponseEntity<?> getAllActiveOrders() {
        //Fetch all the orders with status true
        List<Order> orders = orderRepository.findAllByStatus(true);

        return ResponseEntity.status(HttpStatus.OK).body(orders);
    }

    //Get order by order id
    public ResponseEntity<?> getOrderById(String _id) throws Exception {
        //Fetch order with mentioned id from DB
        Order order = orderRepository.findById(_id)
                .orElseThrow(() -> new Exception("Order id is Invalid"));

        //Response
        return ResponseEntity.status(HttpStatus.OK).body(order);
    }
    

    //Update guest details
    public ResponseEntity<?> updateGuestDetails(Guest guest, String _id) throws Exception {
       
        Order order = orderRepository.findById(_id).orElseThrow(() -> new Exception("Order id is Invalid"));
        order.setGuest(guest);
        orderRepository.save(order);

        //Response
        return ResponseEntity.status(HttpStatus.OK)
                .body(Map.of("message", "Guest details are updated for order id: " + _id));
    }

    
    //Get all orders whether it is active or not active
    public ResponseEntity<?> getAllOrders() throws Exception {
     
        List<Order> orders = orderRepository.findAll();

        //Response
        return ResponseEntity.status(HttpStatus.OK).body(orders);
    }
}
