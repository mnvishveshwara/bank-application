package com.bankbroker.loanapp.service.inbox.api;


import com.bankbroker.loanapp.dto.inbox.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface InboxService {

    List<InboxThreadResponse> getAgencyInbox();

    List<InboxMessageResponse> getThreadMessages(Long threadId);

    void markAsRead(Long threadId);

    void sendReply(Long threadId, String message, MultipartFile file);

    void createThreadForAssignment(
            String applicationId,
            Long bankId,
            Long agencyId,
            String subject
    );


    List<InboxThreadResponse> getManagerInbox();

    void markAsReadForManager(Long threadId);

    void sendManagerReply(Long threadId, String message, MultipartFile file);


}