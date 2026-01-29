package com.bankbroker.loanapp.service.core.api;

import com.bankbroker.loanapp.dto.admin.BankMasterRequest;
import com.bankbroker.loanapp.dto.admin.BankMasterResponse;

import java.util.List;

public interface BankMasterService {

    BankMasterResponse create(BankMasterRequest request);

    BankMasterResponse update(Long id, BankMasterRequest request);

    BankMasterResponse getById(Long id);

    List<BankMasterResponse> getAll();

    List<BankMasterResponse> getActiveBanks();

    List<BankMasterResponse> getBanksForLoggedInAdmin();

}
