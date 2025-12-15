package com.bankbroker.loanapp.entity.site_visit;

import com.bankbroker.loanapp.entity.LoanApplication;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "site_visit_property_value_assessment")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SiteVisitPropertyValueAssessment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // -------------------------------------------------
    // 🔗 Application Mapping
    // -------------------------------------------------
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "application_id", nullable = false, unique = true)
    private LoanApplication application;

    // =================================================
    // 🟩 LAND TAB
    // =================================================

    // Area (Sq.Ft)
    @Column(name = "land_area_actual")
    private BigDecimal landAreaAsPerActual;

    @Column(name = "land_area_document")
    private BigDecimal landAreaAsPerDocument;

    @Column(name = "land_area_layout")
    private BigDecimal landAreaAsPerLayout;

    // Rates (Per Sq.Ft)
    @Column(name = "government_price_per_sqft")
    private BigDecimal governmentPricePerSqFt;

    @Column(name = "consideration_price_per_sqft")
    private BigDecimal considerationPricePerSqFt;

    // Total Value – Government Price
    @Column(name = "govt_value_actual")
    private BigDecimal govtValueAsPerActual;

    @Column(name = "govt_value_document")
    private BigDecimal govtValueAsPerDocument;

    @Column(name = "govt_value_layout")
    private BigDecimal govtValueAsPerLayout;

    // Total Value – Consideration Price
    @Column(name = "consideration_value_actual")
    private BigDecimal considerationValueAsPerActual;

    @Column(name = "consideration_value_document")
    private BigDecimal considerationValueAsPerDocument;

    @Column(name = "consideration_value_layout")
    private BigDecimal considerationValueAsPerLayout;

    // Total Value – Fair Market
    @Column(name = "fair_market_value_actual")
    private BigDecimal fairMarketValueAsPerActual;

    @Column(name = "fair_market_value_document")
    private BigDecimal fairMarketValueAsPerDocument;

    @Column(name = "fair_market_value_layout")
    private BigDecimal fairMarketValueAsPerLayout;

    // =================================================
    // 🟩 AMENITIES TAB
    // =================================================

    @Column(name = "amenity_category")
    private String amenityCategory;

    @Column(name = "amenity_rating")
    private String amenityRating;

    @Column(name = "amenity_impact")
    private String amenityImpact;

    @Column(name = "amenities_total_value")
    private BigDecimal amenitiesTotalValue;

    // =================================================
    // 🟩 FINAL VALUATION TAB
    // =================================================

    // FMV as on Date
    @Column(name = "fmv_date_actual")
    private BigDecimal fmvDateAsPerActual;

    @Column(name = "fmv_date_document")
    private BigDecimal fmvDateAsPerDocument;

    @Column(name = "fmv_date_layout")
    private BigDecimal fmvDateAsPerLayout;

    // FMV on Completion
    @Column(name = "fmv_completion_actual")
    private BigDecimal fmvCompletionAsPerActual;

    @Column(name = "fmv_completion_document")
    private BigDecimal fmvCompletionAsPerDocument;

    @Column(name = "fmv_completion_layout")
    private BigDecimal fmvCompletionAsPerLayout;

    // Distressed Sale Value
    @Column(name = "distressed_value_actual")
    private BigDecimal distressedValueAsPerActual;

    @Column(name = "distressed_value_document")
    private BigDecimal distressedValueAsPerDocument;

    @Column(name = "distressed_value_layout")
    private BigDecimal distressedValueAsPerLayout;

    // Guideline / Circle Value
    @Column(name = "guideline_value_actual")
    private BigDecimal guidelineValueAsPerActual;

    @Column(name = "guideline_value_document")
    private BigDecimal guidelineValueAsPerDocument;

    @Column(name = "guideline_value_layout")
    private BigDecimal guidelineValueAsPerLayout;

    // Insurance Value
    @Column(name = "insurance_value_actual")
    private BigDecimal insuranceValueAsPerActual;

    @Column(name = "insurance_value_document")
    private BigDecimal insuranceValueAsPerDocument;

    @Column(name = "insurance_value_layout")
    private BigDecimal insuranceValueAsPerLayout;

    // Final Value
    @Column(name = "final_value_considered")
    private BigDecimal finalValueToBeConsidered;

    // =================================================
    // 🟩 SUMMARY TABLE (Bottom Section)
    // =================================================

    @Column(name = "building_total_value")
    private BigDecimal buildingTotalValue;

    @Column(name = "building_considered_value")
    private BigDecimal buildingConsideredValue;

    @Column(name = "amenities_considered_value")
    private BigDecimal amenitiesConsideredValue;

    @Column(name = "grand_final_value")
    private BigDecimal grandFinalValue;
}
