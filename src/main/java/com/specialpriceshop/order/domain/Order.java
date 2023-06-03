package com.specialpriceshop.order.domain;

import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "purchase_orders")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private OrderType orderType;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    private String userId;

    private String address;

    private LocalDateTime paymentDueDate;

    private Long paymentId;

    public Order(
        final OrderType orderType,
        final OrderStatus orderStatus,
        final String userId,
        final String address,
        final LocalDateTime paymentDueDate,
        final Long paymentId
    ) {
        this.orderType = orderType;
        this.orderStatus = orderStatus;
        this.userId = userId;
        this.address = address;
        this.paymentDueDate = paymentDueDate;
        this.paymentId = paymentId;
    }
}
