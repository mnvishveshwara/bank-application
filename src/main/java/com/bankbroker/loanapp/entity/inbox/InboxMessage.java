package com.bankbroker.loanapp.entity.inbox;

import com.bankbroker.loanapp.entity.enums.SenderType;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "inbox_message")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InboxMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "thread_id")
    private InboxThread thread;

    @Enumerated(EnumType.STRING)
    private SenderType senderType;

    @Column(columnDefinition = "TEXT")
    private String messageText;

    private String attachmentUrl;

    private LocalDateTime sentAt;
}
