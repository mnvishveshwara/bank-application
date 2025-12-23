package com.bankbroker.loanapp.dto.site_visit;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SiteVisitPropertyValueAssessmentFinalValuationRequest {

    // FMV as on Date
    private Double fmvOnDateActual;
    private Double fmvOnDateDocument;
    private Double fmvOnDateLayout;

    // FMV on Completion
    private Double fmvOnCompletionActual;
    private Double fmvOnCompletionDocument;
    private Double fmvOnCompletionLayout;

    // Distressed Sale Value
    private Double distressedValueActual;
    private Double distressedValueDocument;
    private Double distressedValueLayout;

    // Guideline / Circle Value
    private Double guidelineValueActual;
    private Double guidelineValueDocument;
    private Double guidelineValueLayout;

    // Insurance Value
    private Double insuranceValueActual;
    private Double insuranceValueDocument;
    private Double insuranceValueLayout;

    // Final Value (can be auto / overridden)
    private Double finalValueConsidered;
}
