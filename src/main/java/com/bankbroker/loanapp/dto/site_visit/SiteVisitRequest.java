package com.bankbroker.loanapp.dto.site_visit;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SiteVisitRequest {
    @NotNull(message = "Site visit date is required")
    private LocalDate siteVisitDate;
    private String remarks;
}