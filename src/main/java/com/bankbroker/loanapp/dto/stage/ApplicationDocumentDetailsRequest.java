package com.bankbroker.loanapp.dto.stage;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApplicationDocumentDetailsRequest {
    private Boolean panUploaded;
    private Boolean aadhaarUploaded;
    private Boolean incomeProofUploaded;
    private Boolean propertyDocUploaded;
    private String remarks;
}
