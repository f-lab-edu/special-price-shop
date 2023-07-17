package com.specialpriceshop.auth.application;

import com.specialpriceshop.account.domain.AccountId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LogoutService {

    private final TokenCreateService tokenCreateService;

    public void logout(final AccountId accountId) {
        tokenCreateService.deleteToken(accountId.getValue());
    }

}
