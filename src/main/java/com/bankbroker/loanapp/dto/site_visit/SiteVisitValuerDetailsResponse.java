package com.bankbroker.loanapp.dto.site_visit;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SiteVisitValuerDetailsResponse {

    private Long id;
    private String applicationId;

    private String organisationSealFilePath;
    private String valuerSignatureFilePath;
}