package com.bankbroker.loanapp.entity.valuator;

import com.bankbroker.loanapp.entity.core.AdminUser;
import com.bankbroker.loanapp.entity.core.LoanApplication;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "application_valuator_assignment")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AssignValuator {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 🔗 Application being assigned
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "application_id", nullable = false)
    private LoanApplication application;

    // 🔗 Which valuator is assigned (AdminUser with role AGENCY_VALUATOR)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "valuator_id", nullable = false)
    private AdminUser valuator;

    // 🔗 Assigned by (Agency Admin)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "assigned_by", nullable = false)
    private AdminUser assignedBy;

    // Notes / remarks
    @Column(name = "remarks")
    private String remarks;

    @Column(name = "assigned_date")
    private LocalDateTime assignedDate;

    @Column(name = "updated_date")
    private LocalDateTime updatedDate;

    @PrePersist
    public void onCreate() {
        assignedDate = LocalDateTime.now();
        updatedDate = LocalDateTime.now();
    }

    @PreUpdate
    public void onUpdate() {
        updatedDate = LocalDateTime.now();
    }
}
