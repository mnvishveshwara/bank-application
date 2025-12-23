package com.bankbroker.loanapp.dto.site_visit;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SiteVisitTechnicalBuaLevelRequest {

    private String levelType;     // BASEMENT / GROUND_FLOOR / FIRST_FLOOR / NON_RCC
    private Integer levelOrder;   // 0,1,2...

    private Double areaActual;
    private Double areaDocument;
    private Double areaApproved;
}
