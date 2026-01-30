package com.bankbroker.loanapp.service.inbox.impl;


import com.bankbroker.loanapp.dto.inbox.*;
import com.bankbroker.loanapp.entity.core.AdminUser;
import com.bankbroker.loanapp.entity.core.BankMaster;
import com.bankbroker.loanapp.entity.enums.SenderType;
import com.bankbroker.loanapp.entity.inbox.*;
import com.bankbroker.loanapp.repository.inbox.*;
import com.bankbroker.loanapp.service.inbox.api.InboxService;
import com.bankbroker.loanapp.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class InboxServiceImpl implements InboxService {

    private final InboxThreadRepository threadRepo;
    private final InboxMessageRepository messageRepo;
    private final SecurityUtil securityUtil;

    @Override
    public List<InboxThreadResponse> getAgencyInbox() {

        Long agencyId = securityUtil.getLoggedInAdmin().getAgencyId();

        return threadRepo.findByAgencyIdOrderByLastUpdatedDesc(agencyId)
                .stream()
                .map(t -> InboxThreadResponse.builder()
                        .id(t.getId())
                        .sender("Bank ID: " + t.getBankId())
                        .subject(t.getSubject())



                        .unread(t.isUnreadForAgency())
                        .lastUpdated(t.getLastUpdated())
                        .build())
                .toList();
    }

    @Override
    public List<InboxMessageResponse> getThreadMessages(Long threadId) {

        return messageRepo.findByThreadIdOrderBySentAtAsc(threadId)
                .stream()
                .map(m -> InboxMessageResponse.builder()
                        .id(m.getId())
                        .from(m.getSenderType())
                        .text(m.getMessageText())
                        .fileUrl(m.getAttachmentUrl())
                        .sentAt(m.getSentAt())
                        .build())
                .toList();
    }

    @Override
    public void markAsRead(Long threadId) {

        InboxThread thread = threadRepo.findById(threadId)
                .orElseThrow();

        thread.setUnreadForAgency(false);
        threadRepo.save(thread);
    }

    @Override
    public void sendReply(Long threadId, String message, MultipartFile file) {

        InboxThread thread = threadRepo.findById(threadId)
                .orElseThrow();

        String fileUrl = null;

        //   Save file if present
        if (file != null && !file.isEmpty()) {
            fileUrl = file.getOriginalFilename(); // later store properly
        }

        InboxMessage msg = InboxMessage.builder()
                .thread(thread)
                .senderType(SenderType.AGENCY)
                .messageText(message)
                .attachmentUrl(fileUrl)
                .sentAt(LocalDateTime.now())
                .build();

        messageRepo.save(msg);

        thread.setUnreadForBank(true);
        thread.setLastUpdated(LocalDateTime.now());

        threadRepo.save(thread);
    }



    @Override
    public void createThreadForAssignment(
            String applicationId,
            Long bankId,
            Long agencyId,
            String subject
    ) {

        //   Avoid duplicate thread creation
        if (threadRepo.findByApplicationId(applicationId).isPresent()) {
            return;
        }

        //   Create new thread
        InboxThread thread = InboxThread.builder()
                .applicationId(applicationId)
                .bankId(bankId)
                .agencyId(agencyId)
                .subject(subject)
                .unreadForAgency(true)
                .unreadForBank(false)
                .lastUpdated(LocalDateTime.now())
                .build();

        thread = threadRepo.save(thread);

        //   Add first system message
        InboxMessage firstMessage = InboxMessage.builder()
                .thread(thread)
                .senderType(SenderType.SYSTEM)
                .messageText(
                        "A new application has been assigned to your agency. Please proceed with valuation."
                )
                .sentAt(LocalDateTime.now())
                .build();


        messageRepo.save(firstMessage);
    }

    @Override
    public List<InboxThreadResponse> getManagerInbox() {

        AdminUser manager = securityUtil.getLoggedInAdmin();

        // Manager can have multiple banks
        List<Long> bankIds = manager.getBanks()
                .stream()
                .map(BankMaster::getId)
                .toList();

        return threadRepo.findByBankIdInOrderByLastUpdatedDesc(bankIds)
                .stream()
                .map(t -> InboxThreadResponse.builder()
                        .id(t.getId())
                        .sender("Agency ID: " + t.getAgencyId())
                        .subject(t.getSubject())
                        .unread(t.isUnreadForBank()) //   Manager checks bank unread flag
                        .lastUpdated(t.getLastUpdated())
                        .build())
                .toList();
    }

    @Override
    public void markAsReadForManager(Long threadId) {

        InboxThread thread = threadRepo.findById(threadId)
                .orElseThrow();

        thread.setUnreadForBank(false); //   Manager reads → bank unread cleared
        threadRepo.save(thread);
    }
    @Override
    public void sendManagerReply(Long threadId, String message, MultipartFile file) {

        InboxThread thread = threadRepo.findById(threadId)
                .orElseThrow();

        String fileUrl = null;

        if (file != null && !file.isEmpty()) {
            fileUrl = file.getOriginalFilename(); // later store properly
        }

        InboxMessage msg = InboxMessage.builder()
                .thread(thread)
                .senderType(SenderType.BANK) //   Manager is Bank side
                .messageText(message)
                .attachmentUrl(fileUrl)
                .sentAt(LocalDateTime.now())
                .build();

        messageRepo.save(msg);

        //   Notify Agency unread
        thread.setUnreadForAgency(true);
        thread.setLastUpdated(LocalDateTime.now());

        threadRepo.save(thread);
    }

}
