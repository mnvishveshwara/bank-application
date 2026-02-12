package com.bankbroker.loanapp.entity.core;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "user_sessions")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserSession {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String userId;

    @Column(nullable = false, unique = true, length = 500)
    private String token;

    @Column(nullable = false)
    private boolean active;

    @Column(nullable = false)
    private LocalDateTime loginTime;

    @Column(length = 100)
    private String ipAddress;

    @Column(length = 500)
    private String deviceInfo;
}
