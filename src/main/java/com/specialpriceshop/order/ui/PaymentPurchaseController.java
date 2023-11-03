package com.specialpriceshop.order.ui;

import com.specialpriceshop.account.domain.AccountId;
import com.specialpriceshop.common.config.annotation.AuthUser;
import com.specialpriceshop.order.application.command.PaymentService;
import com.specialpriceshop.order.dto.PaymentRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders/{order-id}/payment")
@RequiredArgsConstructor
public class PaymentPurchaseController {

    private final PaymentService paymentService;

    @PostMapping
    public ResponseEntity<Void> payment(
        @PathVariable("order-id") final Long orderId,
        @AuthUser final AccountId accountId,
        @RequestBody final PaymentRequest paymentRequest
    ) {
        paymentService.payment(orderId, accountId, paymentRequest);
        return ResponseEntity.ok().build();
    }
}
