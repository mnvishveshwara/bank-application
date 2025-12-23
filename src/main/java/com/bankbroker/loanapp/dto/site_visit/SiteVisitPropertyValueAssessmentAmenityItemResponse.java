package com.bankbroker.loanapp.dto.site_visit;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SiteVisitPropertyValueAssessmentAmenityItemResponse {

    private Long id;
    private String amenityCategory;
    private String amenityRating;
    private String amenityImpact;
    private Double amenityValue;
}
