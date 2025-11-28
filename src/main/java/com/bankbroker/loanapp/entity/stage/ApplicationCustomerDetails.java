package com.bankbroker.loanapp.entity.stage;

import com.bankbroker.loanapp.entity.LoanApplication;
import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "application_customer_details")
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
}