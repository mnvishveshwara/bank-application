package com.bankbroker.loanapp.dto.site_visit;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SiteVisitTechnicalSbuaResponse {

    private Long id;
    private String applicationId;

    private Double sbuaAsPerActual;
    private Double sbuaAsPerDocument;
    private Double sbuaAsPerApprovedPlan;

    // Derived
    private Boolean match;
}
