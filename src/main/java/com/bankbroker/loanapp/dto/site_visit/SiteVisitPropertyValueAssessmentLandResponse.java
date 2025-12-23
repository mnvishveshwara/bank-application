package com.bankbroker.loanapp.dto.site_visit;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SiteVisitPropertyValueAssessmentLandResponse {

    private Long id;
    private String applicationId;

    // Area
    private Double landAreaAsPerActual;
    private Double landAreaAsPerDocument;
    private Double landAreaAsPerLayoutPlan;

    // Rates
    private Double governmentRatePerSqFt;
    private Double considerationRatePerSqFt;

    // Government Total Values
    private Double govtTotalValueActual;
    private Double govtTotalValueDocument;
    private Double govtTotalValueLayout;

    // Consideration Total Values
    private Double considerationTotalValueActual;
    private Double considerationTotalValueDocument;
    private Double considerationTotalValueLayout;

    // Fair Market Values
    private Double fairMarketValueActual;
    private Double fairMarketValueDocument;
    private Double fairMarketValueLayout;
}