package com.bankbroker.loanapp.entity.site_visit;

import com.bankbroker.loanapp.entity.core.AdminUser;
import com.bankbroker.loanapp.entity.core.LoanApplication;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(
        name = "site_visit_property_value_assessment_final_valuation",
        uniqueConstraints = @UniqueConstraint(columnNames = "application_id")
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SiteVisitPropertyValueAssessmentFinalValuation {

    // -------------------------------------------------
    // 🔑 Primary Key
    // -------------------------------------------------
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // -------------------------------------------------
    // 🔗 Application
    // -------------------------------------------------
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "application_id", nullable = false)
    private LoanApplication application;

    // -------------------------------------------------
    // 💰 FAIR MARKET VALUE (FMV) – AS ON DATE
    // -------------------------------------------------
    @Column(name = "fmv_on_date_actual")
    private Double fmvOnDateActual;

    @Column(name = "fmv_on_date_document")
    private Double fmvOnDateDocument;

    @Column(name = "fmv_on_date_layout")
    private Double fmvOnDateLayout;

    // -------------------------------------------------
    // 💰 FAIR MARKET VALUE (FMV) – ON COMPLETION
    // -------------------------------------------------
    @Column(name = "fmv_on_completion_actual")
    private Double fmvOnCompletionActual;

    @Column(name = "fmv_on_completion_document")
    private Double fmvOnCompletionDocument;

    @Column(name = "fmv_on_completion_layout")
    private Double fmvOnCompletionLayout;

    // -------------------------------------------------
    // 💸 DISTRESSED SALE VALUE
    // -------------------------------------------------
    @Column(name = "distressed_value_actual")
    private Double distressedValueActual;

    @Column(name = "distressed_value_document")
    private Double distressedValueDocument;

    @Column(name = "distressed_value_layout")
    private Double distressedValueLayout;

    // -------------------------------------------------
    // 📘 GUIDELINE / CIRCLE VALUE
    // -------------------------------------------------
    @Column(name = "guideline_value_actual")
    private Double guidelineValueActual;

    @Column(name = "guideline_value_document")
    private Double guidelineValueDocument;

    @Column(name = "guideline_value_layout")
    private Double guidelineValueLayout;

    // -------------------------------------------------
    // 🛡 INSURANCE VALUE
    // -------------------------------------------------
    @Column(name = "insurance_value_actual")
    private Double insuranceValueActual;

    @Column(name = "insurance_value_document")
    private Double insuranceValueDocument;

    @Column(name = "insurance_value_layout")
    private Double insuranceValueLayout;

    // -------------------------------------------------
    //   FINAL VALUE TO BE CONSIDERED
    // -------------------------------------------------
    @Column(name = "final_value_considered")
    private Double finalValueConsidered;

    // -------------------------------------------------
    // 🧾 AUDIT
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
