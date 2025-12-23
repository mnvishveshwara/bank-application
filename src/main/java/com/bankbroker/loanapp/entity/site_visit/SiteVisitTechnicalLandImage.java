package com.bankbroker.loanapp.entity.site_visit;

import com.bankbroker.loanapp.entity.core.AdminUser;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "site_visit_technical_land_images")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SiteVisitTechnicalLandImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 🔗 Link ONLY to Land
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "technical_land_id", nullable = false)
    private SiteVisitTechnicalLand technicalLand;

    private String fileName;
    private String filePath;
    private String contentType;
    private Long fileSize;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "uploaded_by", nullable = false)
    private AdminUser uploadedBy;

    private LocalDateTime uploadedDate;
}
