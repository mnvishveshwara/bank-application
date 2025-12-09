package com.bankbroker.loanapp.dto.valuator;

import lombok.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AssignValuatorResponse {

    private Long id;
    private String applicationId;

    private String valuatorId;
    private String valuatorName;

    private String assignedBy;
    private String remarks;

    private LocalDateTime assignedDate;
    private LocalDateTime updatedDate;
}
