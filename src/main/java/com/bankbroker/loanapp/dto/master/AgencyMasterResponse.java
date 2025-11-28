package com.bankbroker.loanapp.dto.master;


import lombok.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AgencyMasterResponse {

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

    private String createdBy;
    private String updatedBy;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}