package com.bankbroker.loanapp.service.site_visit.impl;

import com.bankbroker.loanapp.dto.site_visit.SiteVisitTechnicalAdditionalRequest;
import com.bankbroker.loanapp.dto.site_visit.SiteVisitTechnicalAdditionalResponse;
import com.bankbroker.loanapp.entity.core.AdminUser;
import com.bankbroker.loanapp.entity.core.LoanApplication;
import com.bankbroker.loanapp.entity.site_visit.SiteVisitTechnicalAdditional;
import com.bankbroker.loanapp.mapper.site_visit.SiteVisitTechnicalAdditionalMapper;
import com.bankbroker.loanapp.repository.core.LoanApplicationRepository;
import com.bankbroker.loanapp.repository.site_visit.SiteVisitTechnicalAdditionalRepository;
import com.bankbroker.loanapp.service.site_visit.api.SiteVisitTechnicalAdditionalService;
import com.bankbroker.loanapp.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class SiteVisitTechnicalAdditionalServiceImpl
        implements SiteVisitTechnicalAdditionalService {

    private final LoanApplicationRepository loanRepo;
    private final SiteVisitTechnicalAdditionalRepository additionalRepo;
    private final SiteVisitTechnicalAdditionalMapper mapper;
    private final SecurityUtil securityUtil;

    @Override
    public SiteVisitTechnicalAdditionalResponse saveTechnicalAdditional(
            String applicationId,
            SiteVisitTechnicalAdditionalRequest request) {

        AdminUser user = securityUtil.getLoggedInAdmin();

        LoanApplication app = loanRepo.findById(applicationId)
                .orElseThrow(() -> new RuntimeException("Application not found"));

        SiteVisitTechnicalAdditional entity =
                additionalRepo.findByApplication(app)
                        .orElseGet(() ->
                                mapper.toEntity(request, app, user)
                        );

        if (entity.getId() != null) {
            mapper.updateEntity(request, entity);
            entity.setUpdatedBy(user);
        }

        SiteVisitTechnicalAdditional saved =
                additionalRepo.save(entity);

        return mapper.toResponse(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public SiteVisitTechnicalAdditionalResponse getTechnicalAdditional(
            String applicationId) {

        LoanApplication app = loanRepo.findById(applicationId)
                .orElseThrow(() -> new RuntimeException("Application not found"));

        SiteVisitTechnicalAdditional entity =
                additionalRepo.findByApplication(app)
                        .orElseThrow(() ->
                                new RuntimeException("Additional details not found")
                        );

        return mapper.toResponse(entity);
    }
}
