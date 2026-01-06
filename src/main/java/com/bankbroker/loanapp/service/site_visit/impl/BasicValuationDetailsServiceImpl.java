package com.bankbroker.loanapp.service.site_visit.impl;

import com.bankbroker.loanapp.dto.site_visit.BasicValuationDetailsRequest;
import com.bankbroker.loanapp.dto.site_visit.BasicValuationDetailsResponse;
import com.bankbroker.loanapp.dto.site_visit.PropertyOwnerDetailsResponse;
import com.bankbroker.loanapp.dto.stage.ApplicationHistoryRequest;
import com.bankbroker.loanapp.entity.core.AdminUser;
import com.bankbroker.loanapp.entity.core.LoanApplication;
import com.bankbroker.loanapp.entity.enums.ApplicationHistoryStatus;
import com.bankbroker.loanapp.entity.enums.Role;
import com.bankbroker.loanapp.entity.site_visit.BasicValuationDetails;
import com.bankbroker.loanapp.entity.stage.ApplicationCustomerDetails;
import com.bankbroker.loanapp.exception.ResourceNotFoundException;
import com.bankbroker.loanapp.mapper.site_visit.BasicValuationDetailsMapper;
import com.bankbroker.loanapp.repository.core.LoanApplicationRepository;
import com.bankbroker.loanapp.repository.site_visit.BasicValuationDetailsRepository;
import com.bankbroker.loanapp.repository.stage.ApplicationCustomerDetailsRepository;
import com.bankbroker.loanapp.service.core.api.ApplicationStageService;
import com.bankbroker.loanapp.service.site_visit.api.BasicValuationDetailsService;
import com.bankbroker.loanapp.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Slf4j
@Service
@RequiredArgsConstructor
public class BasicValuationDetailsServiceImpl
        implements BasicValuationDetailsService {

    private final LoanApplicationRepository loanRepo;
    private final BasicValuationDetailsRepository repo;
    private final ApplicationCustomerDetailsRepository applicationCustomerDetailsRepo;
    private final BasicValuationDetailsMapper mapper;
    private final ApplicationStageService stageService;
    private final SecurityUtil securityUtil;


    // -------------------------------------------------------
    // SAVE BASIC VALUATION
    // -------------------------------------------------------
    @Override
    @Transactional
    public BasicValuationDetailsResponse saveBasicValuation(
            String applicationId,
            BasicValuationDetailsRequest request) {

        AdminUser logged = securityUtil.getLoggedInAdmin();

        if (logged.getRole() != Role.AGENCY_VALUATOR) {
            throw new RuntimeException("Only valuators can submit site visit details");
        }

        LoanApplication app = loanRepo.findById(applicationId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("LoanApplication", "id", applicationId));

        if (!app.getValuator().getId().equals(logged.getId())) {
            throw new RuntimeException("You are not assigned to this application");
        }

        BasicValuationDetails entity = repo.findByApplication(app)
                .orElseGet(() -> {
                    BasicValuationDetails e = mapper.toEntity(request, app, logged);
                    e.setUpdatedBy(logged);
                    e.setUpdatedDate(LocalDateTime.now());
                    return e;
                });


        if (entity.getId() != null) {
            mapper.updateEntity(request, entity);
            entity.setUpdatedBy(logged);
            entity.setUpdatedDate(LocalDateTime.now());
        }

        entity = repo.save(entity);

        stageService.addHistory(
                applicationId,
                new ApplicationHistoryRequest(
                        ApplicationHistoryStatus.SITE_VISIT_IN_PROGRESS.name(),
                        "Basic valuation details captured",
                        logged.getId()
                )
        );


        return mapper.toResponse(entity);
    }

    // -------------------------------------------------------
    // GET BASIC VALUATION
    // -------------------------------------------------------
    @Override
    public BasicValuationDetailsResponse getBasicValuation(String applicationId) {

        LoanApplication app = loanRepo.findById(applicationId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("LoanApplication", "id", applicationId));

        BasicValuationDetails entity = repo.findByApplication(app)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "BasicValuationDetails",
                                "applicationId",
                                applicationId));

        return mapper.toResponse(entity);
    }

    // -------------------------------------------------------
    // GET PROPERTY OWNER DETAILS (PREFILL API)
    // -------------------------------------------------------
    @Override
    @Transactional(readOnly = true)
    public PropertyOwnerDetailsResponse getPropertyOwnerDetails(String applicationId) {

        LoanApplication application = loanRepo.findById(applicationId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "LoanApplication",
                                "id",
                                applicationId
                        ));

        ApplicationCustomerDetails customerDetails =
                applicationCustomerDetailsRepo.findByApplication(application)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "ApplicationCustomerDetails",
                                        "applicationId",
                                        applicationId
                                ));

        String applicantName = String.join(" ",
                customerDetails.getFirstName(),
                customerDetails.getMiddleName() != null ? customerDetails.getMiddleName() : "",
                customerDetails.getLastName() != null ? customerDetails.getLastName() : ""
        ).trim();

        return PropertyOwnerDetailsResponse.builder()
                .applicantName(applicantName)
                .reportDate(application.getPlannedSiteVisitDate())
                .loanType(customerDetails.getLoanType())
                .build();
    }
}
