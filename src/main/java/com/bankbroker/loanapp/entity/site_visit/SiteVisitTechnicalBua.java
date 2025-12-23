package com.bankbroker.loanapp.entity.site_visit;

import com.bankbroker.loanapp.entity.core.AdminUser;
import com.bankbroker.loanapp.entity.core.LoanApplication;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
//
//@Entity
//@Table(
//        name = "site_visit_technical_bua",
//        uniqueConstraints = @UniqueConstraint(columnNames = "application_id")
//)
//@Getter
//@Setter
//@NoArgsConstructor
//@AllArgsConstructor
//@Builder
//public class SiteVisitTechnicalBua {
//
//    // -------------------------------------------------
//    // 🔑 Primary Key
//    // -------------------------------------------------
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    // -------------------------------------------------
//    // 🔗 Application
//    // -------------------------------------------------
//    @OneToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "application_id", nullable = false)
//    private LoanApplication application;
//
//    // -------------------------------------------------
//    // 🏗 CONFIGURATION (from UI counters)
//    // -------------------------------------------------
//    @Column(name = "no_of_basements")
//    private Integer basements;
//
//    @Column(name = "no_of_floors")
//    private Integer floors;
//
//    @Column(name = "no_of_non_rcc")
//    private Integer nonRcc;
//
//    // -------------------------------------------------
//    // ➕ SUMMATION (AUTO CALCULATED)
//    // -------------------------------------------------
//    @Column(name = "total_bua_actual")
//    private Double totalBuaActual;
//
//    @Column(name = "total_bua_document")
//    private Double totalBuaDocument;
//
//    @Column(name = "total_bua_approved")
//    private Double totalBuaApproved;
//
//    // -------------------------------------------------
//    // 🔗 DYNAMIC LEVELS
//    // -------------------------------------------------
//    @OneToMany(
//            mappedBy = "bua",
//            cascade = CascadeType.ALL,
//            orphanRemoval = true
//    )
//
//    private List<SiteVisitTechnicalBuaLevel> levels;
//
//    // -------------------------------------------------
//    // 🧾 AUDIT
//    // -------------------------------------------------
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "created_by", nullable = false)
//    private AdminUser createdBy;
//
//    @Column(name = "created_date", nullable = false)
//    private LocalDateTime createdDate;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "updated_by")
//    private AdminUser updatedBy;
//
//    @Column(name = "updated_date")
//    private LocalDateTime updatedDate;
//}

@Entity
@Table(
        name = "site_visit_technical_bua",
        uniqueConstraints = @UniqueConstraint(columnNames = "application_id")
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SiteVisitTechnicalBua {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "application_id", nullable = false)
    private LoanApplication application;

    private Integer basements;
    private Integer floors;
    private Integer nonRcc;

    private Double totalBuaActual;
    private Double totalBuaDocument;
    private Double totalBuaApproved;

    @OneToMany(
            mappedBy = "bua",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @Builder.Default
    private List<SiteVisitTechnicalBuaLevel> levels = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "created_by", nullable = false)
    private AdminUser createdBy;

    private LocalDateTime createdDate;

    @ManyToOne(fetch = FetchType.LAZY)
    private AdminUser updatedBy;

    private LocalDateTime updatedDate;
}
