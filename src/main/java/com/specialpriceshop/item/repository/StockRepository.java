package com.specialpriceshop.item.repository;

import com.specialpriceshop.item.domain.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockRepository extends JpaRepository<Stock, Long> {

}
