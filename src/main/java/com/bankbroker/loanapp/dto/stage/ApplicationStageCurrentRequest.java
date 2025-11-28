package com.bankbroker.loanapp.dto.stage;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApplicationStageCurrentRequest {
    private String stage; // enum name
    private String remark;
    private String updatedByAdminId;
}
