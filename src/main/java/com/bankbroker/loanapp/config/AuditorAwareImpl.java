package com.bankbroker.loanapp.config;

import com.bankbroker.loanapp.entity.core.AdminUser;
import com.bankbroker.loanapp.repository.core.AdminUserRepository;
import com.bankbroker.loanapp.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Optional;
//
//@Component("auditorAware")
//@RequiredArgsConstructor
//public class AuditorAwareImpl implements AuditorAware<AdminUser> {
//
//    private final AdminUserRepository adminUserRepository;
//
//    @Override
//    public Optional<AdminUser> getCurrentAuditor() {
//        Authentication auth =
//                SecurityContextHolder.getContext().getAuthentication();
//
//        if (auth == null || !auth.isAuthenticated()
//                || "anonymousUser".equals(auth.getPrincipal())) {
//            return Optional.empty(); // IMPORTANT
//        }
//
//        String adminId = auth.getPrincipal().toString();
//
//        return adminUserRepository.findById(adminId);
//    }
//}
//

@Component("auditorAware")
@RequiredArgsConstructor
public class AuditorAwareImpl implements AuditorAware<AdminUser> {

    private final SecurityUtil securityUtil;

    @Override
    public Optional<AdminUser> getCurrentAuditor() {
        return Optional.ofNullable(securityUtil.getLoggedInAdmin());
    }
}
