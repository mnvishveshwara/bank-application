package com.bankbroker.loanapp.service.site_visit.impl;

import com.bankbroker.loanapp.dto.site_visit.SiteVisitTechnicalSbuaRequest;
import com.bankbroker.loanapp.dto.site_visit.SiteVisitTechnicalSbuaResponse;
import com.bankbroker.loanapp.entity.core.AdminUser;
import com.bankbroker.loanapp.entity.core.LoanApplication;
import com.bankbroker.loanapp.entity.enums.AssignmentType;
import com.bankbroker.loanapp.entity.enums.Role;
import com.bankbroker.loanapp.entity.site_visit.SiteVisitTechnicalSbua;
import com.bankbroker.loanapp.exception.ResourceNotFoundException;
import com.bankbroker.loanapp.mapper.site_visit.SiteVisitTechnicalSbuaMapper;
import com.bankbroker.loanapp.repository.core.LoanApplicationRepository;
import com.bankbroker.loanapp.repository.site_visit.SiteVisitTechnicalSbuaRepository;
import com.bankbroker.loanapp.repository.stage.ApplicationAgencyAssignmentRepository;
import com.bankbroker.loanapp.service.site_visit.api.SiteVisitTechnicalSbuaService;
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
public class SiteVisitTechnicalSbuaServiceImpl
        implements SiteVisitTechnicalSbuaService {

    private final LoanApplicationRepository loanRepo;
    private final SiteVisitTechnicalSbuaRepository sbuaRepo;
    private final SiteVisitTechnicalSbuaMapper mapper;
    private final SecurityUtil securityUtil;
    private final ApplicationAgencyAssignmentRepository agencyAssignmentRepo;

//    @Override
//    public SiteVisitTechnicalSbuaResponse save(
//            String applicationId,
//            SiteVisitTechnicalSbuaRequest request) {
//
//        AdminUser user = securityUtil.getLoggedInAdmin();
//
//        LoanApplication app = loanRepo.findById(applicationId)
//                .orElseThrow(() -> new RuntimeException("Application not found"));
//
//        SiteVisitTechnicalSbua sbua = sbuaRepo.findByApplication(app)
//                .orElseGet(() -> mapper.toEntity(request, app, user));
//
//        if (sbua.getId() != null) {
//            mapper.updateEntity(request, sbua);
//            sbua.setUpdatedBy(user);
//        }
//
//        SiteVisitTechnicalSbua saved = sbuaRepo.save(sbua);
//        return mapper.toResponse(saved);
//    }
@Override
public SiteVisitTechnicalSbuaResponse save(
        String applicationId,
        SiteVisitTechnicalSbuaRequest request) {

    AdminUser user = securityUtil.getLoggedInAdmin();
    Role role = user.getRole();

    // 1. Role Validation
    if (role != Role.AGENCY_VALUATOR && role != Role.BANK_VALUATOR) {
        throw new RuntimeException("Unauthorized: Only assigned valuators can save SBUA details");
    }

    LoanApplication app = loanRepo.findById(applicationId)
            .orElseThrow(() -> new ResourceNotFoundException("LoanApplication", "id", applicationId));

    // 2. Assignment Validation
    validateAssignment(app, user);

    SiteVisitTechnicalSbua sbua = sbuaRepo.findByApplication(app)
            .orElseGet(() -> {
                SiteVisitTechnicalSbua entity = mapper.toEntity(request, app, user);
                entity.setCreatedBy(user);
                entity.setCreatedDate(LocalDateTime.now());
                return entity;
            });

    if (sbua.getId() != null) {
        mapper.updateEntity(request, sbua);
    }

    sbua.setUpdatedBy(user);
    sbua.setUpdatedDate(LocalDateTime.now());

    SiteVisitTechnicalSbua saved = sbuaRepo.save(sbua);
    return mapper.toResponse(saved);
}

//    @Override
//    @Transactional(readOnly = true)
//    public SiteVisitTechnicalSbuaResponse get(String applicationId) {
//
//        LoanApplication app = loanRepo.findById(applicationId)
//                .orElseThrow(() -> new RuntimeException("Application not found"));
//
//        SiteVisitTechnicalSbua sbua = sbuaRepo.findByApplication(app)
//                .orElseThrow(() -> new RuntimeException("SBUA details not found"));
//
//        return mapper.toResponse(sbua);
//    }

    @Override
    @Transactional(readOnly = true)
    public SiteVisitTechnicalSbuaResponse get(String applicationId) {
        LoanApplication app = loanRepo.findById(applicationId)
                .orElseThrow(() -> new ResourceNotFoundException("LoanApplication", "id", applicationId));

        SiteVisitTechnicalSbua sbua = sbuaRepo.findByApplication(app)
                .orElseThrow(() -> new RuntimeException("SBUA details not found"));

        return mapper.toResponse(sbua);
    }
    private void validateAssignment(LoanApplication app, AdminUser user) {
        boolean isAssigned = false;

        if (app.getAssignmentType() == AssignmentType.INTERNAL) {
            isAssigned = app.getInternalValuator() != null &&
                    app.getInternalValuator().getId().equals(user.getId());
        } else if (app.getAssignmentType() == AssignmentType.AGENCY) {
            isAssigned = agencyAssignmentRepo.existsByApplicationAndAgency(app, user.getAgency());
        }

        if (!isAssigned) {
            log.warn("Unauthorized access attempt by user {} for application {}", user.getId(), app.getId());
            throw new RuntimeException("Access Denied: You are not authorized to edit this application.");
        }
    }
}
