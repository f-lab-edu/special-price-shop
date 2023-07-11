package com.specialpriceshop.auth.ui;

import com.specialpriceshop.account.domain.AccountId;
import com.specialpriceshop.auth.application.LoginService;
import com.specialpriceshop.auth.application.LogoutService;
import com.specialpriceshop.auth.application.TokenReissueService;
import com.specialpriceshop.auth.dto.LoginRequest;
import com.specialpriceshop.auth.dto.LoginResponse;
import com.specialpriceshop.auth.dto.TokenResponse;
import com.specialpriceshop.common.config.annotation.AuthUser;
import com.specialpriceshop.common.util.CookieUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpHeaders.SET_COOKIE;

@RestController
@Slf4j
@RequestMapping("/auth")
public class AuthController {

    private final LoginService loginService;
    private final LogoutService logoutService;
    private final TokenReissueService tokenReissueService;
    private final String domain;

    public AuthController(
        final LoginService loginService,
        final LogoutService logoutService,
        final TokenReissueService tokenReissueService,
        @Value("${custom.cookieDomain}") final String domain
    ) {
        this.loginService = loginService;
        this.logoutService = logoutService;
        this.tokenReissueService = tokenReissueService;
        this.domain = domain;
    }

    @PostMapping("/login")
    public ResponseEntity<Void> login(
        @Valid @RequestBody final LoginRequest loginRequest) {

        final TokenResponse tokenResponse = loginService.login(loginRequest);

        final HttpHeaders httpHeaders = createLoginHeader(
            tokenResponse.refreshToken(),
            tokenResponse.refreshTokenValidityInMilliseconds(),
            tokenResponse.accessToken()
        );

        return ResponseEntity.ok()
            .headers(httpHeaders)
            .build();
    }

    @DeleteMapping("/logout")
    public ResponseEntity<Void> logout(@AuthUser final AccountId userId) {

        logoutService.logout(userId);

        return ResponseEntity.noContent()
            .headers(createLogoutHeader())
            .build();
    }

    @PostMapping("/reissue")
    public ResponseEntity<Void> reissue(
        @CookieValue("refresh") final String refreshToken) {
        final LoginResponse loginResponse = tokenReissueService.reIssue(refreshToken);

        final HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setBearerAuth(loginResponse.accessToken());

        return ResponseEntity.ok()
            .headers(httpHeaders)
            .build();
    }

    private HttpHeaders createLoginHeader(
        final String refreshToken,
        final long tokenValidityInMill,
        final String accessToken
    ) {
        final ResponseCookie cookie = CookieUtil.create(
            "refresh",
            refreshToken,
            tokenValidityInMill,
            domain
        );

        final HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(SET_COOKIE, cookie.toString());
        httpHeaders.setBearerAuth(accessToken);
        return httpHeaders;
    }

    private HttpHeaders createLogoutHeader() {
        ResponseCookie cookie = CookieUtil.deleteCookie("refresh", domain);

        final HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(SET_COOKIE, cookie.toString());
        httpHeaders.add(AUTHORIZATION, "");

        return httpHeaders;
    }

}
