package com.bankbroker.loanapp.dto.stage;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApplicationUploadedDocumentResponse {
    private Long id;
    private String fileName;
    private String fileType;
    private Long fileSizeKB;
    private String documentType;
    private String fileUrl;
}
