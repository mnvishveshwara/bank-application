package com.bankbroker.loanapp.service.impl;

import com.bankbroker.loanapp.dto.valuator.AssignValuatorRequest;
import com.bankbroker.loanapp.dto.valuator.AssignValuatorResponse;
import com.bankbroker.loanapp.entity.AdminUser;
import com.bankbroker.loanapp.entity.LoanApplication;
import com.bankbroker.loanapp.entity.enums.ApplicationStageType;
import com.bankbroker.loanapp.entity.enums.Role;
import com.bankbroker.loanapp.entity.valuator.AssignValuator;
import com.bankbroker.loanapp.exception.ResourceNotFoundException;
import com.bankbroker.loanapp.mapper.AssignValuatorMapper;
import com.bankbroker.loanapp.repository.AdminUserRepository;
import com.bankbroker.loanapp.repository.LoanApplicationRepository;
import com.bankbroker.loanapp.repository.valuator.AssignValuatorRepository;
import com.bankbroker.loanapp.service.AssignValuatorService;
import com.bankbroker.loanapp.service.ApplicationStageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.core.context.SecurityContextHolder;

import java.time.LocalDateTime;

@Slf4j
@Service
@RequiredArgsConstructor
public class AssignValuatorServiceImpl implements AssignValuatorService {

    private final LoanApplicationRepository loanRepo;
    private final AdminUserRepository adminRepo;
    private final AssignValuatorRepository repo;
    private final ApplicationStageService stageService;
    private final AssignValuatorMapper mapper;

    /** Logged-in user */
    private AdminUser getLoggedIn() {
        String id = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return adminRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Invalid logged-in user."));
    }

    @Override
    @Transactional
    public AssignValuatorResponse assignValuator(String applicationId, AssignValuatorRequest req) {

        AdminUser logged = getLoggedIn();

        if (logged.getRole() != Role.AGENCY)
            throw new RuntimeException("Only agency admins can assign valuators.");

        // Load application
        LoanApplication app = loanRepo.findById(applicationId)
                .orElseThrow(() -> new ResourceNotFoundException("LoanApplication", "id", applicationId));

        // Validate agency ownership
        if (!logged.getAgencyId().equals(app.getAssignedTo().getAgencyId()))
            throw new RuntimeException("You cannot assign valuators for another agency's application.");

        // Load valuator
        AdminUser valuator = adminRepo.findById(req.getValuatorId())
                .orElseThrow(() -> new ResourceNotFoundException("AdminUser", "id", req.getValuatorId()));

        if (valuator.getRole() != Role.AGENCY_VALUATOR)
            throw new RuntimeException("Selected user is not a valuator.");

        if (!valuator.getAgencyId().equals(logged.getAgencyId()))
            throw new RuntimeException("Valuator does not belong to your agency.");

        // Check if assignment already exists
        AssignValuator assign = repo.findByApplication(app)
                .orElseGet(() -> mapper.toEntity(req, app, valuator, logged));

        // If exists, update using MapStruct
        if (assign.getId() != null) {
            mapper.updateEntity(req, assign);
        }

        assign = repo.save(assign);

        // Update LoanApplication entity
        app.setValuator(valuator);
        app.setUpdatedDate(LocalDateTime.now());
        loanRepo.save(app);

        // Add stage history
        stageService.addHistory(
                applicationId,
                new com.bankbroker.loanapp.dto.stage.ApplicationHistoryRequest(
                        ApplicationStageType.ASSIGN_VALUATOR.name(),
                        logged.getId(),
                        "Assigned to valuator"
                )
        );

        log.info("Application {} assigned to valuator {}", applicationId, valuator.getId());

        // Convert to response
        return mapper.toResponse(assign);
    }

    @Override
    public AssignValuatorResponse getValuatorAssignment(String applicationId) {
        LoanApplication app = loanRepo.findById(applicationId)
                .orElseThrow(() -> new ResourceNotFoundException("LoanApplication", "id", applicationId));

        AssignValuator assign = repo.findByApplication(app)
                .orElseThrow(() -> new ResourceNotFoundException("AssignValuator", "application", applicationId));

        return mapper.toResponse(assign);
    }
}
