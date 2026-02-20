package com.bankbroker.loanapp.entity.site_visit;

import com.bankbroker.loanapp.entity.core.AdminUser;
import com.bankbroker.loanapp.entity.core.LoanApplication;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(
        name = "site_visit_technical_plot",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "application_id")
        }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SiteVisitTechnicalPlot {

    // -------------------------------------------------
    //   Primary Key
    // -------------------------------------------------
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // -------------------------------------------------
    //   Loan Application
    // -------------------------------------------------
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "application_id", nullable = false)
    private LoanApplication application;

    // -------------------------------------------------
    //   PROPERTY TYPE
    // -------------------------------------------------
    @Column(name = "property_type", nullable = false)
    private String propertyType;
    // Independent / Villa / Apartment / Multi-Storied / Commercial

    // -------------------------------------------------
    //   EAST SIDE
    // -------------------------------------------------
    @Column(name = "east_site_visit")
    private String eastAsPerSiteVisit;

    @Column(name = "east_legal_document")
    private String eastAsPerLegalDocument;

    @Column(name = "east_match")
    private Boolean eastMatch;

    // -------------------------------------------------
    //   WEST SIDE
    // -------------------------------------------------
    @Column(name = "west_site_visit")
    private String westAsPerSiteVisit;

    @Column(name = "west_legal_document")
    private String westAsPerLegalDocument;

    @Column(name = "west_match")
    private Boolean westMatch;

    // -------------------------------------------------
    //   NORTH SIDE
    // -------------------------------------------------
    @Column(name = "north_site_visit")
    private String northAsPerSiteVisit;

    @Column(name = "north_legal_document")
    private String northAsPerLegalDocument;

    @Column(name = "north_match")
    private Boolean northMatch;

    // -------------------------------------------------
    //   SOUTH SIDE
    // -------------------------------------------------
    @Column(name = "south_site_visit")
    private String southAsPerSiteVisit;

    @Column(name = "south_legal_document")
    private String southAsPerLegalDocument;

    @Column(name = "south_match")
    private Boolean southMatch;

    // -------------------------------------------------
    //   AUDIT
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
