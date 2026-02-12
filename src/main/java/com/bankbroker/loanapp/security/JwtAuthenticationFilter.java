package com.bankbroker.loanapp.security;

import com.bankbroker.loanapp.repository.core.UserSessionRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtTokenProvider tokenProvider;
    private final CustomUserDetailsService userDetailsService;
    private final UserSessionRepository userSessionRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        String header = request.getHeader("Authorization");
        String token = null;
        String username = null;


//        if (header != null && header.startsWith("Bearer ")) {
//            token = header.substring(7);
//
//            if (tokenProvider.validateToken(token)) {
//                username = tokenProvider.getUsernameFromJwt(token);
//                String adminId = tokenProvider.getUserIdFromJwt(token);
//
//                // Save adminId in request (so your service can read it)
//                request.setAttribute("adminId", adminId);
//            }
//        }

        if (header != null && header.startsWith("Bearer ")) {
            token = header.substring(7);

            if (tokenProvider.validateToken(token)) {

                // 🔐 CHECK DB: is this token still active?
                boolean active = userSessionRepository
                        .findByTokenAndActiveTrue(token)
                        .isPresent();

                if (!active) {
                    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                    return; // ❌ stop filter chain
                }

                username = tokenProvider.getUsernameFromJwt(token);
                String adminId = tokenProvider.getUserIdFromJwt(token);

                request.setAttribute("adminId", adminId);
            }
        }



        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);

            String adminId = (String) request.getAttribute("adminId");

            UsernamePasswordAuthenticationToken auth =
                    new UsernamePasswordAuthenticationToken(
                            adminId,
                            null,
                            userDetails.getAuthorities()
                    );

            auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

            SecurityContextHolder.getContext().setAuthentication(auth);
        }


        filterChain.doFilter(request, response);
    }
}
