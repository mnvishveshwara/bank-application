package com.bankbroker.loanapp.controller.core.api;

import com.bankbroker.loanapp.dto.admin.BankMasterRequest;
import com.bankbroker.loanapp.dto.admin.BankMasterResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

public interface BankMasterController {

    ResponseEntity<BankMasterResponse> create(BankMasterRequest request);

    ResponseEntity<BankMasterResponse> update(Long id, BankMasterRequest request);

    ResponseEntity<BankMasterResponse> get(Long id);

    ResponseEntity<List<BankMasterResponse>> getAll();

    ResponseEntity<List<BankMasterResponse>> getActive();
    ResponseEntity<List<BankMasterResponse>> getMyBanks();
}
