package com.bankbroker.loanapp.dto.site_visit;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SiteVisitTechnicalBuaLevelResponse {

    private Long id;
    private String levelType;
    private Integer levelOrder;

    private Double areaActual;
    private Double areaDocument;
    private Double areaApproved;

    private Boolean match;
}
