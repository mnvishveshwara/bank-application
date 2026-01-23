package com.bankbroker.loanapp.entity.core;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "bank_master",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "bank_code")
        })
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BankMaster {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "bank_code", nullable = false, length = 20)
    private String bankCode;

    @Column(name = "bank_name", nullable = false, length = 150)
    private String bankName;

    @Column(name = "ifsc_prefix", length = 20)
    private String ifscPrefix;

    @Column(name = "swift_code", length = 20)
    private String swiftCode;

    @Column(name = "bank_type", length = 30)
    private String bankType; // PUBLIC, PRIVATE, COOPERATIVE

    @Column(length = 50)
    private String country;

    @Column(name = "head_office_address", length = 500)
    private String headOfficeAddress;

    private String contactEmail;
    private String contactPhone;
    private String websiteUrl;

    private Boolean isActive;

    @ManyToOne
    @JoinColumn(name = "created_by")
    private AdminUser createdBy;

    private LocalDateTime createdDate;

    @ManyToOne
    @JoinColumn(name = "updated_by")
    private AdminUser updatedBy;

    private LocalDateTime updatedDate;
}
