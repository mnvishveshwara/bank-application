package com.bankbroker.loanapp.service.core.impl;

import com.bankbroker.loanapp.dto.site_visit.SiteVisitRequest;
import com.bankbroker.loanapp.dto.stage.ApplicationHistoryRequest;
import com.bankbroker.loanapp.dto.stage.ApplicationHistoryResponse;
import com.bankbroker.loanapp.dto.valuator.AssignValuatorRequest;
import com.bankbroker.loanapp.dto.valuator.AssignValuatorResponse;
import com.bankbroker.loanapp.entity.core.AdminUser;
import com.bankbroker.loanapp.entity.core.LoanApplication;
import com.bankbroker.loanapp.entity.enums.ApplicationHistoryStatus;
import com.bankbroker.loanapp.entity.enums.Role;
import com.bankbroker.loanapp.entity.valuator.AssignValuator;
import com.bankbroker.loanapp.exception.ResourceNotFoundException;
import com.bankbroker.loanapp.mapper.core.AssignValuatorMapper;
import com.bankbroker.loanapp.repository.core.AdminUserRepository;
import com.bankbroker.loanapp.repository.core.LoanApplicationRepository;
import com.bankbroker.loanapp.repository.valuator.AssignValuatorRepository;
import com.bankbroker.loanapp.service.core.api.AssignValuatorService;
import com.bankbroker.loanapp.service.core.api.ApplicationStageService;
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

        AdminUser valuator = adminRepo.findById(req.getValuatorId())
                .orElseThrow(() -> new ResourceNotFoundException("Valuator", "valuatorId", req.getValuatorId()));

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
                new ApplicationHistoryRequest(
                        ApplicationHistoryStatus.VALUATOR_ASSIGNED.name(),
                        "Assigned to valuator",
                        logged.getId()
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


    @Override
    @Transactional
    public ApplicationHistoryResponse scheduleSiteVisit(String applicationId, SiteVisitRequest req) {

        AdminUser logged = getLoggedIn();

        if (logged.getRole() != Role.AGENCY_VALUATOR)
            throw new RuntimeException("Only valuators can update site visit status.");

        LoanApplication app = loanRepo.findById(applicationId)
                .orElseThrow(() -> new ResourceNotFoundException("LoanApplication", "id", applicationId));

        // Ensure same valuator
        if (!app.getValuator().getId().equals(logged.getId()))
            throw new RuntimeException("You are not assigned to this application.");

        // Update planned visit date
        app.setPlannedSiteVisitDate(req.getSiteVisitDate());
        app.setUpdatedDate(LocalDateTime.now());
        loanRepo.save(app);

        // Save stage history (returns DTO)
        ApplicationHistoryResponse history = stageService.addHistory(
                applicationId,
                new ApplicationHistoryRequest(
                        ApplicationHistoryStatus.SITE_VISIT_SCHEDULED.name(),
                        req.getRemarks() != null ? req.getRemarks() : "Site visit scheduled",
                        logged.getId()
                )
        );

        // return the DTO directly
        return history;
    }


}
