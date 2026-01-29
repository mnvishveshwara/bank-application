package com.bankbroker.loanapp.repository.inbox;

import com.bankbroker.loanapp.entity.inbox.InboxMessage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InboxMessageRepository extends JpaRepository<InboxMessage, Long> {

    List<InboxMessage> findByThreadIdOrderBySentAtAsc(Long threadId);
}