package com.specialpriceshop.order.repository;

import com.specialpriceshop.order.domain.Orderline;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface OrderLineRepository extends JpaRepository<Orderline, Long> {

    @Query("""
        SELECT ol 
        FROM Orderline ol 
        INNER JOIN  Order o ON ol.id=o.orderline.id
        WHERE o.id=:orderId
        """)
    Optional<Orderline> findByOrderId(final Long orderId);
}
