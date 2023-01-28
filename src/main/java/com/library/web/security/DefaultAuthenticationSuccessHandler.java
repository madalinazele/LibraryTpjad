package com.library.web.security;

import com.library.core.model.Role;
import com.library.facade.CartFacade;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AbstractAuthenticationTargetUrlRequestHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Component
@AllArgsConstructor
public class DefaultAuthenticationSuccessHandler extends AbstractAuthenticationTargetUrlRequestHandler implements AuthenticationSuccessHandler {

    private static final Map<String, String> ROLE_TARGET_URL_MAP = new HashMap<>();

    private CartFacade cartFacade;
    private SavedRequestAwareAuthenticationSuccessHandler savedRequest;

    static {
        ROLE_TARGET_URL_MAP.put(Role.ROLE_CLIENT.toString(), "/");
        ROLE_TARGET_URL_MAP.put(Role.ROLE_ADMIN.toString(), "/admin");
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authentication) throws IOException, ServletException {
        AuthenticationSuccessHandler.super.onAuthenticationSuccess(request, response, chain, authentication);
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        Optional<GrantedAuthority> authority = getAuthority(authentication);
        if (authority.isPresent() && authority.get().getAuthority().equals(Role.ROLE_CLIENT.toString())) {
            handleCart(request, response);
            savedRequest.onAuthenticationSuccess(request, response, authentication);
        } else {
            handle(request, response, authentication);
        }
        clearAuthenticationAttributes(request);
    }

    protected void clearAuthenticationAttributes(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null) {
            return;
        }
        session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
    }

    @Override
    protected String determineTargetUrl(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        String defaultTargetUrl = super.determineTargetUrl(request, response, authentication);
        Optional<GrantedAuthority> authority = getAuthority(authentication);

        if (authority.isEmpty()) {
            return defaultTargetUrl;
        }

        return ROLE_TARGET_URL_MAP.getOrDefault(authority.get().getAuthority(), defaultTargetUrl);
    }

    private Optional<GrantedAuthority> getAuthority(Authentication authentication) {
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

        if (authorities == null || authorities.isEmpty()) {
            return Optional.empty();
        }
        return Optional.ofNullable(authorities.iterator().next());
    }

    private void handleCart(HttpServletRequest request, HttpServletResponse response) {
        cartFacade.assignCartToCurrentUser(request, response);
    }
}
