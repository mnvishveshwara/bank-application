package com.bankbroker.loanapp.dto.valuator;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ValuatorRequest {
    private String name;
    private String phone;
    private String email;
}
