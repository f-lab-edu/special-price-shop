package com.specialpriceshop.order.repository;

import com.specialpriceshop.order.domain.OrderStock;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderStockRepository extends JpaRepository<OrderStock, Long> {

}
