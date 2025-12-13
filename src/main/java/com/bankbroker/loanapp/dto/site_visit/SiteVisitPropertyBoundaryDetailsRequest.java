package com.bankbroker.loanapp.dto.site_visit;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SiteVisitPropertyBoundaryDetailsRequest {

    private String propertyFacing;

    private String eastAsPerSiteVisit;
    private String eastAsPerLegalDocument;
    private Boolean eastMatch;

    private String southAsPerSiteVisit;
    private String southAsPerLegalDocument;
    private Boolean southMatch;

    private String westAsPerSiteVisit;
    private String westAsPerLegalDocument;
    private Boolean westMatch;

    private String northAsPerSiteVisit;
    private String northAsPerLegalDocument;
    private Boolean northMatch;

    private Boolean boundaryMatching;
    private Boolean earthquakeResistant;
    private Boolean propertyIdentification;

    private String currentZoning;
}
