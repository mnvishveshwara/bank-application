package com.bankbroker.loanapp.dto.valuator;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AssignValuatorRequest {

    @NotBlank(message = "Valuator ID cannot be empty")
    private String valuatorId;
    private String remarks;
}
