package com.bankbroker.loanapp.entity.valuator;

import com.bankbroker.loanapp.entity.core.AgencyMaster;
import com.bankbroker.loanapp.entity.core.AdminUser;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "valuator_master")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ValuatorMaster {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "valuator_name", nullable = false)
    private String valuatorName;

    @Column(name = "valuator_last_name", nullable = false)
    private String valuatorLastName;

    @Column(name = "contact_number", nullable = false)
    private String contactNumber;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    // ⭐ Every valuator belongs to ONE agency
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "agency_id", nullable = false)
    private AgencyMaster agency;

    // ⭐ Audit fields
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "created_by")
    private AdminUser createdBy;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "updated_by")
    private AdminUser updatedBy;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    // Login account stored inside AdminUser table
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "login_user_id")
    private AdminUser loginAccount;

    @PrePersist
    public void prePersist() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        updatedAt = LocalDateTime.now();
    }
}
