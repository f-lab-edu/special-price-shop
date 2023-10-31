package com.specialpriceshop.order.repository;

import com.specialpriceshop.order.domain.Order;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query("SELECT o FROM Order o INNER JOIN FETCH o.payment INNER JOIN FETCH o.orderline WHERE o.id=:orderId")
    Optional<Order> findById(final Long orderId);
}
