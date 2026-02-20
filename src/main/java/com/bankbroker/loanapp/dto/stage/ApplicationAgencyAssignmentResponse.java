package com.bankbroker.loanapp.dto.stage;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApplicationAgencyAssignmentResponse {
    private Long id; // Will be null for INTERNAL assignments
    private String applicationId;

    // --- Agency Specific ---
    private Long agencyId;
    private String agencyName;

    // --- Internal Specific (New) ---
    private String internalValuatorId;
    private String internalValuatorName;
    private String assignmentType; // "AGENCY" or "INTERNAL"

    private String createdByAdminId;
    private String updatedByAdminId;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private String remarks;
}