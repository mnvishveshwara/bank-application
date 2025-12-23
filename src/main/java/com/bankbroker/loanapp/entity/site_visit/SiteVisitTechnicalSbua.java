package com.bankbroker.loanapp.entity.site_visit;

import com.bankbroker.loanapp.entity.core.AdminUser;
import com.bankbroker.loanapp.entity.core.LoanApplication;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(
        name = "site_visit_technical_sbua",
        uniqueConstraints = @UniqueConstraint(columnNames = "application_id")
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SiteVisitTechnicalSbua {

    // -------------------------------------------------
    // 🔑 Primary Key
    // -------------------------------------------------
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // -------------------------------------------------
    // 🔗 Application
    // -------------------------------------------------
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "application_id", nullable = false)
    private LoanApplication application;

    // -------------------------------------------------
    // 🏙 SUPER BUILT-UP AREA
    // -------------------------------------------------
    @Column(name = "sbua_actual")
    private Double sbuaAsPerActual;

    @Column(name = "sbua_document")
    private Double sbuaAsPerDocument;

    @Column(name = "sbua_approved")
    private Double sbuaAsPerApprovedPlan;

    // -------------------------------------------------
    // 🧾 AUDIT
    // -------------------------------------------------
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "created_by", nullable = false)
    private AdminUser createdBy;

    @Column(name = "created_date", nullable = false)
    private LocalDateTime createdDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "updated_by")
    private AdminUser updatedBy;

    @Column(name = "updated_date")
    private LocalDateTime updatedDate;
}
