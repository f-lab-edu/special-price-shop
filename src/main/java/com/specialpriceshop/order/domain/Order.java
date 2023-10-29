package com.specialpriceshop.order.domain;

import com.specialpriceshop.account.domain.AccountId;
import com.specialpriceshop.common.entity.BaseTimeEntity;
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
public class Order extends BaseTimeEntity {

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

    private AccountId accountId;

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
        final AccountId accountId,
        final String address,
        final LocalDateTime paymentDueDate,
        final Payment payment
    ) {
        this.orderType = orderType;
        this.orderStatus = orderStatus;
        this.orderline = orderline;
        this.accountId = accountId;
        this.address = address;
        this.paymentDueDate = paymentDueDate;
        this.payment = payment;
    }

    public static Order timeDealOrder(
        final Orderline orderline,
        final AccountId accountId,
        final String address,
        final LocalDateTime paymentDueDate,
        final Payment payment
    ) {
        return Order.builder()
            .orderType(OrderType.TIME_DEAL)
            .orderStatus(OrderStatus.ORDER)
            .orderline(orderline)
            .accountId(accountId)
            .address(address)
            .paymentDueDate(paymentDueDate)
            .payment(payment)
            .build();
    }

    public void isMyOrder(final AccountId accountId) {
        if (!this.accountId.equals(accountId)) {
            throw new RuntimeException("내주문이 아님");
        }
    }
}
