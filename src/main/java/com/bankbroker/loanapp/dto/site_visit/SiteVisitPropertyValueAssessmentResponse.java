package com.bankbroker.loanapp.dto.site_visit;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SiteVisitPropertyValueAssessmentResponse {

    private Long id;
    private String applicationId;

    private BigDecimal finalValueToBeConsidered;
    private BigDecimal buildingTotalValue;
    private BigDecimal buildingConsideredValue;
    private BigDecimal amenitiesConsideredValue;
    private BigDecimal grandFinalValue;
}