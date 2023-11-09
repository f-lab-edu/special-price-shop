package com.specialpriceshop.order.domain;

import static org.junit.jupiter.api.Assertions.*;

import com.specialpriceshop.account.domain.AccountId;
import com.specialpriceshop.fixture.order.OrderFixture;
import com.specialpriceshop.fixture.order.PaymentFixture;
import com.specialpriceshop.order.exception.NotAvailableException;
import com.specialpriceshop.order.exception.NotMyOrderException;
import com.specialpriceshop.order.exception.PaymentDueDateOverException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;
import javax.servlet.http.PushBuilder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class OrderTest {

    @Test
    @DisplayName("나의 주문 여부")
    void isMyOrder() {
        final String uuid = UUID.randomUUID().toString();
        final Order order = OrderFixture.create().accountId(AccountId.of(uuid)).build();

        assertDoesNotThrow(() -> order.isMyOrder(AccountId.of(uuid)));
    }

    @Test
    @DisplayName("나의 주문 여부-실패")
    void isMyOrder_throw() {
        final String uuid = UUID.randomUUID().toString();
        final String another = UUID.randomUUID().toString();
        final Order order = OrderFixture.create()
            .accountId(AccountId.of(uuid))
            .build();

        assertThrows(NotMyOrderException.class, () -> order.isMyOrder(AccountId.of(another)));
    }

    @Test
    @DisplayName("결제전 결제가능 여부 확인")
    void paymentAfterCheck() {
        final Long amount = 100L;
        final Payment payment = PaymentFixture.create()
            .amount(BigDecimal.valueOf(amount))
            .build();
        final Order order = OrderFixture.create()
            .payment(payment)
            .paymentDueDate(LocalDateTime.MAX)
            .build();

        assertDoesNotThrow(() ->
            order.paymentAfterCheck(LocalDateTime.now(),
                BigDecimal.valueOf(amount)));
    }

    @Test
    @DisplayName("결제만료 시간 초과 에러")
    void paymentAfterCheck_paymentDueDateOver() {
        final LocalDateTime localDateTime = LocalDateTime.of(2023, 11, 11, 10, 11);
        final Payment payment = PaymentFixture.create()
            .build();

        final Order order = OrderFixture.create()
            .payment(payment)
            .paymentDueDate(LocalDateTime.MIN)
            .build();

        assertThrows(
            PaymentDueDateOverException.class,
            () -> order.paymentAfterCheck(localDateTime, BigDecimal.valueOf(100L)));
    }

    @Test
    @DisplayName("결제 상태가 미결제가 아닐경우")
    void paymentAfterCheck_paymentStatusNotNONE() {
        final Payment payment = PaymentFixture.create()
            .amount(BigDecimal.valueOf(100L))
            .paymentStatus(PaymentStatus.PAYMENT)
            .build();

        final Order order = OrderFixture.create()
            .payment(payment)
            .paymentDueDate(LocalDateTime.MAX)
            .build();

        assertThrows(
            NotAvailableException.class,
            () -> order.paymentAfterCheck(LocalDateTime.now(),
                BigDecimal.valueOf(100L))
        );
    }
    @Test
    @DisplayName("결제 처리")
    void pay() {
        final Payment payment = PaymentFixture.create()
            .amount(BigDecimal.valueOf(100L))
            .paymentStatus(PaymentStatus.PAYMENT)
            .build();

        final Order order = OrderFixture.create()
            .payment(payment)
            .paymentDueDate(LocalDateTime.MAX)
            .build();

        order.pay();

        assertEquals(payment.getPaymentStatus(),PaymentStatus.PAYMENT);
    }
}