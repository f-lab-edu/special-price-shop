package com.specialpriceshop.order.ui;

import com.specialpriceshop.account.domain.AccountId;
import com.specialpriceshop.common.config.annotation.AuthUser;
import com.specialpriceshop.order.application.command.OrderCreateService;
import com.specialpriceshop.order.dto.OrderCreateRequest;
import java.net.URI;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class TimeDealOrderCreateController {

    private final OrderCreateService orderCreateService;

    @PostMapping("/time-deals/{time-deal-id}")
    public ResponseEntity<Void> createTimeDealOrder(
        @AuthUser final AccountId accountId,
        @PathVariable("time-deal-id") final Long timeDealId,
        @RequestBody @Valid OrderCreateRequest orderCreateRequest
    ) {

        final Long createOrderId = orderCreateService.createTimeDealOrder(
            timeDealId,
            orderCreateRequest,
            accountId
        );

        return ResponseEntity
            .created(URI.create("/orders/" + createOrderId))
            .build();
    }
}
