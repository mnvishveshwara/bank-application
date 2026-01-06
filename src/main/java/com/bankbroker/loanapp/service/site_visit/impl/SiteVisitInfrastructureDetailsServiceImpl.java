package com.bankbroker.loanapp.service.site_visit.impl;

import com.bankbroker.loanapp.dto.site_visit.SiteVisitInfrastructureDetailsRequest;
import com.bankbroker.loanapp.dto.site_visit.SiteVisitInfrastructureDetailsResponse;
import com.bankbroker.loanapp.entity.core.AdminUser;
import com.bankbroker.loanapp.entity.core.LoanApplication;
import com.bankbroker.loanapp.entity.enums.Role;
import com.bankbroker.loanapp.entity.site_visit.SiteVisitInfrastructureDetails;
import com.bankbroker.loanapp.exception.ResourceNotFoundException;
import com.bankbroker.loanapp.mapper.site_visit.SiteVisitInfrastructureDetailsMapper;
import com.bankbroker.loanapp.repository.core.LoanApplicationRepository;
import com.bankbroker.loanapp.repository.site_visit.SiteVisitInfrastructureDetailsRepository;
import com.bankbroker.loanapp.service.site_visit.api.SiteVisitInfrastructureDetailsService;
import com.bankbroker.loanapp.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class SiteVisitInfrastructureDetailsServiceImpl
        implements SiteVisitInfrastructureDetailsService {

    private final LoanApplicationRepository loanRepo;
    private final SiteVisitInfrastructureDetailsRepository repo;
    private final SiteVisitInfrastructureDetailsMapper mapper;
    private final SecurityUtil securityUtil;

    @Override
    @Transactional
    public SiteVisitInfrastructureDetailsResponse saveInfrastructureDetails(
            String applicationId,
            SiteVisitInfrastructureDetailsRequest request) {

        AdminUser logged = securityUtil.getLoggedInAdmin();

        if (logged.getRole() != Role.AGENCY_VALUATOR) {
            throw new RuntimeException(
                    "Only valuators can submit infrastructure details");
        }

        LoanApplication app = loanRepo.findById(applicationId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "LoanApplication", "id", applicationId));

        if (app.getValuator() == null
                || !app.getValuator().getId().equals(logged.getId())) {
            throw new RuntimeException(
                    "You are not assigned to this application");
        }

        SiteVisitInfrastructureDetails entity =
                repo.findByApplication(app)
                        .orElseGet(() ->
                                mapper.toEntity(request, app, logged));

        if (entity.getId() != null) {
            mapper.updateEntity(request, entity);
        }

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
