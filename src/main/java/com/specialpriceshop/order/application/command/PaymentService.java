package com.specialpriceshop.order.application.command;

import com.specialpriceshop.account.domain.AccountId;
import com.specialpriceshop.order.client.PaymentClient;
import com.specialpriceshop.order.domain.OrderStock;
import com.specialpriceshop.order.dto.PaymentRequest;
import com.specialpriceshop.order.repository.OrderRepository;
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

    //TODO 결제호출 -> db에 결제 완료 처리시 검증 -> 실패시 결제취소하기를 호출하는 방법
    @Transactional(
        isolation = Isolation.READ_COMMITTED)
    public void payment(
        final Long orderId,
        final AccountId accountId,
        final PaymentRequest paymentRequest
    ) {

        boolean approvePayment = isApprovePayment(orderId, paymentRequest);

        orderManagement.payment(
            orderId,
            paymentRequest.getAmount(),
            accountId);

        if (!approvePayment) {
            paymentClient.cancelPayment(orderId, paymentRequest.getAmount());
        }

        OrderStock orderStock = orderManagement.getOrderStock(orderId);
        decreaseStock(orderId, paymentRequest.getAmount(), orderStock);
    }

    private void decreaseStock(final Long orderId, final BigDecimal amount,
        final OrderStock orderStock) {
        int row = stockManagementStrategy.decreaseStock(orderStock.getStockId(),
            orderStock.getQuantity());

        if (row <= 0) {
            paymentClient.cancelPayment(orderId, amount);
            throw new RuntimeException("재고부족");
        }
    }

    private boolean isApprovePayment(final Long orderId, final PaymentRequest paymentRequest) {
        return paymentClient.approvePayment(orderId, paymentRequest.getAmount());
    }
}
