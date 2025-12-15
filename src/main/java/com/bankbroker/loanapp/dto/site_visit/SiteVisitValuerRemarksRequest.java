package com.bankbroker.loanapp.dto.site_visit;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SiteVisitValuerRemarksRequest {

    private String generalRemarks;
    private String criticalAndActionableRemarks;
    private String insights;
}
