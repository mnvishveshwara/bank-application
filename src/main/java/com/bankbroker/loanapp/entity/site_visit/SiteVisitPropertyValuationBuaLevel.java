package com.bankbroker.loanapp.entity.site_visit;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;

@Entity
@Table(name = "site_visit_property_valuation_bua_level")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SiteVisitPropertyValuationBuaLevel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "property_valuation_bua_id", nullable = false)
    private SiteVisitPropertyValuationBua propertyValuationBua;

    @Column(name = "level_id")
    private Long levelId;           // references TechnicalBuaLevel.id

    @Column(name = "level_type")
    private String levelType;       // BASEMENT, GROUND_FLOOR, NON_RCC, etc.

    @Column(name = "level_order")
    private Integer levelOrder;

    @Column(name = "area_considered")
    private Double areaConsidered;  // least of actual/document/approved

    @Column(name = "rate_per_sqft")
    private Double ratePerSqFt;

    @Column(name = "total_amount")
    private Double totalAmount;
}
