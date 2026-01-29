package com.bankbroker.loanapp.dto.admin;

import lombok.*;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AdminResponse {
    private String id;
    private String email;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String role;
    private LocalDateTime createdDate;
    private List<Long> bankIds;
    private List<String> bankNames;

}
