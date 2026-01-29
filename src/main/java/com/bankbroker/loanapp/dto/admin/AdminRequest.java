package com.bankbroker.loanapp.dto.admin;

import com.bankbroker.loanapp.entity.core.BankMaster;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AdminRequest {
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private List<Long> bankIds;
    private String role;
}
