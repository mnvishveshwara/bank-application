package com.bankbroker.loanapp.service.site_visit.impl;

import com.bankbroker.loanapp.dto.site_visit.SiteVisitTechnicalPlotRequest;
import com.bankbroker.loanapp.dto.site_visit.SiteVisitTechnicalPlotResponse;
import com.bankbroker.loanapp.entity.core.AdminUser;
import com.bankbroker.loanapp.entity.core.LoanApplication;
import com.bankbroker.loanapp.entity.site_visit.SiteVisitTechnicalPlot;
import com.bankbroker.loanapp.mapper.site_visit.SiteVisitTechnicalPlotMapper;
import com.bankbroker.loanapp.repository.core.LoanApplicationRepository;
import com.bankbroker.loanapp.repository.site_visit.SiteVisitTechnicalPlotRepository;
import com.bankbroker.loanapp.service.site_visit.api.SiteVisitTechnicalPlotService;
import com.bankbroker.loanapp.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class SiteVisitTechnicalPlotServiceImpl
        implements SiteVisitTechnicalPlotService {

    private final LoanApplicationRepository loanRepo;
    private final SiteVisitTechnicalPlotRepository repo;
    private final SiteVisitTechnicalPlotMapper mapper;
    private final SecurityUtil securityUtil;

    @Override
    public SiteVisitTechnicalPlotResponse save(
            String applicationId,
            SiteVisitTechnicalPlotRequest request) {

        AdminUser loggedIn = securityUtil.getLoggedInAdmin();

        LoanApplication application = loanRepo.findById(applicationId)
                .orElseThrow(() -> new RuntimeException("Application not found"));

        SiteVisitTechnicalPlot entity = repo.findByApplication(application)
                .orElseGet(() ->
                        mapper.toEntity(request, application, loggedIn)
                );

        if (entity.getId() != null) {
            mapper.updateEntity(request, entity);
            entity.setUpdatedBy(loggedIn);
        }

        // MATCH LOGIC
        entity.setEastMatch(match(entity.getEastAsPerSiteVisit(), entity.getEastAsPerLegalDocument()));
        entity.setWestMatch(match(entity.getWestAsPerSiteVisit(), entity.getWestAsPerLegalDocument()));
        entity.setNorthMatch(match(entity.getNorthAsPerSiteVisit(), entity.getNorthAsPerLegalDocument()));
        entity.setSouthMatch(match(entity.getSouthAsPerSiteVisit(), entity.getSouthAsPerLegalDocument()));

        return mapper.toResponse(repo.save(entity));
    }

    @Override
    @Transactional(readOnly = true)
    public SiteVisitTechnicalPlotResponse get(String applicationId) {

        LoanApplication application = loanRepo.findById(applicationId)
                .orElseThrow(() -> new RuntimeException("Application not found"));

        SiteVisitTechnicalPlot entity = repo.findByApplication(application)
                .orElseThrow(() -> new RuntimeException("Plot details not found"));

        return mapper.toResponse(entity);
    }

    private boolean match(String a, String b) {
        return a != null && b != null && a.trim().equalsIgnoreCase(b.trim());
    }
}
