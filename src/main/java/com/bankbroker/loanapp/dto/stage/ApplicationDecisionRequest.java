package com.bankbroker.loanapp.dto.stage;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApplicationDecisionRequest {

    private String status;

    private String remarks;
}