package com.bankbroker.loanapp.dto.site_visit;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SiteVisitPropertyValueAssessmentAmenitiesResponse {

    private Long id;
    private String applicationId;

    private Double totalAmenitiesValue;

    private List<SiteVisitPropertyValueAssessmentAmenityItemResponse> items;
}
