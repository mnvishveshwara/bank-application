package com.bankbroker.loanapp.dto.site_visit;
import lombok.*;


@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class SiteVisitPropertyBoundaryDetailsResponse {
    private String applicationId;
    private String propertyFacing;
    private Boolean boundaryMatching;
    private Boolean earthquakeResistant;
    private Boolean propertyIdentification;
    private String currentZoning;
}