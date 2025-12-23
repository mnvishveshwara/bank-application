package com.bankbroker.loanapp.dto.site_visit;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SiteVisitTechnicalLandImageResponse {

    private Long id;
    private String fileName;
    private String filePath;
    private String contentType;
    private Long fileSize;
}
