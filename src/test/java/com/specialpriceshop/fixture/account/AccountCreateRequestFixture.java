package com.specialpriceshop.fixture.account;

import com.specialpriceshop.account.dto.AccountCreateRequest;
import com.specialpriceshop.account.dto.AccountCreateRequest.AccountCreateRequestBuilder;

public class AccountCreateRequestFixture {

    public static AccountCreateRequestBuilder create() {
        return AccountCreateRequest.builder()
            .email("test@test.com")
            .password("password")
            .nickname("닉네임")
            .phone("01012341234");
    }

}
