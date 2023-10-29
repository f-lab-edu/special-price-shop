package com.specialpriceshop.order.application.command;

import com.specialpriceshop.account.domain.AccountId;
import com.specialpriceshop.order.client.PaymentClient;
import com.specialpriceshop.order.domain.Order;
import com.specialpriceshop.order.dto.PaymentRequest;
import com.specialpriceshop.order.repository.OrderRepository;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final OrderRepository orderRepository;
    private final PaymentClient paymentClient;

    @Transactional
    public void payment(
        final Long orderId,
        final AccountId accountId,
        final PaymentRequest paymentRequest
    ) {
        Order order = orderRepository.findById(orderId)
            .orElseThrow();
        order.isMyOrder(accountId);

        paymentClient.approvePayment(orderId);

        order.getPayment().pay(paymentRequest.getAmount());
        //TODO 결제완료시 실 재고 차감
    }
}
