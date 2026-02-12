package com.bankbroker.loanapp.repository.core;

import com.bankbroker.loanapp.entity.core.UserSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface UserSessionRepository
        extends JpaRepository<UserSession, Long> {

    Optional<UserSession> findByUserIdAndActiveTrue(String userId);

    Optional<UserSession> findByTokenAndActiveTrue(String token);

    @Modifying
    @Transactional
    @Query("UPDATE UserSession s SET s.active = false WHERE s.userId = :userId")
    void deactivateAllActiveSessions(String userId);


}
