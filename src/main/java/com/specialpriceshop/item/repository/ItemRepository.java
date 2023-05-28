package com.specialpriceshop.item.repository;

import com.specialpriceshop.item.domain.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {

}
