package com.bankbroker.loanapp.entity.site_visit;

import com.bankbroker.loanapp.entity.core.AdminUser;
import com.bankbroker.loanapp.entity.core.LoanApplication;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "site_visit_technical_details")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SiteVisitTechnicalDetails {

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
    // 🏗 Property Type (Plot / Land / Apartment / etc.)
    // -------------------------------------------------
    @Column(name = "property_type")
    private String propertyType;

    // -------------------------------------------------
    // 📐 LAND / PLOT AREA DETAILS
    // -------------------------------------------------
    @Column(name = "land_area_actual")
    private Double landAreaAsPerActual;

    @Column(name = "land_area_document")
    private Double landAreaAsPerDocument;

    @Column(name = "land_area_layout_plan")
    private Double landAreaAsPerLayoutPlan;

    @Column(name = "land_area_match")
    private Boolean landAreaMatch;

    // -------------------------------------------------
    // 📄 HOLDING TYPE
    // -------------------------------------------------
    @Column(name = "holding_type")
    private String holdingType; // Freehold / Mortgage / Leasehold

    // -------------------------------------------------
    // 🏢 CONFIGURATION (BUA)
    // -------------------------------------------------
    @Column(name = "no_of_basements")
    private Integer basements;

    @Column(name = "no_of_floors")
    private Integer floors;

    @Column(name = "no_of_non_rcc")
    private Integer nonRcc;

    // -------------------------------------------------
    // 📏 FLOOR WISE AREA (BUA)
    // -------------------------------------------------
    @Column(name = "first_basement_actual")
    private Double firstBasementActual;

    @Column(name = "first_basement_document")
    private Double firstBasementDocument;

    @Column(name = "first_basement_approved")
    private Double firstBasementApproved;

    @Column(name = "ground_floor_actual")
    private Double groundFloorActual;

    @Column(name = "ground_floor_document")
    private Double groundFloorDocument;

    @Column(name = "ground_floor_approved")
    private Double groundFloorApproved;

    @Column(name = "first_floor_actual")
    private Double firstFloorActual;

    @Column(name = "first_floor_document")
    private Double firstFloorDocument;

    @Column(name = "first_floor_approved")
    private Double firstFloorApproved;

    @Column(name = "second_floor_actual")
    private Double secondFloorActual;

    @Column(name = "second_floor_document")
    private Double secondFloorDocument;

    @Column(name = "second_floor_approved")
    private Double secondFloorApproved;

    @Column(name = "non_rcc_actual")
    private Double nonRccActual;

    @Column(name = "non_rcc_document")
    private Double nonRccDocument;

    @Column(name = "non_rcc_approved")
    private Double nonRccApproved;

    // -------------------------------------------------
    // ➕ SUMMATION (AUTO CALCULATED)
    // -------------------------------------------------
    @Column(name = "total_bua_actual")
    private Double totalBuaActual;

    @Column(name = "total_bua_document")
    private Double totalBuaDocument;

    @Column(name = "total_bua_approved")
    private Double totalBuaApproved;

    // -------------------------------------------------
    // 🏙 SUPER BUILT UP AREA (SBUA)
    // -------------------------------------------------
    @Column(name = "sbua_actual")
    private Double sbuaActual;

    @Column(name = "sbua_document")
    private Double sbuaDocument;

    @Column(name = "sbua_approved")
    private Double sbuaApproved;

    // -------------------------------------------------
    // ⚠ ADDITIONAL / TECHNICAL OBSERVATIONS
    // -------------------------------------------------
    @Column(name = "risk_of_demolition")
    private String riskOfDemolition; // Yes / No / Maybe

    @Column(name = "construction_progress_percent")
    private Integer constructionProgressPercent;

    @Column(name = "construction_progress_remarks", length = 500)
    private String constructionProgressRemarks;

    @Column(name = "recommended_funding_percent")
    private Integer recommendedFundingPercent;

    @Column(name = "age_of_property")
    private Integer ageOfProperty;

    @Column(name = "residual_age_of_property")
    private Integer residualAgeOfProperty;

    // -------------------------------------------------
    // 📜 OWNERSHIP DETAILS
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
    // 🏛 REGULATORY & MARKET
    // -------------------------------------------------
    @Column(name = "rera_details")
    private String reraDetails;

    @Column(name = "market_feedback")
    private String marketFeedback;

    @Column(name = "development_in_vicinity_percent")
    private Integer developmentInVicinityPercent;

    @Column(name = "earlier_valuation_done")
    private Boolean earlierValuationDone;

    @Column(name = "negative_area_local_norms")
    private Boolean negativeAreaAsPerLocalNorms;

    @Column(name = "demolition_notification")
    private Boolean demolitionNotification;

    @Column(name = "community_sensitivity")
    private String communitySensitivity;

    @Column(name = "municipal_notification")
    private String municipalNotification;

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
