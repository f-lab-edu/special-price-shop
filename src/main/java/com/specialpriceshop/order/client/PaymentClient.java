package com.specialpriceshop.order.client;

public interface PaymentClient {

    void approvePayment(final Long orderId);

    void cancelPayment();
}
