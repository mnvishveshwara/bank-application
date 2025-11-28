package com.bankbroker.loanapp.dto.stage;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApplicationAgencyAssignmentResponse {
    private Long id;

    private String applicationId;

    private Long agencyId;
    private String agencyName;

    private String createdByAdminId;
    private String updatedByAdminId;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private String remarks;
}
