package com.specialpriceshop.auth.domain;

import com.specialpriceshop.account.domain.Role;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.util.Base64Utils;

import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;


class JwtTokenParserTest {

    private JwtTokenParser jwtTokenParser;
    private JwtTokenProvider jwtTokenProvider;

    @BeforeEach
    void init() {
        final String secretKey = Base64Utils.encodeToString(
            "TestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTest".getBytes());
        jwtTokenParser = new JwtTokenParser(secretKey);
        jwtTokenProvider = new JwtTokenProvider(secretKey, Long.MAX_VALUE / 2, Long.MAX_VALUE / 2);
    }

    @Test
    @DisplayName("토큰에서 Authentication객체를 가져온다")
    void extractAuthentication() {
        final var authentication = createAuthentication();
        final var token = jwtTokenProvider.createAccessToken(authentication);

        final var extractedAuthentication = jwtTokenParser.extractAuthentication(token);

        assertThat(authentication.getAuthorities())
            .containsAll(extractedAuthentication.getAuthorities());
    }

    @Test
    @DisplayName("정상적인 엑세스 토큰 검증")
    void validateAccessToken() {
        final var authentication = createAuthentication();
        final var token = jwtTokenProvider.createAccessToken(authentication);

        assertThat(jwtTokenParser.validateAccessToken(token)).isTrue();

    }

    @Test
    @DisplayName("잘못된 엑세스 토큰 검증")
    void validateAccessToken_invalidToken() {
        final var invalidToken = "invalidToken";
        assertThat(jwtTokenParser.validateAccessToken(invalidToken)).isFalse();
    }

    @Test
    @DisplayName("정상적인 리프레쉬 토큰 검증")
    void validateRefreshToken() {
        final var token = jwtTokenProvider.createRefreshToken();

        assertThat(jwtTokenParser.validateRefreshToken(token)).isTrue();

    }

    @Test
    @DisplayName("잘못된 리프레쉬 토큰 검증")
    void validateRefreshToken_invalidToken() {
        final var invalidToken = "invalidToken";

        assertThat(jwtTokenParser.validateRefreshToken(invalidToken)).isFalse();
    }

    private UsernamePasswordAuthenticationToken createAuthentication() {
        final var authorities = List.of(new SimpleGrantedAuthority(Role.USER.name()),
            new SimpleGrantedAuthority(Role.ADMIN.name()));
        final var authentication = new UsernamePasswordAuthenticationToken(
            "principal",
            "credentials", authorities);
        return authentication;
    }
}