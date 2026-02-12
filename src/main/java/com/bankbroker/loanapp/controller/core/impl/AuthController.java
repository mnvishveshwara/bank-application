package com.bankbroker.loanapp.controller.core.impl;

import com.bankbroker.loanapp.dto.auth.AuthResponse;
import com.bankbroker.loanapp.dto.auth.LoginRequest;
import com.bankbroker.loanapp.entity.core.AdminUser;
import com.bankbroker.loanapp.entity.core.Customer;
import com.bankbroker.loanapp.entity.core.UserSession;
import com.bankbroker.loanapp.repository.core.AdminUserRepository;
import com.bankbroker.loanapp.repository.core.CustomerRepository;
import com.bankbroker.loanapp.repository.core.UserSessionRepository;
import com.bankbroker.loanapp.security.JwtTokenProvider;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@Slf4j
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider tokenProvider;
    private final AdminUserRepository adminUserRepository;
    private final CustomerRepository customerRepository;
    private final UserSessionRepository userSessionRepository;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(
            @RequestBody LoginRequest request,
            HttpServletRequest httpRequest
    ) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );

        UserDetails principal = (UserDetails) authentication.getPrincipal();

        String email = principal.getUsername();
        String role = extractRole(principal);
        String userId = resolveUserId(email, role);

        // 🔥 FORCE LOGIN: kill all existing sessions
        userSessionRepository.deactivateAllActiveSessions(userId);

        // 🔐 Generate new JWT
        String token = tokenProvider.generateToken(
                userId,
                email,
                role
        );

        // 🌍 Device & IP
        String ipAddress = getClientIp(httpRequest);
        String deviceInfo = getDeviceInfo(httpRequest);

        // ✅ Save new active session
        userSessionRepository.save(
                UserSession.builder()
                        .userId(userId)
                        .token(token)
                        .active(true)
                        .loginTime(LocalDateTime.now())
                        .ipAddress(ipAddress)

                        .deviceInfo(deviceInfo)
                        .build()
        );

        return ResponseEntity.ok(
                AuthResponse.builder()
                        .token(token)
                        .role(role)
                        .build()
        );
    }

 

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(
            @RequestHeader("Authorization") String authHeader
    ) {
        String token = authHeader.replace("Bearer ", "");

        userSessionRepository.findByTokenAndActiveTrue(token)
                .ifPresent(session -> {
                    session.setActive(false);
                    userSessionRepository.save(session);
                });

        return ResponseEntity.ok().build();
    }


    // -------------------------------------------------
    // 🔐 Helper Methods
    // -------------------------------------------------

    private String extractRole(UserDetails principal) {
        return principal.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Role not found"));
    }

    private String resolveUserId(String email, String role) {

        // Admin / Agency / Valuator users
        if (!"ROLE_USER".equals(role)) {
            AdminUser admin = adminUserRepository.findByEmail(email)
                    .orElseThrow(() -> new RuntimeException("Admin user not found"));
            return admin.getId();
        }

        // Customer users
        Customer customer = customerRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Customer not found"));
        return customer.getId();
    }

    private String getClientIp(HttpServletRequest request) {
        String xff = request.getHeader("X-Forwarded-For");
        if (xff != null && !xff.isBlank()) {
            return xff.split(",")[0];
        }
        return request.getRemoteAddr();
    }


    private String getDeviceInfo(HttpServletRequest request) {
        return request.getHeader("User-Agent");
    }

}
