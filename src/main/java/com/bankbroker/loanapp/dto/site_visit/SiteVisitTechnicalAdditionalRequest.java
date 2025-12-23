package com.bankbroker.loanapp.dto.site_visit;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SiteVisitTechnicalAdditionalRequest {

    // Risk & Progress
    private String riskOfDemolition; // YES / NO / MAYBE
    private Integer constructionProgressPercent;
    private String constructionProgressRemarks;

    // Funding
    private Integer recommendedFundingPercent;

    // Property Age
    private Integer ageOfProperty;
    private Integer residualAgeOfProperty;

    // Ownership
    private String documentType;
    private String inFavourOf;
    private LocalDate documentExecutedOn;
    private String documentNumber;

    // Regulatory & Market
    private String reraDetails;
    private String marketFeedback;

    // Vicinity
    private Integer developmentInVicinityPercent;

    // Compliance
    private Boolean earlierValuationDone;
    private Boolean negativeAreaAsPerLocalNorms;
    private Boolean demolitionNotification;

    private String communitySensitivity;
    private String municipalNotification;
}
