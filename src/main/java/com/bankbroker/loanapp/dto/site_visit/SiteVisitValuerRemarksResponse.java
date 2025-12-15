package com.bankbroker.loanapp.dto.site_visit;


import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SiteVisitValuerRemarksResponse {

    private Long id;
    private String applicationId;

    private String generalRemarks;
    private String criticalAndActionableRemarks;
    private String insights;

}