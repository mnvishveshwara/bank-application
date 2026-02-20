package com.bankbroker.loanapp.service.site_visit.impl;

import com.bankbroker.loanapp.dto.site_visit.SiteVisitTechnicalPlotRequest;
import com.bankbroker.loanapp.dto.site_visit.SiteVisitTechnicalPlotResponse;
import com.bankbroker.loanapp.entity.core.AdminUser;
import com.bankbroker.loanapp.entity.core.LoanApplication;
import com.bankbroker.loanapp.entity.enums.AssignmentType;
import com.bankbroker.loanapp.entity.enums.Role;
import com.bankbroker.loanapp.entity.site_visit.SiteVisitTechnicalPlot;
import com.bankbroker.loanapp.exception.ResourceNotFoundException;
import com.bankbroker.loanapp.mapper.site_visit.SiteVisitTechnicalPlotMapper;
import com.bankbroker.loanapp.repository.core.LoanApplicationRepository;
import com.bankbroker.loanapp.repository.site_visit.SiteVisitTechnicalPlotRepository;
import com.bankbroker.loanapp.repository.stage.ApplicationAgencyAssignmentRepository;
import com.bankbroker.loanapp.service.site_visit.api.SiteVisitTechnicalPlotService;
import com.bankbroker.loanapp.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Transactional
public class SiteVisitTechnicalPlotServiceImpl
        implements SiteVisitTechnicalPlotService {

    private final LoanApplicationRepository loanRepo;
    private final SiteVisitTechnicalPlotRepository repo;
    private final SiteVisitTechnicalPlotMapper mapper;
    private final SecurityUtil securityUtil;
    private final ApplicationAgencyAssignmentRepository agencyAssignmentRepo;

//    @Override
//    public SiteVisitTechnicalPlotResponse save(
//            String applicationId,
//            SiteVisitTechnicalPlotRequest request) {
//
//        AdminUser loggedIn = securityUtil.getLoggedInAdmin();
//
//        LoanApplication application = loanRepo.findById(applicationId)
//                .orElseThrow(() -> new RuntimeException("Application not found"));
//
//        SiteVisitTechnicalPlot entity = repo.findByApplication(application)
//                .orElseGet(() ->
//                        mapper.toEntity(request, application, loggedIn)
//                );
//
//        if (entity.getId() != null) {
//            mapper.updateEntity(request, entity);
//            entity.setUpdatedBy(loggedIn);
//        }
//
//        // MATCH LOGIC
//        entity.setEastMatch(match(entity.getEastAsPerSiteVisit(), entity.getEastAsPerLegalDocument()));
//        entity.setWestMatch(match(entity.getWestAsPerSiteVisit(), entity.getWestAsPerLegalDocument()));
//        entity.setNorthMatch(match(entity.getNorthAsPerSiteVisit(), entity.getNorthAsPerLegalDocument()));
//        entity.setSouthMatch(match(entity.getSouthAsPerSiteVisit(), entity.getSouthAsPerLegalDocument()));
//
//        return mapper.toResponse(repo.save(entity));
//    }
@Override
@Transactional
public SiteVisitTechnicalPlotResponse save(
        String applicationId,
        SiteVisitTechnicalPlotRequest request) {

    AdminUser loggedIn = securityUtil.getLoggedInAdmin();
    Role role = loggedIn.getRole();

    // 1. Role Validation
    if (role != Role.AGENCY_VALUATOR && role != Role.BANK_VALUATOR) {
        throw new RuntimeException("Unauthorized: Only assigned valuators can submit technical plot details");
    }

    LoanApplication application = loanRepo.findById(applicationId)
            .orElseThrow(() -> new ResourceNotFoundException("LoanApplication", "id", applicationId));

    // 2. Assignment Validation (Internal vs. Agency)
    boolean isAssigned = false;

    if (application.getAssignmentType() == AssignmentType.INTERNAL) {
        isAssigned = application.getInternalValuator() != null &&
                application.getInternalValuator().getId().equals(loggedIn.getId());
    } else if (application.getAssignmentType() == AssignmentType.AGENCY) {
        isAssigned = agencyAssignmentRepo.existsByApplicationAndAgency(application, loggedIn.getAgency());
    }

    if (!isAssigned) {
        throw new RuntimeException("Access Denied: You are not authorized to perform valuation for this application.");
    }

    // 3. Entity Logic
    SiteVisitTechnicalPlot entity = repo.findByApplication(application)
            .orElseGet(() -> {
                SiteVisitTechnicalPlot e = mapper.toEntity(request, application, loggedIn);
                e.setCreatedBy(loggedIn);
                e.setCreatedDate(LocalDateTime.now());
                return e;
            });

    if (entity.getId() != null) {
        mapper.updateEntity(request, entity);
    }

    entity.setUpdatedBy(loggedIn);
    entity.setUpdatedDate(LocalDateTime.now());

    // 4. Boundary Match Logic (Automatic Validation)
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
