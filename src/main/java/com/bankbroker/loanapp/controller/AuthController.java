package com.bankbroker.loanapp.controller;

import com.bankbroker.loanapp.dto.auth.AuthResponse;
import com.bankbroker.loanapp.dto.auth.LoginRequest;
import com.bankbroker.loanapp.entity.AdminUser;
import com.bankbroker.loanapp.repository.AdminUserRepository;
import com.bankbroker.loanapp.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider tokenProvider;
    private final AdminUserRepository adminUserRepository;


    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );

        UserDetails principal = (UserDetails) authentication.getPrincipal();
        // Fetch user from DB
        AdminUser user = adminUserRepository.findByEmail(principal.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));

        String role = principal.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .findFirst()
                .orElse("ROLE_USER");

        // ⭐ Pass userId
        String token = tokenProvider.generateToken(user.getId(), user.getEmail(), role);

        return ResponseEntity.ok(AuthResponse.builder()
                .token(token)
                .role(role)
                .build());
    }

}
