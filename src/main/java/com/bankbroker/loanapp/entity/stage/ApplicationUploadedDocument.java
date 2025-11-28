package com.bankbroker.loanapp.entity.stage;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "application_uploaded_documents")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApplicationUploadedDocument {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fileName;
    private String fileType;
    private Long fileSizeKB;
    private String documentType;
    private String fileUrl;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "document_details_id")
    private ApplicationDocumentDetails documentDetails;
}
