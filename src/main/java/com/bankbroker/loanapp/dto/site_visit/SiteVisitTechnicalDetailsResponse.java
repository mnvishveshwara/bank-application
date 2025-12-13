package com.bankbroker.loanapp.dto.site_visit;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SiteVisitTechnicalDetailsResponse {

    private Long id;
    private String applicationId;

    private String propertyType;

    private Double landAreaAsPerActual;
    private Double landAreaAsPerDocument;
    private Double landAreaAsPerLayoutPlan;
    private Boolean landAreaMatch;

    private String holdingType;

    private Integer basements;
    private Integer floors;
    private Integer nonRcc;

    private Double totalBuaActual;
    private Double totalBuaDocument;
    private Double totalBuaApproved;

    private Double sbuaActual;
    private Double sbuaDocument;
    private Double sbuaApproved;

    private String riskOfDemolition;
    private Integer constructionProgressPercent;
    private Integer recommendedFundingPercent;

    private Integer ageOfProperty;
    private Integer residualAgeOfProperty;
}
