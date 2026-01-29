package com.bankbroker.loanapp.service.core.impl;

import com.bankbroker.loanapp.dto.application.LoanApplicationAssignRequest;
import com.bankbroker.loanapp.dto.application.LoanApplicationRequest;
import com.bankbroker.loanapp.dto.application.LoanApplicationResponse;
import com.bankbroker.loanapp.entity.core.AdminUser;
import com.bankbroker.loanapp.entity.core.BankMaster;
import com.bankbroker.loanapp.entity.core.Customer;
import com.bankbroker.loanapp.entity.core.LoanApplication;
import com.bankbroker.loanapp.entity.enums.ApplicationHistoryStatus;
import com.bankbroker.loanapp.exception.ResourceNotFoundException;
import com.bankbroker.loanapp.repository.core.AdminUserRepository;
import com.bankbroker.loanapp.repository.core.ApplicationStageHistoryRepository;
import com.bankbroker.loanapp.repository.core.CustomerRepository;
import com.bankbroker.loanapp.repository.core.LoanApplicationRepository;
import com.bankbroker.loanapp.service.core.api.LoanApplicationService;
import com.bankbroker.loanapp.util.IdGenerator;
import com.bankbroker.loanapp.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LoanApplicationServiceImpl implements LoanApplicationService {

    private final LoanApplicationRepository loanApplicationRepository;
    private final CustomerRepository customerRepository;
    private final AdminUserRepository adminUserRepository;
    private final ApplicationStageHistoryRepository applicationHistoryRepository;

    private final SecurityUtil securityUtil;

    @Override
    public LoanApplicationResponse createApplication(LoanApplicationRequest request) {
        Customer client = customerRepository.findById(request.getClientId())
                .orElseThrow(() -> new ResourceNotFoundException("Customer", "id", request.getClientId()));

        AdminUser createdBy = adminUserRepository.findById(request.getCreatedByAdminId())
                .orElseThrow(() -> new ResourceNotFoundException("AdminUser", "id", request.getCreatedByAdminId()));

        AdminUser assignedTo = null;
        if (request.getAssignedToAdminId() != null) {
            assignedTo = adminUserRepository.findById(request.getAssignedToAdminId())
                    .orElseThrow(() -> new ResourceNotFoundException("AdminUser", "id", request.getAssignedToAdminId()));
        }

        Long bankId = client.getBankId();

        String id = IdGenerator.generateId();
        LoanApplication app = LoanApplication.builder()
                .id(id)
                .client(client)
                .createdBy(createdBy)
                .createdDate(LocalDateTime.now())
                .assignedTo(assignedTo)
                .active(true)
                .updatedDate(LocalDateTime.now())
                .bankId(bankId)
                .build();

        app = loanApplicationRepository.save(app);
        return mapToResponse(app);
    }

    @Override
    public LoanApplicationResponse getApplicationById(String id) {
        LoanApplication app = loanApplicationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("LoanApplication", "id", id));
        return mapToResponse(app);
    }

    @Override
    public List<LoanApplicationResponse> getAllApplications() {
        return loanApplicationRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public List<LoanApplicationResponse> getApplicationsByClientId(String clientId) {
        Customer client = customerRepository.findById(clientId)
                .orElseThrow(() -> new ResourceNotFoundException("Customer", "id", clientId));

        return loanApplicationRepository.findByClient(client)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }


    @Override
    public List<LoanApplicationResponse> getApplicationsForMyBanks() {
        AdminUser user = securityUtil.getLoggedInAdmin();

        // Extract all bank IDs mapped to this admin
        List<Long> bankIds = user.getBanks()
                .stream()
                .map(BankMaster::getId)
                .toList();

        // Fetch applications belonging to those banks
        List<LoanApplication> apps =
                loanApplicationRepository.findByBankIdIn(bankIds);

        return apps.stream()
                .map(this::mapToResponse)
                .toList();
    }


    @Override
    public LoanApplicationResponse assignApplication(String applicationId, LoanApplicationAssignRequest request) {
        LoanApplication app = loanApplicationRepository.findById(applicationId)
                .orElseThrow(() -> new ResourceNotFoundException("LoanApplication", "id", applicationId));

        AdminUser assignedTo = adminUserRepository.findById(request.getAssignedToAdminId())
                .orElseThrow(() -> new ResourceNotFoundException("AdminUser", "id", request.getAssignedToAdminId()));

        app.setAssignedTo(assignedTo);
        app.setUpdatedDate(LocalDateTime.now());
        app = loanApplicationRepository.save(app);
        return mapToResponse(app);
    }

    @Override
    public LoanApplicationResponse updateApplicationStatus(String applicationId, boolean active) {
        LoanApplication app = loanApplicationRepository.findById(applicationId)
                .orElseThrow(() -> new ResourceNotFoundException("LoanApplication", "id", applicationId));

        app.setActive(active);
        app.setUpdatedDate(LocalDateTime.now());
        app = loanApplicationRepository.save(app);
        return mapToResponse(app);
    }

    private LoanApplicationResponse mapToResponse(LoanApplication app) {
        String clientName = app.getClient().getFirstName() + " " +
                (app.getClient().getLastName() != null ? app.getClient().getLastName() : "");

        String createdByName = app.getCreatedBy().getFirstName() + " " +
                (app.getCreatedBy().getLastName() != null ? app.getCreatedBy().getLastName() : "");

        String assignedToId = app.getAssignedTo() != null ? app.getAssignedTo().getId() : null;
        String assignedToName = app.getAssignedTo() != null
                ? app.getAssignedTo().getFirstName() + " " +
                  (app.getAssignedTo().getLastName() != null ? app.getAssignedTo().getLastName() : "")
                : null;

        String s= app.getId();

        ApplicationHistoryStatus status=applicationHistoryRepository.findByApplication(app)
                .map(history -> history.getStatus())
                .orElse(null);

        return LoanApplicationResponse.builder()
                .applicationId(app.getId())
                .active(app.getActive())
                .clientId(app.getClient().getId())
                .clientName(clientName.trim())
                .createdByAdminId(app.getCreatedBy().getId())
                .createdByName(createdByName.trim())
                .assignedToAdminId(assignedToId)
                .assignedToName(assignedToName != null ? assignedToName.trim() : null)
                .bankId(app.getBankId())
                .bankName(app.getBank() != null ? app.getBank().getBankName() : null)
                .createdDate(app.getCreatedDate())
                .updatedDate(app.getUpdatedDate())
                .status(status.name())
                .build();
    }
}
