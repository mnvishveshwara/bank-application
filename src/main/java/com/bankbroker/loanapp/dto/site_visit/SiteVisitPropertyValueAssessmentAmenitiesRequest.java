package com.bankbroker.loanapp.dto.site_visit;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SiteVisitPropertyValueAssessmentAmenitiesRequest {

    private List<SiteVisitPropertyValueAssessmentAmenityItemRequest> items;
}
