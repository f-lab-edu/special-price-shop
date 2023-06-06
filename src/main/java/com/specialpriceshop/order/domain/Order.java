package com.specialpriceshop.order.domain;

import java.time.LocalDateTime;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
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

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "order_line_id")
    private Orderline orderline;

    private String userId;

    private String address;

    private LocalDateTime paymentDueDate;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "payment_id")
    private Payment payment;

    @Builder
    public Order(
        final OrderType orderType,
        final OrderStatus orderStatus,
        final Orderline orderline,
        final String userId,
        final String address,
        final LocalDateTime paymentDueDate,
        final Payment payment) {
        this.orderType = orderType;
        this.orderStatus = orderStatus;
        this.orderline = orderline;
        this.userId = userId;
        this.address = address;
        this.paymentDueDate = paymentDueDate;
        this.payment = payment;
    }

    public static Order timeDealOrder(
        final Orderline orderline,
        final String userId,
        final String address,
        final LocalDateTime paymentDueDate,
        final Payment payment) {
        return Order.builder()
            .orderType(OrderType.TIME_DEAL)
            .orderStatus(OrderStatus.ORDER)
            .orderline(orderline)
            .userId(userId)
            .address(address)
            .paymentDueDate(paymentDueDate)
            .payment(payment)
            .build();
    }
}
