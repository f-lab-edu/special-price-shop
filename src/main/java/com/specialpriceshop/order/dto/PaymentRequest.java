package com.specialpriceshop.order.dto;

import java.math.BigDecimal;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PaymentRequest {

    private BigDecimal amount;

    public PaymentRequest(final BigDecimal amount) {
        this.amount = amount;
    }
}
