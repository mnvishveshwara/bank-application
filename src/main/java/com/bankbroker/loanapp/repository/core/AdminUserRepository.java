package com.bankbroker.loanapp.repository.core;

import com.bankbroker.loanapp.entity.core.AdminUser;
import com.bankbroker.loanapp.entity.enums.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface AdminUserRepository extends JpaRepository<AdminUser, String> {
    Optional<AdminUser> findByEmail(String email);
    boolean existsByEmail(String email);

    Optional<AdminUser> findByAgencyId(Long agencyId);

    Optional<AdminUser> findByAgencyIdAndRole(Long agencyId, Role role);

    Optional<AdminUser> findById(String valuatorId);

    @Query("SELECT u FROM AdminUser u " +
            "JOIN u.banks b " +
            "WHERE b.id = :bankId AND u.role = :role")
    List<AdminUser> findByBankIdAndRole(
            @Param("bankId") Long bankId,
            @Param("role") Role role
    );
}
