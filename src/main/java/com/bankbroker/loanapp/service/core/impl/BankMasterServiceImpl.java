package com.bankbroker.loanapp.service.core.impl;

import com.bankbroker.loanapp.dto.admin.BankMasterRequest;
import com.bankbroker.loanapp.dto.admin.BankMasterResponse;
import com.bankbroker.loanapp.entity.core.AdminUser;
import com.bankbroker.loanapp.entity.core.BankMaster;
import com.bankbroker.loanapp.entity.enums.Role;
import com.bankbroker.loanapp.mapper.core.BankMasterMapper;
import com.bankbroker.loanapp.repository.core.BankMasterRepository;
import com.bankbroker.loanapp.service.core.api.BankMasterService;
import com.bankbroker.loanapp.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class BankMasterServiceImpl implements BankMasterService {

    private final BankMasterRepository repo;
    private final BankMasterMapper mapper;
    private final SecurityUtil securityUtil;


    @Override
    public BankMasterResponse create(BankMasterRequest request) {

        repo.findByBankCode(request.getBankCode())
                .ifPresent(b -> {
                    throw new RuntimeException("Bank code already exists");
                });


        BankMaster bank = mapper.toEntity(request);
        bank.setCreatedDate(LocalDateTime.now());

        return mapper.toResponse(repo.save(bank));
    }

    @Override
    public BankMasterResponse update(Long id, BankMasterRequest request) {

        BankMaster bank = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Bank not found"));

        AdminUser user = securityUtil.getLoggedInAdmin();

        //   MapStruct magic
        mapper.updateEntityFromRequest(request, bank);

        bank.setUpdatedDate(LocalDateTime.now());

        return mapper.toResponse(repo.save(bank));
    }

    @Override
    public BankMasterResponse getById(Long id) {
        return repo.findById(id)
                .map(mapper::toResponse)
                .orElseThrow(() -> new RuntimeException("Bank not found"));
    }

    @Override
    public List<BankMasterResponse> getAll() {
        return repo.findAll()
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    @Override
    public List<BankMasterResponse> getActiveBanks() {
        return repo.findByIsActiveTrue()
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    @Override
    public List<BankMasterResponse> getBanksForLoggedInAdmin() {

        AdminUser loggedIn = securityUtil.getLoggedInAdmin();

        // Other users see only mapped banks
        return loggedIn.getBanks()
                .stream()
                .map(mapper::toResponse)
                .toList();
    }




}
