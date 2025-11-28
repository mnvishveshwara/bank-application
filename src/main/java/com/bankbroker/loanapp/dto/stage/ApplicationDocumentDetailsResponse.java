package com.bankbroker.loanapp.dto.stage;

import lombok.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApplicationDocumentDetailsResponse {
    private Long id;
    private String applicationId;
    private List<ApplicationUploadedDocumentResponse> documents;
}
