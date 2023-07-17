package com.specialpriceshop.auth.domain;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

@Component
public class JwtTokenProvider {

    public static final String AUTHORITIES_KEY = "auth";
    public static final String AUTHORITY_DELIMITER = ",";
    public static final String TOKEN_TYPE_KEY = "typ";
    private final long accessTokenValidityInMilliseconds;
    private final long refreshTokenValidityInMilliseconds;
    private final Key secretKey;

    public JwtTokenProvider(
        @Value("${custom.jwt.secretKey}") String secretKey,
        @Value("${custom.jwt.access-token-validity-in-milliseconds}") long accessTokenValidityInMilliseconds,
        @Value("${custom.jwt.refresh-token-validity-in-milliseconds}") long refreshTokenValidityInMilliseconds
    ) {
        this.accessTokenValidityInMilliseconds = accessTokenValidityInMilliseconds;
        this.refreshTokenValidityInMilliseconds = refreshTokenValidityInMilliseconds;
        this.secretKey = Keys.hmacShaKeyFor(secretKey.getBytes());
    }

    public String createAccessToken(final Authentication authentication) {
        final Date now = new Date();
        final Date validity = new Date(now.getTime() + (accessTokenValidityInMilliseconds));

        return Jwts.builder()
            .setSubject(authentication.getName())
            .claim(AUTHORITIES_KEY, toString(authentication.getAuthorities()))
            .claim(TOKEN_TYPE_KEY, TokenType.ACCESS)
            .signWith(secretKey, SignatureAlgorithm.HS512)
            .setIssuedAt(now)
            .setExpiration(validity)
            .compact();
    }

    public String createRefreshToken() {
        final Date now = new Date();
        final Date validity = new Date(now.getTime() + refreshTokenValidityInMilliseconds);

        return Jwts.builder()
            .claim(TOKEN_TYPE_KEY, TokenType.REFRESH)
            .signWith(secretKey, SignatureAlgorithm.HS512)
            .setIssuedAt(now)
            .setExpiration(validity)
            .compact();
    }

    private String toString(
        final Collection<? extends GrantedAuthority> grantedAuthorities) {
        return grantedAuthorities.stream()
            .map(GrantedAuthority::getAuthority)
            .collect(Collectors.joining(AUTHORITY_DELIMITER));
    }
}
