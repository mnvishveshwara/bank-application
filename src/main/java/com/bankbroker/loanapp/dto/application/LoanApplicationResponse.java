package com.bankbroker.loanapp.dto.application;

import lombok.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoanApplicationResponse {
    private String id;
    private Boolean active;
    private String clientId;
    private String clientName;
    private String createdByAdminId;
    private String createdByName;
    private String assignedToAdminId;
    private String assignedToName;
    private String associatedBank;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
}
