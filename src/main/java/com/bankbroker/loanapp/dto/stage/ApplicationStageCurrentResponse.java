package com.bankbroker.loanapp.dto.stage;

import lombok.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApplicationStageCurrentResponse {
    private Long id;
    private String applicationId;
    private String stage;
    private String remark;
    private LocalDateTime updatedDate;
    private String updatedByAdminId;
}
