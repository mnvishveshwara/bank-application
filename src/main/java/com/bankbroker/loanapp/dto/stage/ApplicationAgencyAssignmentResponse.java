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

    private String createdByAdminId;   // <-- ADD THIS
    private String updatedByAdminId;   // <-- ADD THIS

    private LocalDateTime createdAt;   // <-- ADD THIS
    private LocalDateTime updatedAt;   // <-- ADD THIS

    private String remarks;
}
