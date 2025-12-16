package com.bankbroker.loanapp.entity.site_visit;

import com.bankbroker.loanapp.entity.core.LoanApplication;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "site_visit_images")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SiteVisitImageGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // -------------------------------------------------
    // 🔗 Application Mapping
    // -------------------------------------------------
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "application_id", nullable = false, unique = true)
    private LoanApplication application;

    // -------------------------------------------------
    // 🖼 Images
    // -------------------------------------------------
    @OneToMany(
            mappedBy = "imageGroup",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @Builder.Default
    private List<SiteVisitImage> images = new ArrayList<>();
}
