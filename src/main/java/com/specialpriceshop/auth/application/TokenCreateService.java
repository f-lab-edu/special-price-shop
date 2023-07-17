package com.specialpriceshop.auth.application;


import com.specialpriceshop.auth.domain.JwtTokenProvider;
import com.specialpriceshop.auth.domain.RefreshToken;
import com.specialpriceshop.auth.domain.RefreshTokenRepository;
import com.specialpriceshop.auth.dto.TokenResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class TokenCreateService {

    private final JwtTokenProvider tokenProvider;
    private final RefreshTokenRepository refreshTokenRepository;
    private final long accessTokenValidityInMilliseconds;
    private final long refreshTokenValidityInMilliseconds;

    public TokenCreateService(
        final JwtTokenProvider tokenProvider,
        final RefreshTokenRepository refreshTokenRepository,
        @Value("${custom.jwt.access-token-validity-in-milliseconds}") long accessTokenValidityInMilliseconds,
        @Value("${custom.jwt.refresh-token-validity-in-milliseconds}") long refreshTokenValidityInMilliseconds
    ) {
        this.tokenProvider = tokenProvider;
        this.refreshTokenRepository = refreshTokenRepository;
        this.accessTokenValidityInMilliseconds = accessTokenValidityInMilliseconds;
        this.refreshTokenValidityInMilliseconds = refreshTokenValidityInMilliseconds;
    }

    public TokenResponse createTokenResponse(final Authentication authentication) {
        final String accessToken = createAccessToken(authentication);
        final String refreshToken = tokenProvider.createRefreshToken();

        return new TokenResponse(
            accessToken,
            refreshToken,
            accessTokenValidityInMilliseconds,
            refreshTokenValidityInMilliseconds);
    }

    public void saveRefreshToken(
        final String accountId,
        final String refreshToken
    ) {
        refreshTokenRepository.save(
            new RefreshToken(accountId, refreshToken, refreshTokenValidityInMilliseconds));
    }

    public void deleteToken(final String userId) {
        refreshTokenRepository.deleteById(userId);
    }

    public String createAccessToken(final Authentication authentication) {
        return tokenProvider.createAccessToken(authentication);
    }

}
