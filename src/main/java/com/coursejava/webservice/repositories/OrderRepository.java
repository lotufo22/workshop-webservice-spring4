package com.coursejava.webservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.coursejava.webservice.entities.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {

}
