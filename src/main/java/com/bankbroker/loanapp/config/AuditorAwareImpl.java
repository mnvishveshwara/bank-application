package com.bankbroker.loanapp.config;

import com.bankbroker.loanapp.entity.core.AdminUser;
import com.bankbroker.loanapp.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component("auditorAware")
@RequiredArgsConstructor
public class AuditorAwareImpl implements AuditorAware<AdminUser> {

    private final SecurityUtil securityUtil;

    @Override
    public Optional<AdminUser> getCurrentAuditor() {
        return Optional.ofNullable(securityUtil.getLoggedInAdmin());
    }
}
