package com.bankbroker.loanapp.entity.core;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "application_master")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoanApplication {

    @Id
    @Column(length = 12)
    private String id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "created_by_id", nullable = false)
    private AdminUser createdBy;

    @Column(name = "created_date", nullable = false)
    private LocalDateTime createdDate;

    @Column(name = "is_active", nullable = false)
    private Boolean active;

    @Column(name = "updated_date")
    private LocalDateTime updatedDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id")
    private Customer client;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "assigned_to_id")
    private AdminUser assignedTo;

    @Column(name = "associated_bank")
    private String associatedBank;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "valuator_id")
    private AdminUser valuator;

    @Column(name = "planned_site_visit_date")
    private LocalDate plannedSiteVisitDate;

    @Column(name = "resched_site_visit_date")
    private LocalDate reschedSiteVisitDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reassigned_valuator_id")
    private AdminUser re_assigned_valuator;

    @Column(name = "agency_remarks", length = 1000)
    private String agencyRemarks;

}
