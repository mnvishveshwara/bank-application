package com.bankbroker.loanapp.controller.core.impl;

import com.bankbroker.loanapp.controller.core.api.BankMasterController;
import com.bankbroker.loanapp.dto.admin.AdminResponse;
import com.bankbroker.loanapp.dto.admin.BankMasterRequest;
import com.bankbroker.loanapp.dto.admin.BankMasterResponse;
import com.bankbroker.loanapp.dto.admin.CreateBankValuatorRequest;
import com.bankbroker.loanapp.service.core.api.AdminUserService;
import com.bankbroker.loanapp.service.core.api.BankMasterService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/master/banks")
@RequiredArgsConstructor
public class BankMasterControllerImpl implements BankMasterController {

    private final BankMasterService service;
    private final AdminUserService userService;

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

    @PostMapping("/create-bank-valuator")
    public ResponseEntity<?> createBankValuator(@RequestBody CreateBankValuatorRequest request) {
        userService.createBankValuator(request);
        return ResponseEntity.status(HttpStatus.CREATED).body("Bank Valuator created successfully");
    }


    @GetMapping("/internal-valuators")
    public ResponseEntity<List<AdminResponse>> getInternalValuators() {
        return ResponseEntity.ok(userService.getInternalValuators());
    }
}
