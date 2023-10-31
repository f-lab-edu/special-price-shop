package com.specialpriceshop.order.application.command;

import com.specialpriceshop.account.domain.AccountId;
import com.specialpriceshop.order.client.PaymentClient;
import com.specialpriceshop.order.domain.Order;
import com.specialpriceshop.order.domain.OrderStock;
import com.specialpriceshop.order.dto.PaymentRequest;
import com.specialpriceshop.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final OrderRepository orderRepository;
    private final PaymentClient paymentClient;
    private final StockManagementStrategy stockManagementStrategy;
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public void payment(
        final Long orderId,
        final AccountId accountId,
        final PaymentRequest paymentRequest
    ) {
        Order order = orderRepository.findById(orderId)
            .orElseThrow();
        order.isMyOrder(accountId);

        boolean approvePayment = isApprovePayment(orderId, paymentRequest);

        order.payment(paymentRequest.getAmount());
        if (!approvePayment) {
            paymentClient.cancelPayment(orderId, paymentRequest.getAmount());
        }
        OrderStock orderStock = order.getOrderline().getOrderStock();
        //TODO 재고차감 성공시 결제처리완료
        stockManagementStrategy.decreaseStock(orderStock.getStockId(), orderStock.getQuantity());
    }

    private boolean isApprovePayment(final Long orderId, final PaymentRequest paymentRequest) {
        return paymentClient.approvePayment(orderId, paymentRequest.getAmount());
    }
}
