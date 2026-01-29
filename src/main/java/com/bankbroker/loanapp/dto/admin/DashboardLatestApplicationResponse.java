package com.bankbroker.loanapp.dto.admin;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class DashboardLatestApplicationResponse {

    private String applicationId;
    private String applicantName;
    private String bankName;
    private String agencyName;
    private LocalDateTime updatedDate;
}