package com.bankbroker.loanapp.dto.site_visit;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PropertyOwnerDetailsResponse {
    private String applicantName;
    private LocalDate reportDate;
    private String loanType;

}
