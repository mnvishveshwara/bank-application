
//
//@Service
//@RequiredArgsConstructor
//@Transactional
//public class SiteVisitTechnicalDetailsServiceImpl
//        implements SiteVisitTechnicalDetailsService {
//
//    private final LoanApplicationRepository loanRepo;
//    private final AdminUserRepository adminRepo;
//    private final SiteVisitTechnicalDetailsRepository repo;
//    private final SiteVisitTechnicalDetailsMapper mapper;
//
//    private AdminUser getLoggedIn() {
//        String id = (String) SecurityContextHolder.getContext()
//                .getAuthentication().getPrincipal();
//        return adminRepo.findById(id)
//                .orElseThrow(() -> new RuntimeException("Invalid user"));
//    }
//
//    @Override
//    public SiteVisitTechnicalDetailsResponse saveTechnicalDetails(
//            String applicationId,
//            SiteVisitTechnicalDetailsRequest request) {
//
//        AdminUser logged = getLoggedIn();
//
//        LoanApplication app = loanRepo.findById(applicationId)
//                .orElseThrow(() -> new RuntimeException("Application not found"));
//
//        SiteVisitTechnicalDetails entity =
//                repo.findByApplication(app)
//                        .orElseGet(() -> mapper.toEntity(request, app, logged));
//
//        if (entity.getId() != null) {
//            mapper.updateEntity(request, entity);
//        }
//
//        // 🔢 AUTO CALCULATION
//        double actual = sum(
//                entity.getFirstBasementActual(),
//                entity.getGroundFloorActual(),
//                entity.getFirstFloorActual(),
//                entity.getSecondFloorActual(),
//                entity.getNonRccActual()
//        );
//
//        double document = sum(
//                entity.getFirstBasementDocument(),
//                entity.getGroundFloorDocument(),
//                entity.getFirstFloorDocument(),
//                entity.getSecondFloorDocument(),
//                entity.getNonRccDocument()
//        );
//
//        double approved = sum(
//                entity.getFirstBasementApproved(),
//                entity.getGroundFloorApproved(),
//                entity.getFirstFloorApproved(),
//                entity.getSecondFloorApproved(),
//                entity.getNonRccApproved()
//        );
//
//        entity.setTotalBuaActual(actual);
//        entity.setTotalBuaDocument(document);
//        entity.setTotalBuaApproved(approved);
//
//        entity.setLandAreaMatch(
//                request.getLandAreaAsPerActual() != null &&
//                        request.getLandAreaAsPerDocument() != null &&
//                        request.getLandAreaAsPerLayoutPlan() != null &&
//                        request.getLandAreaAsPerActual()
//                                .equals(request.getLandAreaAsPerDocument()) &&
//                        request.getLandAreaAsPerActual()
//                                .equals(request.getLandAreaAsPerLayoutPlan())
//        );
//
//        return mapper.toResponse(repo.save(entity));
//    }
//
//    @Override
//    @Transactional(readOnly = true)
//    public SiteVisitTechnicalDetailsResponse getTechnicalDetails(String applicationId) {
//
//        LoanApplication app = loanRepo.findById(applicationId)
//                .orElseThrow(() -> new RuntimeException("Application not found"));
//
//        SiteVisitTechnicalDetails entity = repo.findByApplication(app)
//                .orElseThrow(() -> new RuntimeException("Technical details not found"));
//
//        return mapper.toResponse(entity);
//    }
//
//    private double sum(Double... values) {
//        double total = 0;
//        for (Double v : values) {
//            if (v != null) total += v;
//        }
//        return total;
//    }
//}

package com.bankbroker.loanapp.service.site_visit.impl;

import com.bankbroker.loanapp.dto.site_visit.SiteVisitTechnicalDetailsRequest;
import com.bankbroker.loanapp.dto.site_visit.SiteVisitTechnicalDetailsResponse;
import com.bankbroker.loanapp.entity.core.AdminUser;
import com.bankbroker.loanapp.entity.core.LoanApplication;
import com.bankbroker.loanapp.entity.site_visit.SiteVisitTechnicalDetails;
import com.bankbroker.loanapp.mapper.site_visit.SiteVisitTechnicalDetailsMapper;
import com.bankbroker.loanapp.repository.core.LoanApplicationRepository;
import com.bankbroker.loanapp.repository.site_visit.SiteVisitTechnicalDetailsRepository;
import com.bankbroker.loanapp.service.site_visit.api.SiteVisitTechnicalDetailsService;
import com.bankbroker.loanapp.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class SiteVisitTechnicalDetailsServiceImpl
        implements SiteVisitTechnicalDetailsService {

    private final LoanApplicationRepository loanRepo;
    private final SiteVisitTechnicalDetailsRepository repo;
    private final SiteVisitTechnicalDetailsMapper mapper;
    private final SecurityUtil securityUtil;

    @Override
    public SiteVisitTechnicalDetailsResponse saveTechnicalDetails(
            String applicationId,
            SiteVisitTechnicalDetailsRequest request) {

        AdminUser logged = securityUtil.getLoggedInAdmin();

        LoanApplication app = loanRepo.findById(applicationId)
                .orElseThrow(() -> new RuntimeException("Application not found"));

        SiteVisitTechnicalDetails entity =
                repo.findByApplication(app)
                        .orElseGet(() ->
                                mapper.toEntity(request, app, logged));

        if (entity.getId() != null) {
            mapper.updateEntity(request, entity);
        }

        //  AUTO CALCULATION
        double actual = sum(
                entity.getFirstBasementActual(),
                entity.getGroundFloorActual(),
                entity.getFirstFloorActual(),
                entity.getSecondFloorActual(),
                entity.getNonRccActual()
        );

        double document = sum(
                entity.getFirstBasementDocument(),
                entity.getGroundFloorDocument(),
                entity.getFirstFloorDocument(),
                entity.getSecondFloorDocument(),
                entity.getNonRccDocument()
        );

        double approved = sum(
                entity.getFirstBasementApproved(),
                entity.getGroundFloorApproved(),
                entity.getFirstFloorApproved(),
                entity.getSecondFloorApproved(),
                entity.getNonRccApproved()
        );

        entity.setTotalBuaActual(actual);
        entity.setTotalBuaDocument(document);
        entity.setTotalBuaApproved(approved);

        entity.setLandAreaMatch(
                request.getLandAreaAsPerActual() != null &&
                        request.getLandAreaAsPerDocument() != null &&
                        request.getLandAreaAsPerLayoutPlan() != null &&
                        request.getLandAreaAsPerActual()
                                .equals(request.getLandAreaAsPerDocument()) &&
                        request.getLandAreaAsPerActual()
                                .equals(request.getLandAreaAsPerLayoutPlan())
        );

        return mapper.toResponse(repo.save(entity));
    }

    @Override
    @Transactional(readOnly = true)
    public SiteVisitTechnicalDetailsResponse getTechnicalDetails(
            String applicationId) {

        LoanApplication app = loanRepo.findById(applicationId)
                .orElseThrow(() -> new RuntimeException("Application not found"));

        SiteVisitTechnicalDetails entity = repo.findByApplication(app)
                .orElseThrow(() ->
                        new RuntimeException("Technical details not found"));

        return mapper.toResponse(entity);
    }

    private double sum(Double... values) {
        double total = 0;
        for (Double v : values) {
            if (v != null) total += v;
        }
        return total;
    }
}
