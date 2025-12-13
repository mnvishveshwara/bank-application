package com.bankbroker.loanapp.dto.site_visit;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SiteVisitBuildingDetailsResponse {

    private String applicationId;

    private String constructionType;
    private String constructionQuality;
    private String floorType;
    private String roofType;
    private String starType;

    private Boolean approvedPlanAvailable;
    private Boolean planDeviationObserved;
}
