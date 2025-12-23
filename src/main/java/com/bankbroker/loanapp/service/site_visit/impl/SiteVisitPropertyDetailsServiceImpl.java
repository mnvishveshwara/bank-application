package com.bankbroker.loanapp.service.site_visit.impl;

import com.bankbroker.loanapp.dto.site_visit.SiteVisitPropertyBoundaryDetailsRequest;
import com.bankbroker.loanapp.dto.site_visit.SiteVisitPropertyBoundaryDetailsResponse;
import com.bankbroker.loanapp.dto.site_visit.SiteVisitPropertyDetailsRequest;
import com.bankbroker.loanapp.dto.site_visit.SiteVisitPropertyDetailsResponse;
import com.bankbroker.loanapp.entity.core.AdminUser;
import com.bankbroker.loanapp.entity.core.LoanApplication;
import com.bankbroker.loanapp.entity.site_visit.SiteVisitPropertyBoundaryDetails;
import com.bankbroker.loanapp.entity.site_visit.SiteVisitPropertyDetails;
import com.bankbroker.loanapp.mapper.site_visit.SiteVisitPropertyDetailsMapper;
import com.bankbroker.loanapp.repository.core.LoanApplicationRepository;
import com.bankbroker.loanapp.repository.site_visit.SiteVisitPropertyBoundaryDetailsRepository;
import com.bankbroker.loanapp.repository.site_visit.SiteVisitPropertyDetailsRepository;
import com.bankbroker.loanapp.service.site_visit.api.SiteVisitPropertyDetailsService;
import com.bankbroker.loanapp.util.SecurityUtil;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Transactional
public class SiteVisitPropertyDetailsServiceImpl
        implements SiteVisitPropertyDetailsService {

    private final LoanApplicationRepository loanRepo;
    private final SiteVisitPropertyDetailsRepository detailsRepo;
    private final SiteVisitPropertyBoundaryDetailsRepository boundaryRepo;
    private final SiteVisitPropertyDetailsMapper mapper;
    private final SecurityUtil securityUtil;

    @Override
    public SiteVisitPropertyDetailsResponse savePropertyDetails(
            String applicationId,
            SiteVisitPropertyDetailsRequest request) {

        LoanApplication app = loanRepo.findById(applicationId)
                .orElseThrow();

        AdminUser user = securityUtil.getLoggedInAdmin();

        SiteVisitPropertyDetails entity =
                detailsRepo.findByApplication(app)
                        .orElseGet(() ->
                                mapper.toEntity(request, app, user));

        if (entity.getId() != null) {
            mapper.updateEntity(request, entity);
            entity.setUpdatedBy(user);
            entity.setUpdatedDate(LocalDateTime.now());
        }

        return mapper.toResponse(detailsRepo.save(entity));
    }

    @Override
    public SiteVisitPropertyBoundaryDetailsResponse savePropertyBoundaryDetails(
            String applicationId,
            SiteVisitPropertyBoundaryDetailsRequest request) {

        LoanApplication app = loanRepo.findById(applicationId)
                .orElseThrow();

        AdminUser user = securityUtil.getLoggedInAdmin();

        SiteVisitPropertyBoundaryDetails entity =
                boundaryRepo.findByApplication(app)
                        .orElseGet(() ->
                                SiteVisitPropertyBoundaryDetails.builder()
                                        .application(app)
                                        .createdBy(user)
                                        .createdDate(LocalDateTime.now())
                                        .build());

        BeanUtils.copyProperties(
                request,
                entity,
                "id", "application", "createdBy", "createdDate"
        );

        entity.setUpdatedBy(user);
        entity.setUpdatedDate(LocalDateTime.now());

        boundaryRepo.save(entity);

        return SiteVisitPropertyBoundaryDetailsResponse.builder()
                .applicationId(app.getId())
                .propertyFacing(entity.getPropertyFacing())
                .boundaryMatching(entity.getBoundaryMatching())
                .earthquakeResistant(entity.getEarthquakeResistant())
                .propertyIdentification(entity.getPropertyIdentification())
                .currentZoning(entity.getCurrentZoning())
                .build();
    }
}

