package com.bankbroker.loanapp.entity.stage;

import com.bankbroker.loanapp.entity.core.AdminUser;
import com.bankbroker.loanapp.entity.core.LoanApplication;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;


@Entity
@Table(name = "application_customer_details")
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApplicationCustomerDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "application_id", nullable = false, unique = true)
    private LoanApplication application;

    @Column(name = "spock_name")
    private String spockName;

    @Column(name = "lead_source")
    private String leadSource;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "middle_name")
    private String middleName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "primary_contact_number")
    private String primaryContactNumber;

    @Column(name = "secondary_contact_number")
    private String secondaryContactNumber;

    @Column(name = "email")
    private String email;

    @Column(name = "property_reference_no")
    private String propertyReferenceNo;

    @Column(name = "property_type")
    private String propertyType;

    @Column(name = "property_sub_type")
    private String propertySubType;

    @Column(name = "loan_type")
    private String loanType;

    private String remarks;

    private String bank;

    @CreatedDate
    @Column(name = "created_date",  updatable = false)
    private LocalDateTime createdDate;

    @LastModifiedDate
    @Column(name = "updated_date")
    private LocalDateTime updatedDate;

    @CreatedBy
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "created_by_id", nullable = false, updatable = false)
    private AdminUser createdBy;

    @LastModifiedBy
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "updated_by_id", nullable = false)
    private AdminUser updatedBy;
}