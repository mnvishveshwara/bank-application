package com.bankbroker.loanapp.dto.site_visit;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SiteVisitInfrastructureDetailsResponse {

    private String applicationId;

    private Boolean siteAccessibility;
    private String accessibleThrough;
    private String accessibilityType;
    private String roadWidth;

    private Boolean sewerageSystem;
    private Boolean electricity;
    private String waterSupply;
    private Integer numberOfLifts;
}
