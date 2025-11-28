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
public class ApplicationPropertyAddress {

    @Column(name = "prop_door_or_apartment_number")
    private String doorOrApartmentNumber;

    @Column(name = "prop_building_or_apartment_name")
    private String buildingOrApartmentName;

    @Column(name = "prop_street_line_1")
    private String streetLine1;

    @Column(name = "prop_street_line_2")
    private String streetLine2;

    @Column(name = "prop_pincode")
    private String pinCode;

    @Column(name = "prop_city")
    private String city;

    @Column(name = "prop_state")
    private String state;

    @Column(name = "prop_country")
    private String country;
}
