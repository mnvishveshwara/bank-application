package com.bankbroker.loanapp.entity.site_visit;

import com.bankbroker.loanapp.entity.AdminUser;
import com.bankbroker.loanapp.entity.LoanApplication;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "site_visit_infrastructure_details")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SiteVisitInfrastructureDetails {

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
    // 🚧 Site Accessibility
    // -------------------------------------------------
    @Column(name = "site_accessibility")
    private Boolean siteAccessibility;     // Yes / No

    @Column(name = "accessible_through")
    private String accessibleThrough;      // Main Road / Layout Road / Private Road

    @Column(name = "accessibility_type")
    private String accessibilityType;      // Paved / Mud / Concrete

    @Column(name = "road_width")
    private String roadWidth;               // <10 ft / 10–20 ft / >20 ft

    // -------------------------------------------------
    // 🚰 Utilities
    // -------------------------------------------------
    @Column(name = "sewerage_system")
    private Boolean sewerageSystem;         // Yes / No

    @Column(name = "electricity")
    private Boolean electricity;            // Yes / No

    @Column(name = "water_supply")
    private String waterSupply;             // Borewell / Corporation / Tanker

    @Column(name = "number_of_lifts")
    private Integer numberOfLifts;

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
