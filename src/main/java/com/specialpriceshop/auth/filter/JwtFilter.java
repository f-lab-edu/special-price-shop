package com.specialpriceshop.auth.filter;

import com.specialpriceshop.auth.domain.JwtTokenParser;
import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

public class JwtFilter extends OncePerRequestFilter {

    private static final String BEARER_TYPE = "Bearer";

    private final JwtTokenParser jwtTokenParser;

    public JwtFilter(JwtTokenParser jwtTokenParser) {
        this.jwtTokenParser = jwtTokenParser;
    }


    @Override
    protected void doFilterInternal(
        final HttpServletRequest request,
        final HttpServletResponse response,
        final FilterChain filterChain) throws ServletException, IOException {

        final String authToken = resolveToken(request);

        if (jwtTokenParser.validateAccessToken(authToken)) {
            final Authentication authentication = jwtTokenParser.extractAuthentication(authToken);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        filterChain.doFilter(request, response);

    }

    private String resolveToken(final HttpServletRequest request) {
        String authHeaderValue = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (StringUtils.hasText(authHeaderValue) && authHeaderValue.toLowerCase()
            .startsWith(BEARER_TYPE.toLowerCase())) {
            return authHeaderValue.substring(BEARER_TYPE.length()).trim();
        }
        return "";
    }
}
