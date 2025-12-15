package com.bankbroker.loanapp.dto.site_visit;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SiteVisitImageResponse {

    private Long id;
    private String category;
    private String fileName;
    private String filePath;
}