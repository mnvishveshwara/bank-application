package com.bankbroker.loanapp.dto.stage;

import lombok.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApplicationSummaryResponse {
    private Long id;
    private String applicationId;
    private String summaryText;
    private String reviewedByAdminId;
    private LocalDateTime reviewedDate;
}
