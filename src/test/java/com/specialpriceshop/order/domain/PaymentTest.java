package com.specialpriceshop.order.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.specialpriceshop.fixture.order.PaymentFixture;
import java.math.BigDecimal;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

class PaymentTest {


    @Test
    @DisplayName("결제")
    void pay() {
        Payment payment = PaymentFixture.create().paymentStatus(PaymentStatus.NONE).build();
        payment.pay();

        assertTrue(payment.getPaymentStatus().equals(PaymentStatus.PAYMENT));
    }

    @Test
    @DisplayName("결제 가능 여부 체크 true")
    void isAvailable() {
        Payment payment = PaymentFixture.create().paymentStatus(PaymentStatus.NONE).build();

        assertTrue(payment.isAvailable());
    }

    @DisplayName("결제 가능 여부 체크 false")
    @ParameterizedTest
    @EnumSource(names = {"PAYMENT", "CANCEL"})
    void isAvailable_false(PaymentStatus paymentStatus) {
        Payment payment = PaymentFixture.create()
            .paymentStatus(paymentStatus)
            .build();

        assertFalse(payment.isAvailable());
    }

    @Test
    @DisplayName("결제 금액 확인")
    void amountCheck() {
        final Long requestAmount = 100_000L;
        final BigDecimal bigDecimal = new BigDecimal(requestAmount);
        final Payment payment = PaymentFixture.create()
            .amount(BigDecimal.valueOf(requestAmount)).build();

        assertTrue(payment.amountCheck(bigDecimal)==0);
    }

    @Test
    @DisplayName("결제 금액 확인 실패")
    void amountCheck_false() {
        final Long requestAmount = 100_000L;
        final BigDecimal bigDecimal = new BigDecimal(requestAmount);
        final Payment payment = PaymentFixture.create()
            .amount(BigDecimal.valueOf(requestAmount)).build();

        assertEquals(true, payment.amountCheck(BigDecimal.valueOf(100L)) != 0);
    }
}