package com.bankbroker.loanapp.entity.core;

import com.bankbroker.loanapp.entity.enums.Role;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "admin_master")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AdminUser {

    @Id
    @Column(length = 12)
    private String id;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "phone_number", length = 20)
    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @Column(name = "created_date", nullable = false)
    private LocalDateTime createdDate;

//    @Column(name = "bank", nullable = false)
//    private String bank;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "admin_bank_mapping",
            joinColumns = @JoinColumn(name = "admin_id"),
            inverseJoinColumns = @JoinColumn(name = "bank_id")
    )
    private Set<BankMaster> banks = new HashSet<>();


    @Column(name = "agency_id")
    private Long agencyId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "agency_id", insertable = false, updatable = false)
    private AgencyMaster agency;



}
