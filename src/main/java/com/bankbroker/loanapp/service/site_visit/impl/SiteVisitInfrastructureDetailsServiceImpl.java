package com.bankbroker.loanapp.service.site_visit.impl;

import com.bankbroker.loanapp.dto.site_visit.SiteVisitInfrastructureDetailsRequest;
import com.bankbroker.loanapp.dto.site_visit.SiteVisitInfrastructureDetailsResponse;
import com.bankbroker.loanapp.entity.core.AdminUser;
import com.bankbroker.loanapp.entity.core.LoanApplication;
import com.bankbroker.loanapp.entity.enums.AssignmentType;
import com.bankbroker.loanapp.entity.enums.Role;
import com.bankbroker.loanapp.entity.site_visit.SiteVisitInfrastructureDetails;
import com.bankbroker.loanapp.exception.ResourceNotFoundException;
import com.bankbroker.loanapp.mapper.site_visit.SiteVisitInfrastructureDetailsMapper;
import com.bankbroker.loanapp.repository.core.LoanApplicationRepository;
import com.bankbroker.loanapp.repository.site_visit.SiteVisitInfrastructureDetailsRepository;
import com.bankbroker.loanapp.repository.stage.ApplicationAgencyAssignmentRepository;
import com.bankbroker.loanapp.service.site_visit.api.SiteVisitInfrastructureDetailsService;
import com.bankbroker.loanapp.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Slf4j
@Service
@RequiredArgsConstructor
public class SiteVisitInfrastructureDetailsServiceImpl
        implements SiteVisitInfrastructureDetailsService {

    private final LoanApplicationRepository loanRepo;
    private final SiteVisitInfrastructureDetailsRepository repo;
    private final SiteVisitInfrastructureDetailsMapper mapper;
    private final SecurityUtil securityUtil;
    private final ApplicationAgencyAssignmentRepository agencyAssignmentRepo;
//    @Override
//    @Transactional
//    public SiteVisitInfrastructureDetailsResponse saveInfrastructureDetails(
//            String applicationId,
//            SiteVisitInfrastructureDetailsRequest request) {
//
//        AdminUser logged = securityUtil.getLoggedInAdmin();
//
//        if (logged.getRole() != Role.AGENCY_VALUATOR) {
//            throw new RuntimeException(
//                    "Only valuators can submit infrastructure details");
//        }
//
//        LoanApplication app = loanRepo.findById(applicationId)
//                .orElseThrow(() ->
//                        new ResourceNotFoundException(
//                                "LoanApplication", "id", applicationId));
//
//        if (app.getValuator() == null
//                || !app.getValuator().getId().equals(logged.getId())) {
//            throw new RuntimeException(
//                    "You are not assigned to this application");
//        }
//
//        SiteVisitInfrastructureDetails entity =
//                repo.findByApplication(app)
//                        .orElseGet(() ->
//                                mapper.toEntity(request, app, logged));
//
//        if (entity.getId() != null) {
//            mapper.updateEntity(request, entity);
//        }
//
//        entity = repo.save(entity);
//
//
//        return mapper.toResponse(entity);
//    }

    @Override
    @Transactional
    public SiteVisitInfrastructureDetailsResponse saveInfrastructureDetails(
            String applicationId,
            SiteVisitInfrastructureDetailsRequest request) {

        AdminUser logged = securityUtil.getLoggedInAdmin();
        Role role = logged.getRole();

        // 1. Role Validation: Fixed from (==) to (&&) check for dual role support
        if (role != Role.AGENCY_VALUATOR && role != Role.BANK_VALUATOR) {
            throw new RuntimeException("Unauthorized: Only assigned valuators can submit infrastructure details");
        }

        LoanApplication app = loanRepo.findById(applicationId)
                .orElseThrow(() -> new ResourceNotFoundException("LoanApplication", "id", applicationId));

        // 2. Assignment Validation (Dual Logic)
        boolean isAssigned = false;

        if (app.getAssignmentType() == AssignmentType.INTERNAL) {
            // Check internal bank valuator assignment
            isAssigned = app.getInternalValuator() != null &&
                    app.getInternalValuator().getId().equals(logged.getId());
        } else if (app.getAssignmentType() == AssignmentType.AGENCY) {
            // Check agency valuator assignment via mapping table
            isAssigned = agencyAssignmentRepo.existsByApplicationAndAgency(app, logged.getAgency());
        }

        if (!isAssigned) {
            throw new RuntimeException("Access Denied: You are not assigned to perform valuation for this application.");
        }

        // 3. Entity Retrieval and Audit logic
        SiteVisitInfrastructureDetails entity = repo.findByApplication(app)
                .orElseGet(() -> {
                    SiteVisitInfrastructureDetails e = mapper.toEntity(request, app, logged);
                    e.setCreatedBy(logged);
                    e.setCreatedDate(LocalDateTime.now());
                    return e;
                });

        if (entity.getId() != null) {
            mapper.updateEntity(request, entity);
        }

        // Set common updated fields
        entity.setUpdatedBy(logged);
        entity.setUpdatedDate(LocalDateTime.now());

        entity = repo.save(entity);

        return mapper.toResponse(entity);
    }

    @Override
    @Transactional(readOnly = true)
    public SiteVisitInfrastructureDetailsResponse getInfrastructureDetails(
            String applicationId) {

        LoanApplication app = loanRepo.findById(applicationId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "LoanApplication", "id", applicationId));

        SiteVisitInfrastructureDetails entity =
                repo.findByApplication(app)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "InfrastructureDetails",
                                        "applicationId",
                                        applicationId));

        return mapper.toResponse(entity);
    }
}
