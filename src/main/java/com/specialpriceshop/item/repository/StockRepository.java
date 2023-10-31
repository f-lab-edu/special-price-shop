package com.specialpriceshop.item.repository;

import com.specialpriceshop.item.domain.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface StockRepository extends JpaRepository<Stock, Long> {

    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query("""
        UPDATE Stock s 
        SET s.quantity=s.quantity-:quantity 
        WHERE s.id=:stockId 
        AND s.quantity>=:quantity
        """)
    int optimisticDecrease(Long stockId, Long quantity);
}
