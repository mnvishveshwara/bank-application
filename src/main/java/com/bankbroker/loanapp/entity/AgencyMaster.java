package com.bankbroker.loanapp.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(
        name = "agency_master",
        uniqueConstraints = @UniqueConstraint(columnNames = "agency_name")
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AgencyMaster {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "agency_name", nullable = false, unique = true)
    private String agencyName;

    @Column(name = "contact_name")
    private String contactName;

    @Column(name = "contact_number")
    private String contactNumber;

    @Column(name = "street_line_1")

    private String streetLine1;

    @Column(name = "street_line_2")
    private String streetLine2;

    @Column(name = "pincode")
    private String pinCode;

    @Column(name = "city")
    private String city;

    @Column(name = "state")
    private String state;

    @Column(name = "latitude")
    private Double latitude;

    @Column(name = "longitude")
    private Double longitude;

    @Column(name = "map_url")
    private String mapURL;

    @Column(name = "bank", nullable = false)
    private String bank;

     // ---------------- AUDIT FIELDS ----------------

    @ManyToOne
    @JoinColumn(name = "created_by")
    private AdminUser createdBy;

    @ManyToOne
    @JoinColumn(name = "updated_by")
    private AdminUser updatedBy;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @PrePersist
    public void prePersist() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        updatedAt = LocalDateTime.now();
    }
}
