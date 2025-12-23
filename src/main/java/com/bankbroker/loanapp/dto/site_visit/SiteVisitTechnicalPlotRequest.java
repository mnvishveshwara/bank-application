package com.bankbroker.loanapp.dto.site_visit;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SiteVisitTechnicalPlotRequest {

    private String propertyType;

    private String eastAsPerSiteVisit;
    private String eastAsPerLegalDocument;

    private String westAsPerSiteVisit;
    private String westAsPerLegalDocument;

    private String northAsPerSiteVisit;
    private String northAsPerLegalDocument;

    private String southAsPerSiteVisit;
    private String southAsPerLegalDocument;
}
