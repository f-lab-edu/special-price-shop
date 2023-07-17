package com.specialpriceshop.auth.dto;

public record TokenResponse(
    String accessToken,
    String refreshToken,
    long accessTokenValidityInMilliseconds,
    long refreshTokenValidityInMilliseconds) {
}
