package com.bankbroker.loanapp.dto.site_visit;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SiteVisitPropertyDetailsRequest {

    // Postal Address
    private String postalDoorNo;
    private String postalBuildingName;
    private String postalStreetLine1;
    private String postalStreetLine2;
    private String postalPinCode;
    private String postalCity;
    private String postalState;

    // Document Address
    private String documentDoorNo;
    private String documentBuildingName;
    private String documentStreetLine1;
    private String documentStreetLine2;
    private String documentPinCode;
    private String documentCity;
    private String documentState;

    // Geo
    private Double latitude;
    private Double longitude;
    private Double distanceFromCityCentre;

    // Jurisdiction
    private String jurisdiction;
    private String nearbyLandmark;

    // Property Info
    private String propertyType;
    private String propertySubType;
}
