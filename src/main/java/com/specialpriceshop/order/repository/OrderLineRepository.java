package com.specialpriceshop.order.repository;

import com.specialpriceshop.order.domain.Orderline;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderLineRepository extends JpaRepository<Orderline, Long> {

}
