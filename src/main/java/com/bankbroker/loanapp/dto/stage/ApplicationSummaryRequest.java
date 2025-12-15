package com.bankbroker.loanapp.dto.stage;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApplicationSummaryRequest {
    private String summary;
}
