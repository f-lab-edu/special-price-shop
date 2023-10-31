package com.specialpriceshop.order.domain;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Orderline {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private OrderStock orderStock;

    private Long itemId;

    public Orderline(
        final Long itemId,
        final OrderStock orderStock
    ) {
        this.itemId = itemId;
        this.orderStock = orderStock;
    }
}
