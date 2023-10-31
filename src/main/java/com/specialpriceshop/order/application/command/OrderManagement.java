package com.specialpriceshop.order.application.command;

import com.specialpriceshop.account.domain.AccountId;
import com.specialpriceshop.order.domain.Order;
import com.specialpriceshop.order.domain.OrderStock;
import com.specialpriceshop.order.repository.OrderLineRepository;
import com.specialpriceshop.order.repository.OrderRepository;
import java.math.BigDecimal;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderManagement {

    private final OrderRepository orderRepository;
    private final OrderLineRepository orderLineRepository;
    public void isMyOrder(
        final Long orderId,
        final AccountId accountId) {

        orderRepository.findById(orderId)
            .orElseThrow()
            .isMyOrder(accountId);
    }

    @Transactional
    public void payment(
        final Long orderId,
        final BigDecimal requestAmount,
        final AccountId accountId
    ) {
        final Order order = orderRepository.findById(orderId)
            .orElseThrow();
        order.isMyOrder(accountId);
        order.payment(requestAmount);
    }

    public OrderStock getOrderStock(final Long orderId) {
        return orderLineRepository.findByOrderId(orderId).orElseThrow().getOrderStock();
    }
}
