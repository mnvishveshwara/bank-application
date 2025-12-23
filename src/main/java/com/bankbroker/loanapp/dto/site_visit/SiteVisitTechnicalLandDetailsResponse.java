package com.bankbroker.loanapp.dto.site_visit;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SiteVisitTechnicalLandDetailsResponse {

    private Long landId;
    private String applicationId;

    private Double landAreaAsPerActual;
    private Double landAreaAsPerDocument;
    private Double landAreaAsPerLayoutPlan;
    private Boolean landAreaMatch;
    private String holdingType;

    private List<SiteVisitTechnicalLandImageResponse> images;
}
