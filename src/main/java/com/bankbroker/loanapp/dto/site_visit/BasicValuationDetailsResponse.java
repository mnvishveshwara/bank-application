package com.bankbroker.loanapp.dto.site_visit;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BasicValuationDetailsResponse {

    private Long id;
    private String applicationId;

    private String propertyOwnerName;
    private String relationshipWithApplicant;
    private LocalDate reportDate;
    private String loanType;
    private String personMetAtSite;
    private List<String> documentsProvided;

}
