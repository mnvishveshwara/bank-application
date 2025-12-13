package com.bankbroker.loanapp.entity.site_visit;

import com.bankbroker.loanapp.entity.AdminUser;
import com.bankbroker.loanapp.entity.LoanApplication;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "site_visit_property_boundary_details")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SiteVisitPropertyBoundaryDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // -------------------------------------------------
    // 🔗 Application
    // -------------------------------------------------
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "application_id", nullable = false, unique = true)
    private LoanApplication application;

    // -------------------------------------------------
    // 🧭 Property Facing
    // -------------------------------------------------
    @Column(name = "property_facing")
    private String propertyFacing; // East / West / North / South

    // -------------------------------------------------
    // 🧱 East Boundary
    // -------------------------------------------------
    @Column(name = "east_site_visit")
    private String eastAsPerSiteVisit;

    @Column(name = "east_legal_doc")
    private String eastAsPerLegalDocument;

    @Column(name = "east_match")
    private Boolean eastMatch;

    // -------------------------------------------------
    // 🧱 South Boundary
    // -------------------------------------------------
    @Column(name = "south_site_visit")
    private String southAsPerSiteVisit;

    @Column(name = "south_legal_doc")
    private String southAsPerLegalDocument;

    @Column(name = "south_match")
    private Boolean southMatch;

    // -------------------------------------------------
    // 🧱 West Boundary
    // -------------------------------------------------
    @Column(name = "west_site_visit")
    private String westAsPerSiteVisit;

    @Column(name = "west_legal_doc")
    private String westAsPerLegalDocument;

    @Column(name = "west_match")
    private Boolean westMatch;

    // -------------------------------------------------
    // 🧱 North Boundary
    // -------------------------------------------------
    @Column(name = "north_site_visit")
    private String northAsPerSiteVisit;

    @Column(name = "north_legal_doc")
    private String northAsPerLegalDocument;

    @Column(name = "north_match")
    private Boolean northMatch;

    // -------------------------------------------------
    // ✅ Validations
    // -------------------------------------------------
    @Column(name = "boundary_matching")
    private Boolean boundaryMatching;

    @Column(name = "earthquake_resistant")
    private Boolean earthquakeResistant;

    @Column(name = "property_identification")
    private Boolean propertyIdentification;

    // -------------------------------------------------
    // 🏛 Zoning
    // -------------------------------------------------
    @Column(name = "current_zoning")
    private String currentZoning;

    // -------------------------------------------------
    // 🧾 Audit
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
