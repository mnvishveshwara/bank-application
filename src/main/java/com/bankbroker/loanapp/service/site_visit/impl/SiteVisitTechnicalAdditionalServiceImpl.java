package com.bankbroker.loanapp.service.site_visit.impl;

import com.bankbroker.loanapp.dto.site_visit.SiteVisitTechnicalAdditionalRequest;
import com.bankbroker.loanapp.dto.site_visit.SiteVisitTechnicalAdditionalResponse;
import com.bankbroker.loanapp.entity.core.AdminUser;
import com.bankbroker.loanapp.entity.core.LoanApplication;
import com.bankbroker.loanapp.entity.enums.AssignmentType;
import com.bankbroker.loanapp.entity.enums.Role;
import com.bankbroker.loanapp.entity.site_visit.SiteVisitTechnicalAdditional;
import com.bankbroker.loanapp.exception.ResourceNotFoundException;
import com.bankbroker.loanapp.mapper.site_visit.SiteVisitTechnicalAdditionalMapper;
import com.bankbroker.loanapp.repository.core.LoanApplicationRepository;
import com.bankbroker.loanapp.repository.site_visit.SiteVisitTechnicalAdditionalRepository;
import com.bankbroker.loanapp.repository.stage.ApplicationAgencyAssignmentRepository;
import com.bankbroker.loanapp.service.site_visit.api.SiteVisitTechnicalAdditionalService;
import com.bankbroker.loanapp.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class SiteVisitTechnicalAdditionalServiceImpl
        implements SiteVisitTechnicalAdditionalService {

    private final LoanApplicationRepository loanRepo;
    private final SiteVisitTechnicalAdditionalRepository additionalRepo;
    private final SiteVisitTechnicalAdditionalMapper mapper;
    private final SecurityUtil securityUtil;
    private final ApplicationAgencyAssignmentRepository agencyAssignmentRepo;

//    @Override
//    public SiteVisitTechnicalAdditionalResponse saveTechnicalAdditional(
//            String applicationId,
//            SiteVisitTechnicalAdditionalRequest request) {
//
//        AdminUser user = securityUtil.getLoggedInAdmin();
//
//        LoanApplication app = loanRepo.findById(applicationId)
//                .orElseThrow(() -> new RuntimeException("Application not found"));
//
//        SiteVisitTechnicalAdditional entity =
//                additionalRepo.findByApplication(app)
//                        .orElseGet(() ->
//                                mapper.toEntity(request, app, user)
//                        );
//
//        if (entity.getId() != null) {
//            mapper.updateEntity(request, entity);
//            entity.setUpdatedBy(user);
//        }
//
//        SiteVisitTechnicalAdditional saved =
//                additionalRepo.save(entity);
//
//        return mapper.toResponse(saved);
//    }

    @Override
    public SiteVisitTechnicalAdditionalResponse saveTechnicalAdditional(
            String applicationId,
            SiteVisitTechnicalAdditionalRequest request) {

        AdminUser user = securityUtil.getLoggedInAdmin();
        Role role = user.getRole();

        // 1. Role Validation
        if (role != Role.AGENCY_VALUATOR && role != Role.BANK_VALUATOR) {
            throw new RuntimeException("Unauthorized: Only assigned valuators can save additional details");
        }

        LoanApplication app = loanRepo.findById(applicationId)
                .orElseThrow(() -> new ResourceNotFoundException("LoanApplication", "id", applicationId));

        // 2. Assignment Validation (Internal vs. Agency)
        validateAssignment(app, user);

        SiteVisitTechnicalAdditional entity = additionalRepo.findByApplication(app)
                .orElseGet(() -> {
                    SiteVisitTechnicalAdditional e = mapper.toEntity(request, app, user);
                    e.setCreatedBy(user);
                    e.setCreatedDate(LocalDateTime.now());
                    return e;
                });

        if (entity.getId() != null) {
            mapper.updateEntity(request, entity);
        }

        entity.setUpdatedBy(user);
        entity.setUpdatedDate(LocalDateTime.now());

        SiteVisitTechnicalAdditional saved = additionalRepo.save(entity);
        return mapper.toResponse(saved);
    }


//    @Override
//    @Transactional(readOnly = true)
//    public SiteVisitTechnicalAdditionalResponse getTechnicalAdditional(
//            String applicationId) {
//
//        LoanApplication app = loanRepo.findById(applicationId)
//                .orElseThrow(() -> new RuntimeException("Application not found"));
//
//        SiteVisitTechnicalAdditional entity =
//                additionalRepo.findByApplication(app)
//                        .orElseThrow(() ->
//                                new RuntimeException("Additional details not found")
//                        );
//
//        return mapper.toResponse(entity);
//    }
@Override
@Transactional(readOnly = true)
public SiteVisitTechnicalAdditionalResponse getTechnicalAdditional(
        String applicationId) {

    LoanApplication app = loanRepo.findById(applicationId)
            .orElseThrow(() -> new ResourceNotFoundException("LoanApplication", "id", applicationId));

    SiteVisitTechnicalAdditional entity = additionalRepo.findByApplication(app)
            .orElseThrow(() -> new RuntimeException("Additional details not found"));

    return mapper.toResponse(entity);
}

    private void validateAssignment(LoanApplication app, AdminUser user) {
        boolean isAssigned = false;

        if (app.getAssignmentType() == AssignmentType.INTERNAL) {
            // Match internal valuator ID
            isAssigned = app.getInternalValuator() != null &&
                    app.getInternalValuator().getId().equals(user.getId());
        } else if (app.getAssignmentType() == AssignmentType.AGENCY) {
            // Match agency assignment for external valuators
            isAssigned = agencyAssignmentRepo.existsByApplicationAndAgency(app, user.getAgency());
        }

        if (!isAssigned) {
            log.warn("User {} attempted unauthorized edit on app {}", user.getId(), app.getId());
            throw new RuntimeException("Access Denied: You are not assigned to this application.");
        }
    }
}
