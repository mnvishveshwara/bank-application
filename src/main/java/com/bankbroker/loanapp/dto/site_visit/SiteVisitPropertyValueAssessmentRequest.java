package com.bankbroker.loanapp.dto.site_visit;


import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SiteVisitPropertyValueAssessmentRequest {

    // ---------------- LAND ----------------
    private BigDecimal landAreaAsPerActual;
    private BigDecimal landAreaAsPerDocument;
    private BigDecimal landAreaAsPerLayout;

    private BigDecimal governmentPricePerSqFt;
    private BigDecimal considerationPricePerSqFt;

    private BigDecimal govtValueAsPerActual;
    private BigDecimal govtValueAsPerDocument;
    private BigDecimal govtValueAsPerLayout;

    private BigDecimal considerationValueAsPerActual;
    private BigDecimal considerationValueAsPerDocument;
    private BigDecimal considerationValueAsPerLayout;

    private BigDecimal fairMarketValueAsPerActual;
    private BigDecimal fairMarketValueAsPerDocument;
    private BigDecimal fairMarketValueAsPerLayout;

    // ---------------- AMENITIES ----------------
    private String amenityCategory;
    private String amenityRating;
    private String amenityImpact;
    private BigDecimal amenitiesTotalValue;

    // ---------------- FINAL VALUATION ----------------
    private BigDecimal fmvDateAsPerActual;
    private BigDecimal fmvDateAsPerDocument;
    private BigDecimal fmvDateAsPerLayout;

    private BigDecimal fmvCompletionAsPerActual;
    private BigDecimal fmvCompletionAsPerDocument;
    private BigDecimal fmvCompletionAsPerLayout;

    private BigDecimal distressedValueAsPerActual;
    private BigDecimal distressedValueAsPerDocument;
    private BigDecimal distressedValueAsPerLayout;

    private BigDecimal guidelineValueAsPerActual;
    private BigDecimal guidelineValueAsPerDocument;
    private BigDecimal guidelineValueAsPerLayout;

    private BigDecimal insuranceValueAsPerActual;
    private BigDecimal insuranceValueAsPerDocument;
    private BigDecimal insuranceValueAsPerLayout;

    private BigDecimal finalValueToBeConsidered;

    // ---------------- SUMMARY ----------------
    private BigDecimal buildingTotalValue;
    private BigDecimal buildingConsideredValue;
    private BigDecimal amenitiesConsideredValue;
    private BigDecimal grandFinalValue;
}