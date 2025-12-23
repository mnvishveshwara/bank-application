package com.bankbroker.loanapp.dto.site_visit;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SiteVisitPropertyValueAssessmentAmenityItemRequest {

    private String amenityCategory;
    private String amenityRating;
    private String amenityImpact;

    // UI / rule-engine derived (optional input)
    private Double amenityValue;
}
