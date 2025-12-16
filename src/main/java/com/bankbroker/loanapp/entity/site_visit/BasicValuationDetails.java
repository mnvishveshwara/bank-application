package com.bankbroker.loanapp.entity.site_visit;


import com.bankbroker.loanapp.entity.core.AdminUser;
import com.bankbroker.loanapp.entity.core.LoanApplication;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "basic_valuation_details")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BasicValuationDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "application_id", nullable = false)
    private LoanApplication application;

    @Column(name = "property_owner_name", nullable = false, length = 150)
    private String propertyOwnerName;

    @Column(name = "relationship_with_applicant", length = 100)
    private String relationshipWithApplicant;

    @Column(name = "report_date", nullable = false)
    private LocalDate reportDate;

    @Column(name = "loan_type", length = 100)
    private String loanType;

    @Column(name = "person_met_at_site", length = 150)
    private String personMetAtSite;

    @ElementCollection
    @CollectionTable(
            name = "basic_valuation_documents",
            joinColumns = @JoinColumn(name = "basic_valuation_id")
    )
    @Column(name = "document_name")
    private List<String> documentsProvided;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "created_by", nullable = false)
    private AdminUser createdBy;

    @Column(name = "created_date", nullable = false)
    private LocalDateTime createdDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "updated_by", nullable = false)
    private AdminUser updatedBy;

    @Column(name = "updated_date")
    private LocalDateTime updatedDate;




}