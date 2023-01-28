package com.library.facade.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.Optional;

public final class CookieUtils {

    private CookieUtils() {
    }

    public static Optional<String> getCookie(String cookieName, HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        return Arrays.stream(cookies)
                .filter(cookie -> cookieName.equals(cookie.getName()))
                .map(Cookie::getValue)
                .findFirst();
    }

    public static void setCookie(String cookieName, String cookieValue, String path, HttpServletResponse response) {
        Cookie cookie = new Cookie(cookieName, cookieValue);
        cookie.setMaxAge(60 * 60 * 24 * 90);
        cookie.setPath(path);
        response.addCookie(cookie);
    }
}
