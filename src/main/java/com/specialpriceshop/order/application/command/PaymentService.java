package com.specialpriceshop.order.application.command;

import com.specialpriceshop.account.domain.AccountId;
import com.specialpriceshop.item.exception.OutOfStockException;
import com.specialpriceshop.order.client.PaymentClient;
import com.specialpriceshop.order.domain.OrderStock;
import com.specialpriceshop.order.dto.PaymentRequest;
import java.math.BigDecimal;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final OrderManagement orderManagement;
    private final PaymentClient paymentClient;
    private final StockManagementStrategy stockManagementStrategy;

    @Transactional(isolation = Isolation.READ_COMMITTED)
    public void payment(
        final Long orderId,
        final AccountId accountId,
        final PaymentRequest paymentRequest
    ) {
        orderManagement.paymentAfterCheck(orderId, paymentRequest.getAmount(), accountId);

        approvePayment(orderId, paymentRequest);

        orderManagement.pay(orderId);

        final OrderStock orderStock = orderManagement.getOrderStock(orderId);
        decreaseStock(orderId, paymentRequest.getAmount(), orderStock);
    }

    private boolean approvePayment(
        final Long orderId,
        final PaymentRequest paymentRequest
    ) {
        return paymentClient.approvePayment(orderId, paymentRequest.getAmount());
    }

    private void decreaseStock(
        final Long orderId,
        final BigDecimal amount,
        final OrderStock orderStock
    ) {
        int row = stockManagementStrategy.decreaseStock(orderStock.getStockId(),
            orderStock.getQuantity());

        if (row <= 0) {
            paymentClient.cancelPayment(orderId, amount);
            throw new OutOfStockException();
        }
    }
}
