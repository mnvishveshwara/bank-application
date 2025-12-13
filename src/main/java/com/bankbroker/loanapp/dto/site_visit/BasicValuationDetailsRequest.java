package com.bankbroker.loanapp.dto.site_visit;

import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BasicValuationDetailsRequest {

    private String propertyOwnerName;
    private String relationshipWithApplicant;
    private LocalDate reportDate;
    private String loanType;
    private String personMetAtSite;
    private List<String> documentsProvided;
}
