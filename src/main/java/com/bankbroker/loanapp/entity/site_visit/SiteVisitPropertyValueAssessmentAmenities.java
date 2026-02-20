package com.bankbroker.loanapp.entity.site_visit;
import com.bankbroker.loanapp.entity.core.AdminUser;
import com.bankbroker.loanapp.entity.core.LoanApplication;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(
        name = "site_visit_property_value_assessment_amenities",
        uniqueConstraints = @UniqueConstraint(columnNames = "application_id")
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SiteVisitPropertyValueAssessmentAmenities {

    // -------------------------------------------------
    //   Primary Key
    // -------------------------------------------------
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // -------------------------------------------------
    //   Application
    // -------------------------------------------------
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "application_id", nullable = false)
    private LoanApplication application;

    // -------------------------------------------------
    //   SUMMATION (AUTO CALCULATED)
    // -------------------------------------------------
    @Column(name = "total_amenities_value")
    private Double totalAmenitiesValue;

    // -------------------------------------------------
    //   DYNAMIC AMENITIES
    // -------------------------------------------------
    @OneToMany(
            mappedBy = "amenities",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @Builder.Default
    private List<SiteVisitPropertyValueAssessmentAmenityItem> items= new ArrayList<>();

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
