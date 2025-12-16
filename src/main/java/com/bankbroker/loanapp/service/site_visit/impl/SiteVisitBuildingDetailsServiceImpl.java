package com.bankbroker.loanapp.service.site_visit.impl;

import com.bankbroker.loanapp.dto.site_visit.SiteVisitBuildingDetailsRequest;
import com.bankbroker.loanapp.dto.site_visit.SiteVisitBuildingDetailsResponse;
import com.bankbroker.loanapp.entity.core.AdminUser;
import com.bankbroker.loanapp.entity.core.LoanApplication;
import com.bankbroker.loanapp.entity.enums.Role;
import com.bankbroker.loanapp.entity.site_visit.SiteVisitBuildingDetails;
import com.bankbroker.loanapp.exception.ResourceNotFoundException;
import com.bankbroker.loanapp.mapper.site_visit.SiteVisitBuildingDetailsMapper;
import com.bankbroker.loanapp.repository.core.AdminUserRepository;
import com.bankbroker.loanapp.repository.core.LoanApplicationRepository;
import com.bankbroker.loanapp.repository.site_visit.SiteVisitBuildingDetailsRepository;
import com.bankbroker.loanapp.service.site_visit.api.SiteVisitBuildingDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class SiteVisitBuildingDetailsServiceImpl
        implements SiteVisitBuildingDetailsService {

    private final LoanApplicationRepository loanRepo;
    private final SiteVisitBuildingDetailsRepository repo;
    private final SiteVisitBuildingDetailsMapper mapper;
    private final AdminUserRepository adminRepo;

    private AdminUser loggedIn() {
        String id = (String) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();
        return adminRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Invalid logged-in user"));
    }

    @Override
    public SiteVisitBuildingDetailsResponse saveBuildingDetails(
            String applicationId,
            SiteVisitBuildingDetailsRequest request) {

        AdminUser user = loggedIn();

        if (user.getRole() != Role.AGENCY_VALUATOR) {
            throw new RuntimeException("Only valuators can submit building details");
        }

        LoanApplication app = loanRepo.findById(applicationId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("LoanApplication", "id", applicationId));

        SiteVisitBuildingDetails entity = repo.findByApplication(app)
                .orElseGet(() -> mapper.toEntity(request, app, user));

        if (entity.getId() != null) {
            mapper.updateEntity(request, entity);
            entity.setUpdatedBy(user);
        }

        return mapper.toResponse(repo.save(entity));
    }

    @Override
    @Transactional(readOnly = true)
    public SiteVisitBuildingDetailsResponse getBuildingDetails(String applicationId) {

        LoanApplication app = loanRepo.findById(applicationId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("LoanApplication", "id", applicationId));

        SiteVisitBuildingDetails entity = repo.findByApplication(app)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "SiteVisitBuildingDetails",
                                "applicationId",
                                applicationId));

        return mapper.toResponse(entity);
    }
}
