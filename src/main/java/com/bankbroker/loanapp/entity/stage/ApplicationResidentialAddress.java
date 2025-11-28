package com.bankbroker.loanapp.entity.stage;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApplicationResidentialAddress {

    @Column(name = "res_door_or_apartment_number")
    private String doorOrApartmentNumber;

    @Column(name = "res_building_or_apartment_name")
    private String buildingOrApartmentName;

    @Column(name = "res_street_line_1")
    private String streetLine1;

    @Column(name = "res_street_line_2")
    private String streetLine2;

    @Column(name = "res_pincode")
    private String pinCode;

    @Column(name = "res_city")
    private String city;

    @Column(name = "res_state")
    private String state;

    @Column(name = "res_country")
    private String country;
}
