package com.bankbroker.loanapp.service.site_visit.impl;

import com.bankbroker.loanapp.dto.site_visit.SiteVisitTechnicalSbuaRequest;
import com.bankbroker.loanapp.dto.site_visit.SiteVisitTechnicalSbuaResponse;
import com.bankbroker.loanapp.entity.core.AdminUser;
import com.bankbroker.loanapp.entity.core.LoanApplication;
import com.bankbroker.loanapp.entity.site_visit.SiteVisitTechnicalSbua;
import com.bankbroker.loanapp.mapper.site_visit.SiteVisitTechnicalSbuaMapper;
import com.bankbroker.loanapp.repository.core.LoanApplicationRepository;
import com.bankbroker.loanapp.repository.site_visit.SiteVisitTechnicalSbuaRepository;
import com.bankbroker.loanapp.service.site_visit.api.SiteVisitTechnicalSbuaService;
import com.bankbroker.loanapp.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class SiteVisitTechnicalSbuaServiceImpl
        implements SiteVisitTechnicalSbuaService {

    private final LoanApplicationRepository loanRepo;
    private final SiteVisitTechnicalSbuaRepository sbuaRepo;
    private final SiteVisitTechnicalSbuaMapper mapper;
    private final SecurityUtil securityUtil;

    @Override
    public SiteVisitTechnicalSbuaResponse save(
            String applicationId,
            SiteVisitTechnicalSbuaRequest request) {

        AdminUser user = securityUtil.getLoggedInAdmin();

        LoanApplication app = loanRepo.findById(applicationId)
                .orElseThrow(() -> new RuntimeException("Application not found"));

        SiteVisitTechnicalSbua sbua = sbuaRepo.findByApplication(app)
                .orElseGet(() -> mapper.toEntity(request, app, user));

        if (sbua.getId() != null) {
            mapper.updateEntity(request, sbua);
            sbua.setUpdatedBy(user);
        }

        SiteVisitTechnicalSbua saved = sbuaRepo.save(sbua);
        return mapper.toResponse(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public SiteVisitTechnicalSbuaResponse get(String applicationId) {

        LoanApplication app = loanRepo.findById(applicationId)
                .orElseThrow(() -> new RuntimeException("Application not found"));

        SiteVisitTechnicalSbua sbua = sbuaRepo.findByApplication(app)
                .orElseThrow(() -> new RuntimeException("SBUA details not found"));

        return mapper.toResponse(sbua);
    }
}
