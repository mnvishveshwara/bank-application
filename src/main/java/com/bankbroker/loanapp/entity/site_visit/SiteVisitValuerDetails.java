package com.bankbroker.loanapp.entity.site_visit;

import com.bankbroker.loanapp.entity.core.AdminUser;
import com.bankbroker.loanapp.entity.core.LoanApplication;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "site_visit_valuer_details")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SiteVisitValuerDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // -------------------------------------------------
    //   Application Mapping
    // -------------------------------------------------
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "application_id", nullable = false, unique = true)
    private LoanApplication application;

    // =================================================
    //   Valuation Organisation Seal
    // =================================================

    @Column(name = "organisation_seal_file_name")
    private String organisationSealFileName;

    @Column(name = "organisation_seal_file_path")
    private String organisationSealFilePath;

    @Column(name = "organisation_seal_file_type")
    private String organisationSealFileType;

    @Column(name = "organisation_seal_file_size")
    private Long organisationSealFileSize;

    // =================================================
    //   Valuer Signature
    // =================================================

    @Column(name = "valuer_signature_file_name")
    private String valuerSignatureFileName;

    @Column(name = "valuer_signature_file_path")
    private String valuerSignatureFilePath;

    @Column(name = "valuer_signature_file_type")
    private String valuerSignatureFileType;

    @Column(name = "valuer_signature_file_size")
    private Long valuerSignatureFileSize;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "created_by_id")
    private AdminUser createdBy;

    @Column(name = "created_date")
    private LocalDateTime createdDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "updated_by_id")
    private AdminUser updatedBy;

    @Column(name = "updated_date")
    private LocalDateTime updatedDate;
}
