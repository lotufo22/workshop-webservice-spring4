package com.coursejava.webservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.coursejava.webservice.entities.OrderItem;
import com.coursejava.webservice.entities.OrderItemPk;

public interface OrderItemRepository extends JpaRepository<OrderItem, OrderItemPk> {

}
