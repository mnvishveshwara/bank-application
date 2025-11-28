package com.bankbroker.loanapp.dto.stage;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApplicationAgencyAssignmentRequest {

    private Long applicationId;
    private Long id;
    private Long agencyId;
    private String remarks;
}
