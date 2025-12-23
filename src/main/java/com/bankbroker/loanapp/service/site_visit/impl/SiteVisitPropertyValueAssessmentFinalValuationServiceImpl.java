package com.bankbroker.loanapp.service.site_visit.impl;

import com.bankbroker.loanapp.dto.site_visit.*;
import com.bankbroker.loanapp.entity.core.AdminUser;
import com.bankbroker.loanapp.entity.core.LoanApplication;
import com.bankbroker.loanapp.entity.site_visit.SiteVisitPropertyValueAssessmentFinalValuation;
import com.bankbroker.loanapp.mapper.site_visit.SiteVisitPropertyValueAssessmentFinalValuationMapper;
import com.bankbroker.loanapp.repository.core.LoanApplicationRepository;
import com.bankbroker.loanapp.repository.site_visit.SiteVisitPropertyValueAssessmentFinalValuationRepository;
import com.bankbroker.loanapp.service.site_visit.api.SiteVisitPropertyValueAssessmentFinalValuationService;
import com.bankbroker.loanapp.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class SiteVisitPropertyValueAssessmentFinalValuationServiceImpl
        implements SiteVisitPropertyValueAssessmentFinalValuationService {

    private final LoanApplicationRepository loanRepo;
    private final SiteVisitPropertyValueAssessmentFinalValuationRepository repo;
    private final SiteVisitPropertyValueAssessmentFinalValuationMapper mapper;
    private final SecurityUtil securityUtil;

    @Override
    public SiteVisitPropertyValueAssessmentFinalValuationResponse save(
            String applicationId,
            SiteVisitPropertyValueAssessmentFinalValuationRequest request) {

        AdminUser user = securityUtil.getLoggedInAdmin();

        LoanApplication app = loanRepo.findById(applicationId)
                .orElseThrow(() -> new RuntimeException("Application not found"));

        SiteVisitPropertyValueAssessmentFinalValuation entity =
                repo.findByApplication(app)
                        .orElseGet(() ->
                                mapper.toEntity(request, app, user)
                        );

        if (entity.getId() != null) {
            mapper.updateEntity(request, entity);
            entity.setUpdatedBy(user);
        }

        SiteVisitPropertyValueAssessmentFinalValuation saved =
                repo.save(entity);

        return mapper.toResponse(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public SiteVisitPropertyValueAssessmentFinalValuationResponse get(
            String applicationId) {

        LoanApplication app = loanRepo.findById(applicationId)
                .orElseThrow(() -> new RuntimeException("Application not found"));

        SiteVisitPropertyValueAssessmentFinalValuation entity =
                repo.findByApplication(app)
                        .orElseThrow(() ->
                                new RuntimeException("Final valuation not found")
                        );

        return mapper.toResponse(entity);
    }
}
