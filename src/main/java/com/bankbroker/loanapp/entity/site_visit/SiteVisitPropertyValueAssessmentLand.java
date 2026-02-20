package com.bankbroker.loanapp.entity.site_visit;

import com.bankbroker.loanapp.entity.core.AdminUser;
import com.bankbroker.loanapp.entity.core.LoanApplication;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(
        name = "site_visit_property_value_assessment_land",
        uniqueConstraints = @UniqueConstraint(columnNames = "application_id")
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SiteVisitPropertyValueAssessmentLand {

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
    //   AREA DETAILS (Sq.ft)
    // -------------------------------------------------
    @Column(name = "land_area_actual")
    private Double landAreaAsPerActual;

    @Column(name = "land_area_document")
    private Double landAreaAsPerDocument;

    @Column(name = "land_area_layout_plan")
    private Double landAreaAsPerLayoutPlan;

    // -------------------------------------------------
    //   RATE DETAILS (₹ / Sq.ft)
    // -------------------------------------------------
    @Column(name = "government_rate_per_sqft")
    private Double governmentRatePerSqFt;

    @Column(name = "consideration_rate_per_sqft")
    private Double considerationRatePerSqFt;

    // -------------------------------------------------
    //   TOTAL VALUE – GOVERNMENT PRICE
    // -------------------------------------------------
    @Column(name = "govt_total_value_actual")
    private Double govtTotalValueActual;

    @Column(name = "govt_total_value_document")
    private Double govtTotalValueDocument;

    @Column(name = "govt_total_value_layout")
    private Double govtTotalValueLayout;

    // -------------------------------------------------
    //   TOTAL VALUE – CONSIDERATION PRICE
    // -------------------------------------------------
    @Column(name = "consideration_total_value_actual")
    private Double considerationTotalValueActual;

    @Column(name = "consideration_total_value_document")
    private Double considerationTotalValueDocument;

    @Column(name = "consideration_total_value_layout")
    private Double considerationTotalValueLayout;

    // -------------------------------------------------
    //   TOTAL VALUE – FAIR MARKET VALUE
    // -------------------------------------------------
    @Column(name = "fair_market_value_actual")
    private Double fairMarketValueActual;

    @Column(name = "fair_market_value_document")
    private Double fairMarketValueDocument;

    @Column(name = "fair_market_value_layout")
    private Double fairMarketValueLayout;

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
