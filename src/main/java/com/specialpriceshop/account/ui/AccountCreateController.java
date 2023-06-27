package com.specialpriceshop.account.ui;

import com.specialpriceshop.account.application.AccountCreateService;
import com.specialpriceshop.account.dto.AccountCreateRequest;
import java.net.URI;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/accounts")
@RequiredArgsConstructor
public class AccountCreateController {

    private final AccountCreateService accountCreateService;

    @PostMapping
    public ResponseEntity<Void> createAccount(
        @RequestBody final AccountCreateRequest request
    ) {
        final String accountId = accountCreateService.createAccount(request);
        return ResponseEntity
            .created(URI.create("/accounts/" + accountId))
            .build();
    }
}
