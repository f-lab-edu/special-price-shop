package com.specialpriceshop.auth.domain;

import com.specialpriceshop.common.error.exception.BusinessException;
import com.specialpriceshop.common.response.ErrorCode;
import org.springframework.stereotype.Component;

@Component
public class RefreshTokenValidator {

    private final JwtTokenParser tokenParser;
    private final RefreshTokenRepository refreshTokenRepository;

    public RefreshTokenValidator(final JwtTokenParser tokenParser,
        final RefreshTokenRepository refreshTokenRepository) {
        this.tokenParser = tokenParser;
        this.refreshTokenRepository = refreshTokenRepository;
    }

    public boolean validate(final String accountId, final String refreshToken) {
        final RefreshToken savedRefreshToken = getRefreshToken(accountId);
        return tokenParser.validateRefreshToken(refreshToken) && savedRefreshToken.hasSameValue(
            refreshToken);
    }

    private RefreshToken getRefreshToken(final String accountId) {
        return refreshTokenRepository.findById(accountId)
            .orElseThrow(() -> new BusinessException(ErrorCode.INVALID_INPUT_VALUE));
    }
}
