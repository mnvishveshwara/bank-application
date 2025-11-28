package com.bankbroker.loanapp.dto.customer;

import lombok.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerResponse {
    private String id;
    private String email;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String bank;
    private LocalDateTime createdDate;
}
