package com.rahul.program.order.repository;

import com.rahul.program.order.model.Order;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends MongoRepository<Order, String> {
    @Query("{ 'status' : ?0 }")
    List<Order> findAllByStatus(boolean b);

    @Query("{ 'guest.mobileNo' : ?0 }")
    List<Order> findByMobileNo(String mobileNo);
}
