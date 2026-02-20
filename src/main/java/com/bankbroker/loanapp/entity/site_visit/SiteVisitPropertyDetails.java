package com.bankbroker.loanapp.entity.site_visit;

import com.bankbroker.loanapp.entity.core.AdminUser;
import com.bankbroker.loanapp.entity.core.LoanApplication;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "site_visit_property_details")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SiteVisitPropertyDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // -------------------------------------------------
    //   Application
    // -------------------------------------------------
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "application_id", nullable = false, unique = true)
    private LoanApplication application;

    // -------------------------------------------------
    //   Postal Address
    // -------------------------------------------------
    @Column(name = "postal_door_no")
    private String postalDoorNo;

    @Column(name = "postal_building_name")
    private String postalBuildingName;

    @Column(name = "postal_street_line1")
    private String postalStreetLine1;

    @Column(name = "postal_street_line2")
    private String postalStreetLine2;

    @Column(name = "postal_pin_code")
    private String postalPinCode;

    @Column(name = "postal_city")
    private String postalCity;

    @Column(name = "postal_state")
    private String postalState;

    // -------------------------------------------------
    //   Address As Per Document
    // -------------------------------------------------
    @Column(name = "doc_door_no")
    private String documentDoorNo;

    @Column(name = "doc_building_name")
    private String documentBuildingName;

    @Column(name = "doc_street_line1")
    private String documentStreetLine1;

    @Column(name = "doc_street_line2")
    private String documentStreetLine2;

    @Column(name = "doc_pin_code")
    private String documentPinCode;

    @Column(name = "doc_city")
    private String documentCity;

    @Column(name = "doc_state")
    private String documentState;

    // -------------------------------------------------
    //   Geo Coordinates
    // -------------------------------------------------
    @Column(name = "latitude")
    private Double latitude;

    @Column(name = "longitude")
    private Double longitude;

    @Column(name = "distance_from_city_centre")
    private Double distanceFromCityCentre;

    // -------------------------------------------------
    //   Jurisdiction & Landmark
    // -------------------------------------------------
    @Column(name = "jurisdiction")
    private String jurisdiction;

    @Column(name = "nearby_landmark")
    private String nearbyLandmark;

    // -------------------------------------------------
    //   Property Info
    // -------------------------------------------------
    @Column(name = "property_type")
    private String propertyType;

    @Column(name = "property_sub_type")
    private String propertySubType;

    // -------------------------------------------------
    //   Audit
    // -------------------------------------------------
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "created_by", nullable = false)
    private AdminUser createdBy;

    @Column(name = "created_date", nullable = false)
    private LocalDateTime createdDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "updated_by")
    private AdminUser updatedBy;

    @Column(name = "updated_date")
    private LocalDateTime updatedDate;
}
