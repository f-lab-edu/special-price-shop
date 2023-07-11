package com.specialpriceshop.common.util;

import lombok.experimental.UtilityClass;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@UtilityClass
public class CookieUtil {

    public static Optional<Cookie> getCookie(HttpServletRequest request, String name) {
        Cookie[] cookies = request.getCookies();

        if (cookies != null && cookies.length > 0) {
            for (Cookie cookie : cookies) {
                if (name.equals(cookie.getName())) {
                    return Optional.of(cookie);
                }
            }
        }
        return Optional.empty();
    }

    public static Cookie create(
        final String name,
        final String value,
        final long maxAgeMillis,
        final String domain
    ) {
        Cookie cookie = new Cookie(name, value);
        cookie.setDomain(domain);
        cookie.setPath("/");
        cookie.setMaxAge((int) maxAgeMillis / 1000);
        cookie.setHttpOnly(true);
//        cookie.setSecure(true);
        return cookie;
    }

    public static Cookie deleteCookie(
        String name,
        String cookieDomain) {
        Cookie cookie = new Cookie(name, "");
        cookie.setValue("");
        cookie.setPath("/");
        cookie.setMaxAge(0);
        cookie.setDomain(cookieDomain);
        return cookie;
    }
}

