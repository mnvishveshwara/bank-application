package com.bankbroker.loanapp.dto.site_visit;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SiteVisitTechnicalBuaRequest {

    private Integer basements;
    private Integer floors;
    private Integer nonRcc;

    private List<SiteVisitTechnicalBuaLevelRequest> levels;
}
