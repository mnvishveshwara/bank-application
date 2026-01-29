package com.bankbroker.loanapp.entity.inbox;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "inbox_thread")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InboxThread {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String applicationId;

    private Long bankId;

    private Long agencyId;

    private String subject;

    private boolean unreadForAgency;

    private boolean unreadForBank;

    private LocalDateTime lastUpdated;
}
