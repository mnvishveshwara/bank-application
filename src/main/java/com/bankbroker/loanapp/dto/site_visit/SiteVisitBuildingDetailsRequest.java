package com.bankbroker.loanapp.dto.site_visit;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SiteVisitBuildingDetailsRequest {

    // Construction
    private String constructionType;
    private String constructionQuality;
    private String floorType;
    private String roofType;
    private String starType;

    // Plan (future-ready)
    private Boolean approvedPlanAvailable;
    private String planIssuingAuthority;
    private String planApprovalNumber;
    private LocalDateTime planApprovalDate;
    private Boolean planDeviationObserved;
    private String planDeviationRemarks;
}
