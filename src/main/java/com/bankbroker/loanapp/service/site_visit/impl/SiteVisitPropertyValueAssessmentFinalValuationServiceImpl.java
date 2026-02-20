//package com.bankbroker.loanapp.service.site_visit.impl;
//
//import com.bankbroker.loanapp.dto.site_visit.*;
//import com.bankbroker.loanapp.entity.core.AdminUser;
//import com.bankbroker.loanapp.entity.core.LoanApplication;
//import com.bankbroker.loanapp.entity.site_visit.SiteVisitPropertyValueAssessmentFinalValuation;
//import com.bankbroker.loanapp.mapper.site_visit.SiteVisitPropertyValueAssessmentFinalValuationMapper;
//import com.bankbroker.loanapp.repository.core.LoanApplicationRepository;
//import com.bankbroker.loanapp.repository.site_visit.SiteVisitPropertyValueAssessmentFinalValuationRepository;
//import com.bankbroker.loanapp.service.site_visit.api.SiteVisitPropertyValueAssessmentFinalValuationService;
//import com.bankbroker.loanapp.util.SecurityUtil;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//@Service
//@RequiredArgsConstructor
//@Transactional
//public class SiteVisitPropertyValueAssessmentFinalValuationServiceImpl
//        implements SiteVisitPropertyValueAssessmentFinalValuationService {
//
//    private final LoanApplicationRepository loanRepo;
//    private final SiteVisitPropertyValueAssessmentFinalValuationRepository repo;
//    private final SiteVisitPropertyValueAssessmentFinalValuationMapper mapper;
//    private final SecurityUtil securityUtil;
//
//    @Override
//    public SiteVisitPropertyValueAssessmentFinalValuationResponse saveFinalValuation(
//            String applicationId,
//            SiteVisitPropertyValueAssessmentFinalValuationRequest request) {
//
//        AdminUser user = securityUtil.getLoggedInAdmin();
//
//        LoanApplication app = loanRepo.findById(applicationId)
//                .orElseThrow(() -> new RuntimeException("Application not found"));
//
//        SiteVisitPropertyValueAssessmentFinalValuation entity =
//                repo.findByApplication(app)
//                        .orElseGet(() ->
//                                mapper.toEntity(request, app, user)
//                        );
//
//        if (entity.getId() != null) {
//            mapper.updateEntity(request, entity);
//            entity.setUpdatedBy(user);
//        }
//
//        SiteVisitPropertyValueAssessmentFinalValuation saved =
//                repo.save(entity);
//
//        return mapper.toResponse(saved);
//    }
//
//    @Override
//    @Transactional(readOnly = true)
//    public SiteVisitPropertyValueAssessmentFinalValuationResponse getFinalValuation(
//            String applicationId) {
//
//        LoanApplication app = loanRepo.findById(applicationId)
//                .orElseThrow(() -> new RuntimeException("Application not found"));
//
//        SiteVisitPropertyValueAssessmentFinalValuation entity =
//                repo.findByApplication(app)
//                        .orElseThrow(() ->
//                                new RuntimeException("Final valuation not found")
//                        );
//
//        return mapper.toResponse(entity);
//    }
//}


package com.bankbroker.loanapp.service.site_visit.impl;

import com.bankbroker.loanapp.dto.site_visit.*;
import com.bankbroker.loanapp.entity.core.AdminUser;
import com.bankbroker.loanapp.entity.core.LoanApplication;
import com.bankbroker.loanapp.entity.enums.AssignmentType;
import com.bankbroker.loanapp.entity.enums.Role;
import com.bankbroker.loanapp.entity.site_visit.SiteVisitPropertyValueAssessmentFinalValuation;
import com.bankbroker.loanapp.exception.ResourceNotFoundException;
import com.bankbroker.loanapp.mapper.site_visit.SiteVisitPropertyValueAssessmentFinalValuationMapper;
import com.bankbroker.loanapp.repository.core.LoanApplicationRepository;
import com.bankbroker.loanapp.repository.site_visit.SiteVisitPropertyValueAssessmentFinalValuationRepository;
import com.bankbroker.loanapp.repository.stage.ApplicationAgencyAssignmentRepository;
import com.bankbroker.loanapp.service.site_visit.api.SiteVisitPropertyValueAssessmentFinalValuationService;
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
public class SiteVisitPropertyValueAssessmentFinalValuationServiceImpl
        implements SiteVisitPropertyValueAssessmentFinalValuationService {

    private final LoanApplicationRepository loanRepo;
    private final SiteVisitPropertyValueAssessmentFinalValuationRepository repo;
    private final ApplicationAgencyAssignmentRepository agencyAssignmentRepo; // Added
    private final SiteVisitPropertyValueAssessmentFinalValuationMapper mapper;
    private final SecurityUtil securityUtil;

    @Override
    public SiteVisitPropertyValueAssessmentFinalValuationResponse saveFinalValuation(
            String applicationId,
            SiteVisitPropertyValueAssessmentFinalValuationRequest request) {

        AdminUser user = securityUtil.getLoggedInAdmin();
        Role role = user.getRole();

        // 1. Role Validation
        if (role != Role.AGENCY_VALUATOR && role != Role.BANK_VALUATOR) {
            throw new RuntimeException("Unauthorized: Only assigned valuators can save final valuations");
        }

        LoanApplication app = loanRepo.findById(applicationId)
                .orElseThrow(() -> new ResourceNotFoundException("LoanApplication", "id", applicationId));

        // 2. Assignment Validation (Internal vs. Agency)
        validateAssignment(app, user);

        SiteVisitPropertyValueAssessmentFinalValuation entity =
                repo.findByApplication(app)
                        .orElseGet(() -> {
                            SiteVisitPropertyValueAssessmentFinalValuation e = mapper.toEntity(request, app, user);
                            e.setCreatedBy(user);
                            e.setCreatedDate(LocalDateTime.now());
                            return e;
                        });

        if (entity.getId() != null) {
            mapper.updateEntity(request, entity);
        }

        entity.setUpdatedBy(user);
        entity.setUpdatedDate(LocalDateTime.now());

        SiteVisitPropertyValueAssessmentFinalValuation saved = repo.save(entity);
        return mapper.toResponse(saved);
    }

    /**
     * Shared validation to check if the user is authorized for this specific application
     */
    private void validateAssignment(LoanApplication app, AdminUser user) {
        boolean isAssigned = false;

        if (app.getAssignmentType() == AssignmentType.INTERNAL) {
            isAssigned = app.getInternalValuator() != null &&
                    app.getInternalValuator().getId().equals(user.getId());
        } else if (app.getAssignmentType() == AssignmentType.AGENCY) {
            isAssigned = agencyAssignmentRepo.existsByApplicationAndAgency(app, user.getAgency());
        }

        if (!isAssigned) {
            log.warn("Access Denied: User {} attempted to finalize valuation for app {}", user.getId(), app.getId());
            throw new RuntimeException("Access Denied: You are not authorized to edit this application.");
        }
    }

    @Override
    @Transactional(readOnly = true)
    public SiteVisitPropertyValueAssessmentFinalValuationResponse getFinalValuation(
            String applicationId) {

        LoanApplication app = loanRepo.findById(applicationId)
                .orElseThrow(() -> new ResourceNotFoundException("LoanApplication", "id", applicationId));

        SiteVisitPropertyValueAssessmentFinalValuation entity =
                repo.findByApplication(app)
                        .orElseThrow(() -> new RuntimeException("Final valuation not found"));

        return mapper.toResponse(entity);
    }
}