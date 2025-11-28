package com.bankbroker.loanapp.dto.stage;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PropertyAddressDTO {

    private String doorOrApartmentNumber;
    private String buildingOrApartmentName;
    private String streetLine1;
    private String streetLine2;
    private String pinCode;
    private String city;
    private String state;
    private String country;
}
