package com.bankbroker.loanapp.dto.master;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AgencyMasterRequest {
    private Long id;
    private String agencyName;
    private String contactName;
    private String contactNumber;
    private String streetLine1;
    private String streetLine2;
    private String pinCode;
    private String city;
    private String state;
    private Double latitude;
    private Double longitude;
    private String mapURL;
}