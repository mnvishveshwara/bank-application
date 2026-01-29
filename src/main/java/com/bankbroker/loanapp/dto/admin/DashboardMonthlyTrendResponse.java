package com.bankbroker.loanapp.dto.admin;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DashboardMonthlyTrendResponse {

    private Integer day;
    private Long totalApplications;
}