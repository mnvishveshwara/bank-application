package com.bankbroker.loanapp.dto.inbox;


import com.bankbroker.loanapp.entity.enums.SenderType;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InboxMessageResponse {

    private Long id;
    private SenderType from;
    private String text;
    private String fileUrl;
    private LocalDateTime sentAt;
}