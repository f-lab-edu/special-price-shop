package com.specialpriceshop.account.application;

import static org.junit.jupiter.api.Assertions.*;

import com.specialpriceshop.account.domain.Account;
import com.specialpriceshop.account.domain.Role;
import com.specialpriceshop.account.dto.AccountCreateRequest;
import com.specialpriceshop.fixture.account.AccountCreateRequestFixture;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

class AccountMapperTest {

    private AccountMapper accountMapper;
    private PasswordEncoder passwordEncoder;

    @BeforeEach
    void init() {
        this.accountMapper = new AccountMapper();
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    @Test
    @DisplayName("AccountCreateRequest를 Account로 변환한다")
    void mapToAccountEntity() {

        final String email = "test@test.com";
        final String password = "1q2w3e4r!11";
        final String nickname = "닉네임";
        final String phone = "01012341234";

        AccountCreateRequest accountCreateRequest = AccountCreateRequestFixture.create()
            .email(email)
            .password(password)
            .nickname(nickname)
            .phone(phone)
            .build();

        Account account = accountMapper.mapToAccountEntity(accountCreateRequest, passwordEncoder);
        assertEquals(account.getEmail(),email);
        assertNotEquals(account.getPassword(), password);
        assertEquals(account.getNickname(),nickname);
        assertEquals(account.getPhone(), phone);
        assertEquals(account.getRole(),Role.USER);

    }
}
