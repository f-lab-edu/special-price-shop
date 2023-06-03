package com.specialpriceshop.order.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderStock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long orderQuantity;

    @ManyToOne
    @JoinColumn(name = "order_line_id")
    private Orderline orderline;

    public OrderStock(
        final Long id,
        final Long orderQuantity,
        final Orderline orderline
    ) {
        this.id = id;
        this.orderQuantity = orderQuantity;
        this.orderline = orderline;
    }
}
