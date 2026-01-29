package com.bankbroker.loanapp.dto.inbox;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SendMessageRequest {

    private String message;
}