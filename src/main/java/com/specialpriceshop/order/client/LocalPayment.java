package com.specialpriceshop.order.client;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class LocalPayment implements PaymentClient {

    @Override
    public void approvePayment(final Long orderId) {
        try {
            log.info("=====================================");
            log.info("결제 서비스 호출 orderId = {}", orderId);
            log.info("=====================================");

            Thread.sleep(1000L);
            log.info("======================================");
            log.info("결제 완료 orderId = {}", orderId);
            log.info("======================================");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public void cancelPayment() {

    }
}
