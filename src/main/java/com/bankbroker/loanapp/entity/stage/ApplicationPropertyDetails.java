package com.bankbroker.loanapp.entity.stage;

import com.bankbroker.loanapp.entity.core.LoanApplication;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "application_property_details")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApplicationPropertyDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "application_id", nullable = false, unique = true)
    private LoanApplication application;

    // Embedded residential address
    @Embedded
    private ApplicationResidentialAddress residentialAddress;

    // Embedded property address
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "doorOrApartmentNumber", column = @Column(name = "prop_door_or_apartment_number")),
            @AttributeOverride(name = "buildingOrApartmentName", column = @Column(name = "prop_building_or_apartment_name")),
            @AttributeOverride(name = "streetLine1", column = @Column(name = "prop_street_line_1")),
            @AttributeOverride(name = "streetLine2", column = @Column(name = "prop_street_line_2")),
            @AttributeOverride(name = "pinCode", column = @Column(name = "prop_pincode")),
            @AttributeOverride(name = "city", column = @Column(name = "prop_city")),
            @AttributeOverride(name = "state", column = @Column(name = "prop_state")),
            @AttributeOverride(name = "country", column = @Column(name = "prop_country"))
    })
    private ApplicationPropertyAddress propertyAddress;
}
