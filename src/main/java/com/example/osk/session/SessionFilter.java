package com.example.osk.session;

import com.example.osk.model.User;
import com.example.osk.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class SessionFilter extends OncePerRequestFilter {
    private final InMemorySessionRegistry sessionRegistry;
    private final UserServiceImpl userService;

    @Autowired
    public SessionFilter(InMemorySessionRegistry sessionRegistry, UserServiceImpl userService) {
        this.sessionRegistry = sessionRegistry;
        this.userService = userService;
    }


    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        final String sessionId = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (sessionId == null || sessionId.length() == 0) {
            filterChain.doFilter(request, response);
            return;
        }

        final String email = sessionRegistry.getEmailForSession(sessionId);
        if (email == null) {
            filterChain.doFilter(request, response);
            return;
        }

        final User user = userService.loadUserByUsername(email);

        final UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
                user,
                null,
                user.getAuthorities()
        );

        auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(auth);
        filterChain.doFilter(request, response);

    }
}
