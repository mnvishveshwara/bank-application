package com.bankbroker.loanapp.entity.site_visit;

import com.bankbroker.loanapp.entity.core.AdminUser;
import com.bankbroker.loanapp.entity.core.LoanApplication;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(
        name = "site_visit_technical_additional",
        uniqueConstraints = @UniqueConstraint(columnNames = "application_id")
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SiteVisitTechnicalAdditional {

    // -------------------------------------------------
    //   Primary Key
    // -------------------------------------------------
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // -------------------------------------------------
    //   Application
    // -------------------------------------------------
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "application_id", nullable = false)
    private LoanApplication application;

    // -------------------------------------------------
    //   RISK & CONSTRUCTION PROGRESS
    // -------------------------------------------------
    @Column(name = "risk_of_demolition")
    private String riskOfDemolition;
    // YES / NO / MAYBE

    @Column(name = "construction_progress_percent")
    private Integer constructionProgressPercent;

    @Column(name = "construction_progress_remarks", length = 500)
    private String constructionProgressRemarks;

    // -------------------------------------------------
    //   FUNDING RECOMMENDATION
    // -------------------------------------------------
    @Column(name = "recommended_funding_percent")
    private Integer recommendedFundingPercent;

    // -------------------------------------------------
    //   PROPERTY AGE
    // -------------------------------------------------
    @Column(name = "age_of_property")
    private Integer ageOfProperty;

    @Column(name = "residual_age_of_property")
    private Integer residualAgeOfProperty;

    // -------------------------------------------------
    //   OWNERSHIP DETAILS (FROM DOCUMENT)
    // -------------------------------------------------
    @Column(name = "document_type")
    private String documentType;

    @Column(name = "in_favour_of")
    private String inFavourOf;

    @Column(name = "document_executed_on")
    private LocalDate documentExecutedOn;

    @Column(name = "document_number")
    private String documentNumber;

    // -------------------------------------------------
    //   REGULATORY & MARKET
    // -------------------------------------------------
    @Column(name = "rera_details")
    private String reraDetails;

    @Column(name = "market_feedback")
    private String marketFeedback;

    // -------------------------------------------------
    //   DEVELOPMENT IN VICINITY
    // -------------------------------------------------
    @Column(name = "development_in_vicinity_percent")
    private Integer developmentInVicinityPercent;

    // -------------------------------------------------
    //   COMPLIANCE / OBSERVATIONS
    // -------------------------------------------------
    @Column(name = "earlier_valuation_done")
    private Boolean earlierValuationDone;

    @Column(name = "negative_area_as_per_local_norms")
    private Boolean negativeAreaAsPerLocalNorms;

    @Column(name = "demolition_notification")
    private Boolean demolitionNotification;

    @Column(name = "community_sensitivity")
    private String communitySensitivity;

    @Column(name = "municipal_notification")
    private String municipalNotification;

    // -------------------------------------------------
    //   AUDIT
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
