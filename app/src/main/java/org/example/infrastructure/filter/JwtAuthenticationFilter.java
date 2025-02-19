package org.example.infrastructure.filter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Component
@Order(1)
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private static final Map<String, List<String>> PERMITTED_PATHS = new HashMap<String, List<String>>() {{
        put("/api/tyre/**", Arrays.asList(HttpMethod.GET.name(), HttpMethod.POST.name()));
        put("/api/other-endpoint/**", Arrays.asList(HttpMethod.GET.name()));
        put("/swagger-ui/**", Arrays.asList(HttpMethod.GET.name()));
        put("/v3/api-docs/**", Arrays.asList(HttpMethod.GET.name()));
    }};

    @Value("${jwt.secret}")
    private String SECRET_KEY;;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        System.out.println("JWT AUTH FILTER");

        String path = request.getRequestURI();
        if (isPermitted(request.getRequestURI(), request.getMethod())) {
            filterChain.doFilter(request, response); // Skip the JWT logic and continue
            return;
        }

        String header = request.getHeader("Authorization");
        if (header == null || !header.startsWith("Bearer ")) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType("application/json");
            response.getWriter().write("{\"error\": \"No token provided\"}");
            return;
        }

        try{
            String token = header.substring(7);
            DecodedJWT jwt = JWT.require(Algorithm.HMAC256(SECRET_KEY))
                    .build()
                    .verify(token);

            String login = jwt.getSubject();

            UserDetails userDetails = User.withUsername(login).password("").authorities("USER").build();
            UsernamePasswordAuthenticationToken authentication =
                    new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

            SecurityContextHolder.getContext().setAuthentication(authentication);
            filterChain.doFilter(request, response);
        }
        catch (JWTVerificationException e){
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType("application/json");
            response.getWriter().write("{\"error\": \"Invalid or expired token\"}");
        }

    }
    private boolean isPermitted(String uri, String method) {
        PathMatcher pathMatcher = new AntPathMatcher();
        return PERMITTED_PATHS.entrySet().stream()
                .anyMatch(entry -> pathMatcher.match(entry.getKey(), uri) && entry.getValue().contains(method));
    }
}
