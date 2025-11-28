package com.bankbroker.loanapp.dto.stage;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApplicationPropertyDetailsRequest {
    private ResidentialAddressDTO residentialAddress;
    private PropertyAddressDTO propertyAddress;
}
