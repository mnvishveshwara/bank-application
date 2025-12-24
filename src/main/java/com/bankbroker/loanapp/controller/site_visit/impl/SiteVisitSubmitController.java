package com.bankbroker.loanapp.controller.site_visit.impl;

import com.bankbroker.loanapp.dto.site_visit.SiteVisitSubmitResponse;
import com.bankbroker.loanapp.service.site_visit.api.SiteVisitSubmitService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/applications")
public class SiteVisitSubmitController {

    private final SiteVisitSubmitService siteVisitSubmitService;

    @PostMapping("/{applicationId}/site-visit/submit")
    public ResponseEntity<SiteVisitSubmitResponse> submitSiteVisit(
            @PathVariable String applicationId) {

        return ResponseEntity.ok(
                siteVisitSubmitService.submit(applicationId)
        );
    }
}
