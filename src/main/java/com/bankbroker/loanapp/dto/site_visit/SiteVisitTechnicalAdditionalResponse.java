package com.bankbroker.loanapp.dto.site_visit;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SiteVisitTechnicalAdditionalResponse {

    private Long id;
    private String applicationId;

    private String riskOfDemolition;
    private Integer constructionProgressPercent;
    private String constructionProgressRemarks;

    private Integer recommendedFundingPercent;

    private Integer ageOfProperty;
    private Integer residualAgeOfProperty;

    private String documentType;
    private String inFavourOf;
    private LocalDate documentExecutedOn;
    private String documentNumber;

    private String reraDetails;
    private String marketFeedback;

    private Integer developmentInVicinityPercent;

    private Boolean earlierValuationDone;
    private Boolean negativeAreaAsPerLocalNorms;
    private Boolean demolitionNotification;

    private String communitySensitivity;
    private String municipalNotification;
}
