package com.bankbroker.loanapp.controller.core.impl;


import com.bankbroker.loanapp.dto.master.AgencyApplicationLogResponse;
import com.bankbroker.loanapp.service.core.api.AgencyLogsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/agency/logs")
@RequiredArgsConstructor
public class AgencyLogsController {

    private final AgencyLogsService logsService;

    @GetMapping
    public ResponseEntity<List<AgencyApplicationLogResponse>> getAgencyLogs() {
        return ResponseEntity.ok(logsService.getLogsForAgency());
    }
}