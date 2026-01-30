package com.bankbroker.loanapp.controller.inbox;


import com.bankbroker.loanapp.dto.inbox.*;
import com.bankbroker.loanapp.service.inbox.api.InboxService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/agency/inbox")
@RequiredArgsConstructor
public class AgencyInboxController {

    private final InboxService inboxService;

    //   Inbox list
    @GetMapping
    public ResponseEntity<List<InboxThreadResponse>> getInbox() {
        return ResponseEntity.ok(inboxService.getAgencyInbox());
    }

    //   Thread messages
    @GetMapping("/{threadId}")
    public ResponseEntity<List<InboxMessageResponse>> getThread(
            @PathVariable Long threadId
    ) {
        return ResponseEntity.ok(inboxService.getThreadMessages(threadId));
    }

    //   Mark as read
    @PutMapping("/{threadId}/read")
    public ResponseEntity<Void> markRead(@PathVariable Long threadId) {
        inboxService.markAsRead(threadId);
        return ResponseEntity.ok().build();
    }

    //   Send reply
    @PostMapping(value = "/{threadId}/reply", consumes = "multipart/form-data")
    public ResponseEntity<Void> sendReply(
            @PathVariable Long threadId,
            @RequestPart("message") String message,
            @RequestPart(value = "file", required = false) MultipartFile file
    ) {
        inboxService.sendReply(threadId, message, file);
        return ResponseEntity.ok().build();
    }

}