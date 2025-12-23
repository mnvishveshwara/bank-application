package com.bankbroker.loanapp.service.site_visit.impl;

import com.bankbroker.loanapp.dto.site_visit.SiteVisitValuerRemarksRequest;
import com.bankbroker.loanapp.dto.site_visit.SiteVisitValuerRemarksResponse;
import com.bankbroker.loanapp.dto.stage.ApplicationHistoryRequest;
import com.bankbroker.loanapp.entity.core.AdminUser;
import com.bankbroker.loanapp.entity.core.LoanApplication;
import com.bankbroker.loanapp.entity.enums.ApplicationHistoryStatus;
import com.bankbroker.loanapp.entity.site_visit.SiteVisitValuerRemarks;
import com.bankbroker.loanapp.mapper.site_visit.SiteVisitValuerRemarksMapper;
import com.bankbroker.loanapp.repository.core.LoanApplicationRepository;
import com.bankbroker.loanapp.repository.site_visit.SiteVisitValuerRemarksRepository;
import com.bankbroker.loanapp.service.core.api.ApplicationStageService;
import com.bankbroker.loanapp.service.site_visit.api.SiteVisitValuerRemarksService;
import com.bankbroker.loanapp.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Transactional
public class SiteVisitValuerRemarksServiceImpl
        implements SiteVisitValuerRemarksService {

    private final SiteVisitValuerRemarksRepository repository;
    private final LoanApplicationRepository loanApplicationRepository;
    private final SiteVisitValuerRemarksMapper mapper;
    private final ApplicationStageService applicationStageService;
    private final SecurityUtil securityUtil;

    @Override
    public SiteVisitValuerRemarksResponse saveOrUpdate(
            String applicationId,
            SiteVisitValuerRemarksRequest request) {

        LoanApplication application = loanApplicationRepository.findById(applicationId)
                .orElseThrow(() ->
                        new IllegalArgumentException("Invalid application id"));

        AdminUser admin = securityUtil.getLoggedInAdmin();

        // ---------------- UPSERT REMARKS ----------------
        SiteVisitValuerRemarks entity =
                repository.findByApplication_Id(applicationId)
                        .orElseGet(() -> {
                            SiteVisitValuerRemarks e = mapper.toEntity(request);
                            e.setApplication(application);
                            e.setCreatedDate(LocalDateTime.now());
                            e.setCreatedBy(admin);
                            return e;
                        });

        mapper.updateEntity(request, entity);

        entity.setUpdatedDate(LocalDateTime.now());
        entity.setUpdatedBy(admin);

        SiteVisitValuerRemarks saved = repository.save(entity);

        // ---------------- UPDATE APPLICATION STAGE ----------------
        applicationStageService.addHistory(
                applicationId,
                new ApplicationHistoryRequest(
                        ApplicationHistoryStatus.SITE_VISIT_COMPLETED.name(),
                        "Site visit completed with valuer remarks",
                        admin.getId()
                )
        );

        return mapper.toResponse(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public SiteVisitValuerRemarksResponse getByApplicationId(String applicationId) {

        SiteVisitValuerRemarks entity =
                repository.findByApplication_Id(applicationId)
                        .orElseThrow(() ->
                                new IllegalArgumentException("Valuer remarks not found"));

        return mapper.toResponse(entity);
    }
}