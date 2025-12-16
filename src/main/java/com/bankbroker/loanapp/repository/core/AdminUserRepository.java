package com.bankbroker.loanapp.repository.core;

import com.bankbroker.loanapp.entity.core.AdminUser;
import com.bankbroker.loanapp.entity.enums.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdminUserRepository extends JpaRepository<AdminUser, String> {
    Optional<AdminUser> findByEmail(String email);
    boolean existsByEmail(String email);

    Optional<AdminUser> findByAgencyId(Long agencyId);

    Optional<AdminUser> findByAgencyIdAndRole(Long agencyId, Role role);

    Optional<AdminUser> findById(String valuatorId);


}
