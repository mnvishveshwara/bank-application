package com.bankbroker.loanapp.dto.stage;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApplicationCustomerDetailsResponse {

    private Long id;
    private String applicationId;

    private String spockName;
    private String leadSource;

    private String firstName;
    private String middleName;
    private String lastName;

    private String primaryContactNumber;
    private String secondaryContactNumber;
    private String email;

    private String propertyReferenceNo;
    private String propertyType;
    private String propertySubType;

    private String loanType;
    private String remarks;
    private LocalDateTime createdDate;
}
