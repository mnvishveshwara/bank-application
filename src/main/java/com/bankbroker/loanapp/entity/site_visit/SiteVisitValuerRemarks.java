package com.bankbroker.loanapp.entity.site_visit;

import com.bankbroker.loanapp.entity.core.AdminUser;
import com.bankbroker.loanapp.entity.core.LoanApplication;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "site_visit_valuer_remarks")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SiteVisitValuerRemarks {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // -------------------------------------------------
    // 🔗 Application Mapping
    // -------------------------------------------------
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "application_id", nullable = false, unique = true)
    private LoanApplication application;

    // -------------------------------------------------
    // 📝 Valuer Remarks (Step-9)
    // -------------------------------------------------

    @Lob
    @Column(name = "general_remarks")
    private String generalRemarks;

    @Lob
    @Column(name = "critical_actionable_remarks")
    private String criticalAndActionableRemarks;

    @Lob
    @Column(name = "insights")
    private String insights;

    // -------------------------------------------------
    // 🕒 Audit Fields
    // -------------------------------------------------

    @Column(name = "created_date", nullable = false, updatable = false)
    private LocalDateTime createdDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "created_by", updatable = false)
    private AdminUser createdBy;

    @Column(name = "updated_date")
    private LocalDateTime updatedDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "updated_by")
    private AdminUser updatedBy;
}
