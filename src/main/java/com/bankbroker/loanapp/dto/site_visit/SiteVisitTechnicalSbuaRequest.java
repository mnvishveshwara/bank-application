package com.bankbroker.loanapp.dto.site_visit;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SiteVisitTechnicalSbuaRequest {

    private Double sbuaAsPerActual;
    private Double sbuaAsPerDocument;
    private Double sbuaAsPerApprovedPlan;
}
