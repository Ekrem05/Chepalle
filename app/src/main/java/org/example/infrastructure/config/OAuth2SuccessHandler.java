package org.example.infrastructure.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Date;

@Component
public class OAuth2SuccessHandler implements AuthenticationSuccessHandler {

    @Value("${jwt.secret}")
    private String SECRET_KEY; // Change this!

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {

        OAuth2User oauthUser = (OAuth2User) authentication.getPrincipal();
        String login = (String) oauthUser.getAttribute("login");

        String token = JWT.create()
                .withSubject(login)
                .withIssuer("chepalle")
                .withClaim("name", (String) oauthUser.getAttribute("name"))
                .withClaim("login", login)
                .withExpiresAt(new Date(System.currentTimeMillis() + 86400000)) // 1 day expiry
                .sign(Algorithm.HMAC256(SECRET_KEY));

        response.setContentType("application/json");
        response.getWriter().write("{\"token\": \"" + token + "\"}");
    }
}
