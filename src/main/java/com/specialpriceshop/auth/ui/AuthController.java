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
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.validation.Valid;

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
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody final LoginRequest loginRequest) {

        final TokenResponse tokenResponse = loginService.login(loginRequest);

        Cookie refreshToken = CookieUtil.create(
            "RefreshToken",
            tokenResponse.refreshToken(),
            tokenResponse.refreshTokenValidityInMilliseconds(),
            domain
        );

        return ResponseEntity.ok()
            .header(SET_COOKIE, refreshToken.toString())
            .body(new LoginResponse(tokenResponse.accessToken()));
    }

    @DeleteMapping("/logout")
    public ResponseEntity<Void> logout(@AuthUser final AccountId userId) {

        final Cookie expireRefreshToken = CookieUtil.deleteCookie(
            "RefreshToken",
            domain
        );

        logoutService.logout(userId);

        return ResponseEntity.ok()
            .header(SET_COOKIE, expireRefreshToken.toString())
            .build();
    }

    @PostMapping("/reissue")
    public ResponseEntity<LoginResponse> reissue(@CookieValue("RefreshToken") final String refreshToken) {
        return ResponseEntity.ok(tokenReissueService.reIssue(refreshToken));
    }

}
