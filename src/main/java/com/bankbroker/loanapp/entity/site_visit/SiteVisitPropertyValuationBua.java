package com.bankbroker.loanapp.entity.site_visit;


import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "site_visit_property_valuation_bua")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SiteVisitPropertyValuationBua {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "application_id", nullable = false)
    private String applicationId;

    @Column(name = "total_bua_amount")
    private Double totalBuaAmount;

    @OneToMany(mappedBy = "propertyValuationBua", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SiteVisitPropertyValuationBuaLevel> levels = new ArrayList<>();
}
