package com.specialpriceshop.order.repository;

import com.specialpriceshop.order.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {

}
