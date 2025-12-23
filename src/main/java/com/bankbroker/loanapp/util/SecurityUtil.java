package com.bankbroker.loanapp.util;

import com.bankbroker.loanapp.entity.core.AdminUser;
import com.bankbroker.loanapp.repository.core.AdminUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SecurityUtil {

    private final AdminUserRepository adminRepo;

    public AdminUser getLoggedInAdmin() {
        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            throw new RuntimeException("No authenticated user found");
        }

        Object principal = authentication.getPrincipal();

        if (!(principal instanceof String)) {
            throw new RuntimeException("Invalid authentication principal");
        }

        String adminId = (String) principal;

        return adminRepo.findById(adminId)
                .orElseThrow(() -> new RuntimeException("Invalid logged-in user"));
    }
}

