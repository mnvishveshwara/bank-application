package com.bankbroker.loanapp.dto.inbox;


import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InboxThreadResponse {

    private Long id;
    private String sender;
    private String subject;
    private boolean unread;
    private LocalDateTime lastUpdated;
}