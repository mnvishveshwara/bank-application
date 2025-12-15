package com.bankbroker.loanapp.entity.site_visit;

import com.bankbroker.loanapp.entity.enums.SiteVisitImageCategory;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "site_visit_image_files")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SiteVisitImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // -------------------------------------------------
    // 🔗 Parent Mapping
    // -------------------------------------------------
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "image_group_id", nullable = false)
    private SiteVisitImageGroup imageGroup;

    // -------------------------------------------------
    // 🏷 Image Category (UI Sections)
    // -------------------------------------------------
    @Enumerated(EnumType.STRING)
    @Column(name = "image_category", nullable = false)
    private SiteVisitImageCategory category;

    // -------------------------------------------------
    // 📁 File Metadata
    // -------------------------------------------------
    @Column(name = "file_name", nullable = false)
    private String fileName;

    @Column(name = "file_path", nullable = false)
    private String filePath;

    @Column(name = "file_type")
    private String fileType;

    @Column(name = "file_size")
    private Long fileSize;
}
