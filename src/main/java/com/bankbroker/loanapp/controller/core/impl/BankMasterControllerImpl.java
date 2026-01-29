package com.bankbroker.loanapp.controller.core.impl;

import com.bankbroker.loanapp.controller.core.api.BankMasterController;
import com.bankbroker.loanapp.dto.admin.BankMasterRequest;
import com.bankbroker.loanapp.dto.admin.BankMasterResponse;
import com.bankbroker.loanapp.service.core.api.BankMasterService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/master/banks")
@RequiredArgsConstructor
public class BankMasterControllerImpl implements BankMasterController {

    private final BankMasterService service;

    @PostMapping
    public ResponseEntity<BankMasterResponse> create(
            @RequestBody BankMasterRequest request) {
        return ResponseEntity.ok(service.create(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BankMasterResponse> update(
            @PathVariable Long id,
            @RequestBody BankMasterRequest request) {
        return ResponseEntity.ok(service.update(id, request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<BankMasterResponse> get(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @GetMapping
    public ResponseEntity<List<BankMasterResponse>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/active")
    public ResponseEntity<List<BankMasterResponse>> getActive() {
        return ResponseEntity.ok(service.getActiveBanks());
    }


    @GetMapping("/my-banks")
    public ResponseEntity<List<BankMasterResponse>> getMyBanks() {
        return ResponseEntity.ok(service.getBanksForLoggedInAdmin());
    }
}
