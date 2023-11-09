package com.specialpriceshop.order.client;

import java.math.BigDecimal;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class LocalPayment implements PaymentClient {

    @Override
    public boolean approvePayment(final Long orderId, final BigDecimal amount) {
        log.info("=====================================");
        log.info("결제 서비스 호출 orderId = {}", orderId);
        log.info("결제 금액 = {}", amount);
        log.info("=====================================");
        fakeApiSimulation();
        log.info("======================================");
        log.info("결제 완료 orderId = {}", orderId);
        log.info("결제 금액 = {}", amount);
        log.info("======================================");
        return true;
    }


    @Override
    public void cancelPayment(final Long orderId, final BigDecimal amount) {
        log.info("=====================================");
        log.info("결제 서비스 호출 orderId = {}", orderId);
        log.info("결제 금액 = {}", amount);
        log.info("=====================================");
        fakeApiSimulation();
        log.info("======================================");
        log.info("결제 취소 orderId = {}", orderId);
        log.info("결제 취소 금액  = {}", amount);
        log.info("======================================");
    }

    private void fakeApiSimulation() {
        try {
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
