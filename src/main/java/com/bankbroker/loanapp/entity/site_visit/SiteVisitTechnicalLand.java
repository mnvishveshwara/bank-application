package com.bankbroker.loanapp.entity.site_visit;

import com.bankbroker.loanapp.entity.core.AdminUser;
import com.bankbroker.loanapp.entity.core.LoanApplication;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
@Entity
@Table(
        name = "site_visit_technical_land",
        uniqueConstraints = @UniqueConstraint(columnNames = "application_id")
)
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class SiteVisitTechnicalLand {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "application_id", nullable = false)
    private LoanApplication application;

    private Double landAreaAsPerActual;
    private Double landAreaAsPerDocument;
    private Double landAreaAsPerLayoutPlan;

    private Boolean landAreaMatch;

    private String holdingType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "created_by", nullable = false)
    private AdminUser createdBy;

    private LocalDateTime createdDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "updated_by")
    private AdminUser updatedBy;

    private LocalDateTime updatedDate;
}
