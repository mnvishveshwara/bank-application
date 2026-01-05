package com.bankbroker.loanapp.dto.stage;

import lombok.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApplicationHistoryResponse {
    private Long id;
    private String applicationId;
    private String status;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
    private String remarks;
}
