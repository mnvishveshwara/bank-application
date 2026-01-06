package com.bankbroker.loanapp.service.site_visit.impl;


import com.bankbroker.loanapp.dto.site_visit.SiteVisitPropertyValueAssessmentLandRequest;
import com.bankbroker.loanapp.dto.site_visit.SiteVisitPropertyValueAssessmentLandResponse;
import com.bankbroker.loanapp.entity.core.AdminUser;
import com.bankbroker.loanapp.entity.core.LoanApplication;
import com.bankbroker.loanapp.entity.site_visit.SiteVisitPropertyValueAssessmentLand;
import com.bankbroker.loanapp.mapper.site_visit.SiteVisitPropertyValueAssessmentLandMapper;
import com.bankbroker.loanapp.repository.core.LoanApplicationRepository;
import com.bankbroker.loanapp.repository.site_visit.SiteVisitPropertyValueAssessmentLandRepository;
import com.bankbroker.loanapp.service.site_visit.api.SiteVisitPropertyValueAssessmentLandService;
import com.bankbroker.loanapp.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class SiteVisitPropertyValueAssessmentLandServiceImpl
        implements SiteVisitPropertyValueAssessmentLandService {

    private final LoanApplicationRepository loanRepo;
    private final SiteVisitPropertyValueAssessmentLandRepository landRepo;
    private final SiteVisitPropertyValueAssessmentLandMapper mapper;
    private final SecurityUtil securityUtil;

    @Override
    public SiteVisitPropertyValueAssessmentLandResponse saveLand(
            String applicationId,
            SiteVisitPropertyValueAssessmentLandRequest request) {

        AdminUser user = securityUtil.getLoggedInAdmin();

        LoanApplication app = loanRepo.findById(applicationId)
                .orElseThrow(() -> new RuntimeException("Application not found"));

        SiteVisitPropertyValueAssessmentLand entity =
                landRepo.findByApplication(app)
                        .orElseGet(() ->
                                mapper.toEntity(request, app, user)
                        );

        if (entity.getId() != null) {
            mapper.updateEntity(request, entity);
            entity.setUpdatedBy(user);
        }

        // 🔢 GOVERNMENT TOTAL VALUE
        entity.setGovtTotalValueActual(
                multiply(entity.getLandAreaAsPerActual(),
                        entity.getGovernmentRatePerSqFt())
        );
        entity.setGovtTotalValueDocument(
                multiply(entity.getLandAreaAsPerDocument(),
                        entity.getGovernmentRatePerSqFt())
        );
        entity.setGovtTotalValueLayout(
                multiply(entity.getLandAreaAsPerLayoutPlan(),
                        entity.getGovernmentRatePerSqFt())
        );

        // 🔢 CONSIDERATION TOTAL VALUE
        entity.setConsiderationTotalValueActual(
                multiply(entity.getLandAreaAsPerActual(),
                        entity.getConsiderationRatePerSqFt())
        );
        entity.setConsiderationTotalValueDocument(
                multiply(entity.getLandAreaAsPerDocument(),
                        entity.getConsiderationRatePerSqFt())
        );
        entity.setConsiderationTotalValueLayout(
                multiply(entity.getLandAreaAsPerLayoutPlan(),
                        entity.getConsiderationRatePerSqFt())
        );

        SiteVisitPropertyValueAssessmentLand saved = landRepo.save(entity);
        return mapper.toResponse(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public SiteVisitPropertyValueAssessmentLandResponse getLand(
            String applicationId) {

        LoanApplication app = loanRepo.findById(applicationId)
                .orElseThrow(() -> new RuntimeException("Application not found"));

        SiteVisitPropertyValueAssessmentLand entity =
                landRepo.findByApplication(app)
                        .orElseThrow(() ->
                                new RuntimeException("Land valuation not found")
                        );

        return mapper.toResponse(entity);
    }

    private Double multiply(Double a, Double b) {
        if (a == null || b == null) return null;
        return a * b;
    }
}