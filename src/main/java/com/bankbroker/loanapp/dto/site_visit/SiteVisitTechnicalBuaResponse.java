package com.bankbroker.loanapp.dto.site_visit;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SiteVisitTechnicalBuaResponse {

    private Long id;
    private String applicationId;

    private Integer basements;
    private Integer floors;
    private Integer nonRcc;

    private Double totalBuaActual;
    private Double totalBuaDocument;
    private Double totalBuaApproved;

    private Boolean totalMatch;

    private List<SiteVisitTechnicalBuaLevelResponse> levels;
}
