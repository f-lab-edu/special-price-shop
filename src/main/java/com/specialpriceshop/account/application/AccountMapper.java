package com.specialpriceshop.account.application;

import com.specialpriceshop.account.domain.Account;
import com.specialpriceshop.account.domain.Role;
import com.specialpriceshop.account.dto.AccountCreateRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class AccountMapper {

    public Account mapToAccountEntity(
        final AccountCreateRequest accountCreateRequest,
        final PasswordEncoder passwordEncoder
    ) {
        return Account.builder()
            .email(accountCreateRequest.getEmail())
            .password(passwordEncoder.encode(accountCreateRequest.getPassword()))
            .phone(accountCreateRequest.getPhone())
            .nickname(accountCreateRequest.getNickname())
            .role(Role.USER)
            .build();
    }

}
