package com.bankbroker.loanapp.entity.site_visit;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "site_visit_technical_bua_levels")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SiteVisitTechnicalBuaLevel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // -------------------------------------------------
    //   Parent BUA
    // -------------------------------------------------
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bua_id", nullable = false)
    private SiteVisitTechnicalBua bua;

    // -------------------------------------------------
    //   LEVEL TYPE
    // -------------------------------------------------
    @Column(name = "level_type", nullable = false)
    private String levelType;
    /*
        BASEMENT
        GROUND_FLOOR
        FIRST_FLOOR
        SECOND_FLOOR
        NON_RCC
     */

    // Order for rendering (0,1,2...)
    @Column(name = "level_order")
    private Integer levelOrder;

    // -------------------------------------------------
    //   AREA DETAILS
    // -------------------------------------------------
    @Column(name = "area_actual")
    private Double areaActual;

    @Column(name = "area_document")
    private Double areaDocument;

    @Column(name = "area_approved")
    private Double areaApproved;
}
