package com.bankbroker.loanapp.dto.stage;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApplicationPropertyDetailsResponse {
    private Long id;
    private String applicationId;
    private ResidentialAddressDTO residentialAddress;
    private PropertyAddressDTO propertyAddress;
}
