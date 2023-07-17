package com.specialpriceshop.auth.application;

import com.specialpriceshop.auth.dto.LoginRequest;
import com.specialpriceshop.auth.dto.TokenResponse;
import lombok.RequiredArgsConstructor;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final TokenCreateService tokenCreateService;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    public TokenResponse login(final LoginRequest loginRequest) {
        final AuthenticationManager authenticationManager = authenticationManagerBuilder.getObject();
        final Authentication authenticate = authenticationManager.authenticate(
            loginRequest.toAuthentication());

        final TokenResponse tokenResponse = tokenCreateService.createTokenResponse(authenticate);
        tokenCreateService.saveRefreshToken(authenticate.getName(), tokenResponse.refreshToken());

        return tokenResponse;
    }
}
