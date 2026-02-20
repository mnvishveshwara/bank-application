package com.bankbroker.loanapp.service.site_visit.impl;

import com.bankbroker.loanapp.dto.site_visit.SiteVisitBuildingDetailsRequest;
import com.bankbroker.loanapp.dto.site_visit.SiteVisitBuildingDetailsResponse;
import com.bankbroker.loanapp.entity.core.AdminUser;
import com.bankbroker.loanapp.entity.core.LoanApplication;
import com.bankbroker.loanapp.entity.enums.AssignmentType;
import com.bankbroker.loanapp.entity.enums.Role;
import com.bankbroker.loanapp.entity.site_visit.SiteVisitBuildingDetails;
import com.bankbroker.loanapp.exception.ResourceNotFoundException;
import com.bankbroker.loanapp.mapper.site_visit.SiteVisitBuildingDetailsMapper;
import com.bankbroker.loanapp.repository.core.LoanApplicationRepository;
import com.bankbroker.loanapp.repository.site_visit.SiteVisitBuildingDetailsRepository;
import com.bankbroker.loanapp.repository.stage.ApplicationAgencyAssignmentRepository;
import com.bankbroker.loanapp.service.site_visit.api.SiteVisitBuildingDetailsService;
import com.bankbroker.loanapp.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Transactional
public class SiteVisitBuildingDetailsServiceImpl
        implements SiteVisitBuildingDetailsService {

    private final LoanApplicationRepository loanRepo;
    private final SiteVisitBuildingDetailsRepository repo;
    private final SiteVisitBuildingDetailsMapper mapper;
    private final SecurityUtil securityUtil;
    private final ApplicationAgencyAssignmentRepository agencyAssignmentRepo;
//    @Override
//    public SiteVisitBuildingDetailsResponse saveBuildingDetails(
//            String applicationId,
//            SiteVisitBuildingDetailsRequest request) {
//
//        AdminUser user = securityUtil.getLoggedInAdmin();
//
//        if (user.getRole() != Role.AGENCY_VALUATOR && user.getRole() != Role.BANK_VALUATOR) {
//            throw new RuntimeException(
//                    "Only valuators can submit building details");
//        }
//
//        LoanApplication app = loanRepo.findById(applicationId)
//                .orElseThrow(() ->
//                        new ResourceNotFoundException(
//                                "LoanApplication", "id", applicationId));
//
//        SiteVisitBuildingDetails entity =
//                repo.findByApplication(app)
//                        .orElseGet(() ->
//                                mapper.toEntity(request, app, user));
//
//        if (entity.getId() != null) {
//            mapper.updateEntity(request, entity);
//            entity.setUpdatedBy(user);
//        }
//
//        return mapper.toResponse(repo.save(entity));
//    }

    @Override
    @Transactional
    public SiteVisitBuildingDetailsResponse saveBuildingDetails(
            String applicationId,
            SiteVisitBuildingDetailsRequest request) {

        AdminUser user = securityUtil.getLoggedInAdmin();
        Role role = user.getRole();

        // 1. Role Validation (Fixed logic from || to &&)
        if (role != Role.AGENCY_VALUATOR && role != Role.BANK_VALUATOR) {
            throw new RuntimeException("Unauthorized: Only assigned valuators can submit building details");
        }

        LoanApplication app = loanRepo.findById(applicationId)
                .orElseThrow(() -> new ResourceNotFoundException("LoanApplication", "id", applicationId));

        // 2. Assignment Validation
        boolean isAssigned = false;

        if (app.getAssignmentType() == AssignmentType.INTERNAL) {
            // Match the logged-in bank valuator to the one stored on the application
            isAssigned = app.getInternalValuator() != null &&
                    app.getInternalValuator().getId().equals(user.getId());
        } else if (app.getAssignmentType() == AssignmentType.AGENCY) {
            // Match the logged-in agency valuator to the agency assigned to this application
            isAssigned = agencyAssignmentRepo.existsByApplicationAndAgency(app, user.getAgency());
        }

        if (!isAssigned) {
            throw new RuntimeException("Access Denied: You are not authorized to edit this application.");
        }

        // 3. Entity Logic
        SiteVisitBuildingDetails entity = repo.findByApplication(app)
                .orElseGet(() -> {
                    SiteVisitBuildingDetails e = mapper.toEntity(request, app, user);
                    e.setCreatedBy(user); // Ensure audit for new record
                    e.setCreatedDate(LocalDateTime.now());
                    return e;
                });

        if (entity.getId() != null) {
            mapper.updateEntity(request, entity);
        }

        entity.setUpdatedBy(user);
        entity.setUpdatedDate(LocalDateTime.now());

        // 4. Save and return
        return mapper.toResponse(repo.save(entity));
    }

    @Override
    @Transactional(readOnly = true)
    public SiteVisitBuildingDetailsResponse getBuildingDetails(
            String applicationId) {

        LoanApplication app = loanRepo.findById(applicationId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "LoanApplication", "id", applicationId));

        SiteVisitBuildingDetails entity =
                repo.findByApplication(app)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "SiteVisitBuildingDetails",
                                        "applicationId",
                                        applicationId));

        return mapper.toResponse(entity);
    }
}
