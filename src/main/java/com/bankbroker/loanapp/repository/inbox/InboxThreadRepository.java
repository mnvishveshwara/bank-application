package com.bankbroker.loanapp.repository.inbox;


import com.bankbroker.loanapp.entity.inbox.InboxThread;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface InboxThreadRepository extends JpaRepository<InboxThread, Long> {


    List<InboxThread> findByAgencyIdOrderByLastUpdatedDesc(Long agencyId);

    Optional<InboxThread> findByApplicationId(String applicationId);

    List<InboxThread> findByBankIdInOrderByLastUpdatedDesc(List<Long> bankIds);
}