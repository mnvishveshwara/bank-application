package com.bankbroker.loanapp.dto.site_visit;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SiteVisitTechnicalPlotResponse {

    private Long id;
    private String applicationId;

    private String propertyType;

    private String eastAsPerSiteVisit;
    private String eastAsPerLegalDocument;
    private Boolean eastMatch;

    private String westAsPerSiteVisit;
    private String westAsPerLegalDocument;
    private Boolean westMatch;

    private String northAsPerSiteVisit;
    private String northAsPerLegalDocument;
    private Boolean northMatch;

    private String southAsPerSiteVisit;
    private String southAsPerLegalDocument;
    private Boolean southMatch;
}
