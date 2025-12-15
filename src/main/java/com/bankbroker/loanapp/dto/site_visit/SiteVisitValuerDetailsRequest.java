package com.bankbroker.loanapp.dto.site_visit;


import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SiteVisitValuerDetailsRequest {

    private MultipartFile organisationSeal;
    private MultipartFile valuerSignature;
}