package com.bankbroker.loanapp.dto.site_visit;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SiteVisitTechnicalDetailsRequest {

    // Property Type
    private String propertyType;

    // Land / Plot Area
    private Double landAreaAsPerActual;
    private Double landAreaAsPerDocument;
    private Double landAreaAsPerLayoutPlan;

    private String holdingType;

    // Configuration
    private Integer basements;
    private Integer floors;
    private Integer nonRcc;

    // BUA – Floor wise
    private Double firstBasementActual;
    private Double firstBasementDocument;
    private Double firstBasementApproved;

    private Double groundFloorActual;
    private Double groundFloorDocument;
    private Double groundFloorApproved;

    private Double firstFloorActual;
    private Double firstFloorDocument;
    private Double firstFloorApproved;

    private Double secondFloorActual;
    private Double secondFloorDocument;
    private Double secondFloorApproved;

    private Double nonRccActual;
    private Double nonRccDocument;
    private Double nonRccApproved;

    // SBUA
    private Double sbuaActual;
    private Double sbuaDocument;
    private Double sbuaApproved;

    // Additional
    private String riskOfDemolition;
    private Integer constructionProgressPercent;
    private String constructionProgressRemarks;
    private Integer recommendedFundingPercent;

    private Integer ageOfProperty;
    private Integer residualAgeOfProperty;

    // Ownership
    private String documentType;
    private String inFavourOf;
    private LocalDate documentExecutedOn;
    private String documentNumber;

    // Regulatory
    private String reraDetails;
    private String marketFeedback;
    private Integer developmentInVicinityPercent;

    private Boolean earlierValuationDone;
    private Boolean negativeAreaAsPerLocalNorms;
    private Boolean demolitionNotification;

    private String communitySensitivity;
    private String municipalNotification;
}
