package com.bankbroker.loanapp.entity.site_visit;

import com.bankbroker.loanapp.entity.LoanApplication;
import jakarta.persistence.*;
import lombok.*;

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
    // 🔗 Application Mapping
    // -------------------------------------------------
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "application_id", nullable = false, unique = true)
    private LoanApplication application;

    // =================================================
    // 🟩 Valuation Organisation Seal
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
    // 🟩 Valuer Signature
    // =================================================

    @Column(name = "valuer_signature_file_name")
    private String valuerSignatureFileName;

    @Column(name = "valuer_signature_file_path")
    private String valuerSignatureFilePath;

    @Column(name = "valuer_signature_file_type")
    private String valuerSignatureFileType;

    @Column(name = "valuer_signature_file_size")
    private Long valuerSignatureFileSize;
}
