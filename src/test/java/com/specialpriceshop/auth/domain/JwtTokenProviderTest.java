package com.specialpriceshop.auth.domain;

import com.specialpriceshop.account.domain.Role;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.util.Base64Utils;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

class JwtTokenProviderTest {

    private JwtTokenProvider jwtTokenProvider;

    @BeforeEach
    void init() {
        final String secretKey = Base64Utils.encodeToString(
            "TestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTest".getBytes());
        jwtTokenProvider = new JwtTokenProvider(secretKey, Long.MAX_VALUE / 2, Long.MAX_VALUE / 2);
    }

    @Test
    @DisplayName("엑세스 토큰을 생성한다")
    void createAccessToken() {
        final Authentication authentication = new UsernamePasswordAuthenticationToken(
            "pricipal", "credentials",
            Collections.singleton(new SimpleGrantedAuthority(
                Role.USER.name())));
        assertNotNull(jwtTokenProvider.createAccessToken(authentication));
    }

    @Test
    @DisplayName("리프레쉬 토큰을 생성한다")
    void createRefreshToken() {
        final String refreshToken = jwtTokenProvider.createRefreshToken();
        assertNotNull(refreshToken);
    }
}