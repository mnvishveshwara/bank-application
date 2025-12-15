package com.bankbroker.loanapp.controller;

import com.bankbroker.loanapp.dto.auth.AuthResponse;
import com.bankbroker.loanapp.dto.auth.LoginRequest;
import com.bankbroker.loanapp.entity.AdminUser;
import com.bankbroker.loanapp.entity.Customer;
import com.bankbroker.loanapp.repository.AdminUserRepository;
import com.bankbroker.loanapp.repository.CustomerRepository;
import com.bankbroker.loanapp.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider tokenProvider;
    private final AdminUserRepository adminUserRepository;
    private final CustomerRepository customerRepository;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {

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

        String token = tokenProvider.generateToken(
                userId,
                email,
                role
        );

        log.info("Login successful | userId={} | role={}", userId, role);

        return ResponseEntity.ok(
                AuthResponse.builder()
                        .token(token)
                        .role(role)
                        .build()
        );
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
}
