package com.bankbroker.loanapp.dto.admin;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BankMasterResponse {

    private Long id;
    private String bankCode;
    private String bankName;
    private String ifscPrefix;
    private String swiftCode;
    private String bankType;
    private String country;
    private String headOfficeAddress;
    private String contactEmail;
    private String contactPhone;
    private String websiteUrl;
    private Boolean isActive;
}