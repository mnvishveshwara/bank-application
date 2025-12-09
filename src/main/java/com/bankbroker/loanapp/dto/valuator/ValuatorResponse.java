package com.bankbroker.loanapp.dto.valuator;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ValuatorResponse {
    private Long id;
    private String valuatorName;
    private String contactNumber;
    private String email;
    private Long agencyId;
    private String agencyName;
    private String  loginUserId;   // AdminUser ID
}
