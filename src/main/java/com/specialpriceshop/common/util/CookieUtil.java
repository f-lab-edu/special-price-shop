package com.specialpriceshop.common.util;

import java.time.Duration;
import lombok.experimental.UtilityClass;
import org.springframework.http.ResponseCookie;

@UtilityClass
public class CookieUtil {

    public static ResponseCookie create(
        final String name,
        final String value,
        final long maxAgeMillis,
        final String domain
    ) {

        return ResponseCookie.from(name, value)
            .domain(domain)
            .path("/")
            .maxAge(Duration.ofMillis(maxAgeMillis))
            .httpOnly(true)
//            .secure(true)
            .build();
    }

    public static ResponseCookie deleteCookie(
        String name,
        String domain) {
        return ResponseCookie.from(name, "")
            .domain(domain)
            .path("/")
            .maxAge(0)
            .httpOnly(true)
//            .secure(true)
            .build();
    }
}

