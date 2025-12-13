package com.bankbroker.loanapp.dto.site_visit;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SiteVisitPropertyDetailsResponse {
    private String applicationId;
    private String propertyType;
    private String propertySubType;
    private String jurisdiction;
    private String nearbyLandmark;
}
