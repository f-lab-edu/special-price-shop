package com.specialpriceshop.order.client;

import java.math.BigDecimal;

public interface PaymentClient {

    boolean approvePayment(final Long orderId, final BigDecimal amount);

    void cancelPayment(Long orderId, BigDecimal amount);
}
