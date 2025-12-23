package com.bankbroker.loanapp.dto.site_visit;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SiteVisitPropertyValueAssessmentLandRequest {

    private Double landAreaAsPerActual;
    private Double landAreaAsPerDocument;
    private Double landAreaAsPerLayoutPlan;

    private Double governmentRatePerSqFt;
    private Double considerationRatePerSqFt;

    private Double fairMarketValueActual;
    private Double fairMarketValueDocument;
    private Double fairMarketValueLayout;
}