package com.bankbroker.loanapp.service.site_visit.impl;

import com.bankbroker.loanapp.dto.site_visit.*;
import com.bankbroker.loanapp.entity.core.AdminUser;
import com.bankbroker.loanapp.entity.core.LoanApplication;
import com.bankbroker.loanapp.entity.site_visit.*;
import com.bankbroker.loanapp.mapper.site_visit.*;
import com.bankbroker.loanapp.repository.core.LoanApplicationRepository;
import com.bankbroker.loanapp.repository.site_visit.SiteVisitTechnicalBuaRepository;
import com.bankbroker.loanapp.service.site_visit.api.SiteVisitTechnicalBuaService;
import com.bankbroker.loanapp.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class SiteVisitTechnicalBuaServiceImpl
        implements SiteVisitTechnicalBuaService {

    private final LoanApplicationRepository loanRepo;
    private final SiteVisitTechnicalBuaRepository buaRepo;
    private final SiteVisitTechnicalBuaLevelMapper levelMapper;
    private final SiteVisitTechnicalBuaMapper buaMapper;
    private final SecurityUtil securityUtil;

    @Override
    public SiteVisitTechnicalBuaResponse save(
            String applicationId,
            SiteVisitTechnicalBuaRequest request) {

        AdminUser user = securityUtil.getLoggedInAdmin();

        LoanApplication app = loanRepo.findById(applicationId)
                .orElseThrow(() -> new RuntimeException("Application not found"));

        SiteVisitTechnicalBua bua = buaRepo.findByApplication(app)
                .orElseGet(() -> SiteVisitTechnicalBua.builder()
                        .application(app)
                        .createdBy(user)
                        .createdDate(LocalDateTime.now())
                        .build());

        bua.setBasements(request.getBasements());
        bua.setFloors(request.getFloors());
        bua.setNonRcc(request.getNonRcc());
        bua.setUpdatedBy(user);
        bua.setUpdatedDate(LocalDateTime.now());

        // 🔁 Clear & rebuild dynamic levels
        bua.getLevels().clear();

        List<SiteVisitTechnicalBuaLevel> levels = new ArrayList<>();
        double totalActual = 0, totalDoc = 0, totalApproved = 0;

        for (SiteVisitTechnicalBuaLevelRequest lr : request.getLevels()) {

            SiteVisitTechnicalBuaLevel level = levelMapper.toEntity(lr);
            level.setBua(bua);

            totalActual += lr.getAreaActual() == null ? 0 : lr.getAreaActual();
            totalDoc += lr.getAreaDocument() == null ? 0 : lr.getAreaDocument();
            totalApproved += lr.getAreaApproved() == null ? 0 : lr.getAreaApproved();

            levels.add(level);
        }

        bua.setLevels(levels);
        bua.setTotalBuaActual(totalActual);
        bua.setTotalBuaDocument(totalDoc);
        bua.setTotalBuaApproved(totalApproved);

        SiteVisitTechnicalBua saved = buaRepo.save(bua);

        SiteVisitTechnicalBuaResponse response = buaMapper.toResponse(saved);
        response.setLevels(
                saved.getLevels()
                        .stream()
                        .map(levelMapper::toResponse)
                        .toList()
        );

        return response;
    }

    @Override
    @Transactional(readOnly = true)
    public SiteVisitTechnicalBuaResponse get(String applicationId) {

        LoanApplication app = loanRepo.findById(applicationId)
                .orElseThrow(() -> new RuntimeException("Application not found"));

        SiteVisitTechnicalBua bua = buaRepo.findByApplication(app)
                .orElseThrow(() -> new RuntimeException("BUA not found"));

        SiteVisitTechnicalBuaResponse response = buaMapper.toResponse(bua);
        response.setLevels(
                bua.getLevels()
                        .stream()
                        .map(levelMapper::toResponse)
                        .toList()
        );

        return response;
    }
}
