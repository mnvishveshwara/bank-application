package com.bankbroker.loanapp.entity.site_visit;

import com.bankbroker.loanapp.entity.AdminUser;
import com.bankbroker.loanapp.entity.LoanApplication;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "site_visit_building_details")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SiteVisitBuildingDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // -------------------------------------------------
    // 🔗 Application
    // -------------------------------------------------
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "application_id", nullable = false, unique = true)
    private LoanApplication application;

    // -------------------------------------------------
    // 🏗 Construction Details (Construction Tab)
    // -------------------------------------------------
    @Column(name = "construction_type")
    private String constructionType;          // RCC / Load Bearing / Steel etc.

    @Column(name = "construction_quality")
    private String constructionQuality;        // Good / Average / Poor

    @Column(name = "floor_type")
    private String floorType;                  // Vitrified / Marble / Cement

    @Column(name = "roof_type")
    private String roofType;                   // RCC / Sheet / Tile

    @Column(name = "star_type")
    private String starType;                   // 1 Star / 2 Star / NA

    // -------------------------------------------------
    // 📐 Plan Details (Plan Tab – Future Ready)
    // -------------------------------------------------
    @Column(name = "approved_plan_available")
    private Boolean approvedPlanAvailable;

    @Column(name = "plan_issuing_authority")
    private String planIssuingAuthority;

    @Column(name = "plan_approval_number")
    private String planApprovalNumber;

    @Column(name = "plan_approval_date")
    private LocalDateTime planApprovalDate;

    @Column(name = "plan_deviation_observed")
    private Boolean planDeviationObserved;

    @Column(name = "plan_deviation_remarks")
    private String planDeviationRemarks;

    // -------------------------------------------------
    // 🧾 Audit
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
