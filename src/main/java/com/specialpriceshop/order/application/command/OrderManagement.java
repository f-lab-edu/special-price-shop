package com.specialpriceshop.order.application.command;

import com.specialpriceshop.account.domain.AccountId;
import com.specialpriceshop.order.domain.Order;
import com.specialpriceshop.order.domain.OrderStock;
import com.specialpriceshop.order.exception.OrderNotFoundException;
import com.specialpriceshop.order.repository.OrderLineRepository;
import com.specialpriceshop.order.repository.OrderRepository;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderManagement {

    private final OrderRepository orderRepository;
    private final OrderLineRepository orderLineRepository;

    public void paymentAfterCheck(
        final Long orderId,
        final BigDecimal requestAmount,
        final AccountId accountId
    ) {
        final Order order = orderRepository.findById(orderId)
            .orElseThrow(OrderNotFoundException::new);
        order.isMyOrder(accountId);
        order.paymentAfterCheck(
            LocalDateTime.now(),
            requestAmount
        );
    }

    @Transactional
    public void pay(final Long orderId) {
        orderRepository.findById(orderId)
            .orElseThrow(OrderNotFoundException::new)
            .pay();
    }

    public OrderStock getOrderStock(final Long orderId) {
        return orderLineRepository.findByOrderId(orderId)
            .orElseThrow(OrderNotFoundException::new)
            .getOrderStock();
    }
}
