package com.bankbroker.loanapp.dto.site_visit;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SiteVisitPropertyValueAssessmentFinalValuationResponse {

    private Long id;
    private String applicationId;

    private Double fmvOnDateActual;
    private Double fmvOnDateDocument;
    private Double fmvOnDateLayout;

    private Double fmvOnCompletionActual;
    private Double fmvOnCompletionDocument;
    private Double fmvOnCompletionLayout;

    private Double distressedValueActual;
    private Double distressedValueDocument;
    private Double distressedValueLayout;

    private Double guidelineValueActual;
    private Double guidelineValueDocument;
    private Double guidelineValueLayout;

    private Double insuranceValueActual;
    private Double insuranceValueDocument;
    private Double insuranceValueLayout;

    private Double finalValueConsidered;
}
