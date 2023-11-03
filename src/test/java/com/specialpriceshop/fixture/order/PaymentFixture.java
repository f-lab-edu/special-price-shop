package com.specialpriceshop.fixture.order;

import com.specialpriceshop.order.domain.Payment;
import com.specialpriceshop.order.domain.PaymentStatus;
import java.math.BigDecimal;

public class PaymentFixture {


    public static Payment.PaymentBuilder create() {
        return Payment.builder()
            .paymentStatus(PaymentStatus.NONE)
            .amount(BigDecimal.valueOf(10000000L));
    }

}
