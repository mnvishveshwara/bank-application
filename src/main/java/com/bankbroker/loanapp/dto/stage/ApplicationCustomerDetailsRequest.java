package com.bankbroker.loanapp.dto.stage;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApplicationCustomerDetailsRequest {

    private String spockName;
    private String leadSource;
    private  String applicationId;

    private String firstName;
    private String middleName;
    private String lastName;

    private String primaryContactNumber;
    private String secondaryContactNumber;
    private String email;
    private String propertyReferenceNo;
    private String propertyType;
    private String propertySubType;
    private String bank;
    private String loanType;
    private String remarks;
}
