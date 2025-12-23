package com.bankbroker.loanapp.entity.site_visit;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "site_visit_property_value_assessment_amenity_items")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SiteVisitPropertyValueAssessmentAmenityItem {

    // -------------------------------------------------
    // 🔑 Primary Key
    // -------------------------------------------------
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // -------------------------------------------------
    // 🔗 Parent Amenities
    // -------------------------------------------------
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "amenities_id", nullable = false)
    private SiteVisitPropertyValueAssessmentAmenities amenities;

    // -------------------------------------------------
    // 🏷 AMENITY DETAILS
    // -------------------------------------------------
    @Column(name = "amenity_category", nullable = false)
    private String amenityCategory;
    // e.g. Swimming Pool, Gym, Lift

    @Column(name = "amenity_rating", nullable = false)
    private String amenityRating;
    // e.g. 1 Star, 2 Star, 3 Star

    @Column(name = "amenity_impact", nullable = false)
    private String amenityImpact;
    // Low / Medium / High

    // -------------------------------------------------
    // 💰 AMENITY VALUE (Derived)
    // -------------------------------------------------
    @Column(name = "amenity_value")
    private Double amenityValue;
}
