package com.bankbroker.loanapp.service.core.impl;

import com.bankbroker.loanapp.dto.application.LoanApplicationResponse;
import com.bankbroker.loanapp.dto.valuator.ValuatorRequest;
import com.bankbroker.loanapp.dto.valuator.ValuatorResponse;
import com.bankbroker.loanapp.entity.core.AdminUser;
import com.bankbroker.loanapp.entity.core.AgencyMaster;
import com.bankbroker.loanapp.entity.core.LoanApplication;
import com.bankbroker.loanapp.entity.enums.Role;
import com.bankbroker.loanapp.entity.valuator.ValuatorMaster;
import com.bankbroker.loanapp.exception.ResourceNotFoundException;
import com.bankbroker.loanapp.mapper.core.ValuatorMapper;
import com.bankbroker.loanapp.repository.core.*;
import com.bankbroker.loanapp.repository.valuator.ValuatorMasterRepository;
import com.bankbroker.loanapp.service.core.api.ValuatorMasterService;
import com.bankbroker.loanapp.util.IdGenerator;
import com.bankbroker.loanapp.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ValuatorMasterServiceImpl implements ValuatorMasterService {

    private final AgencyMasterRepository agencyRepo;
    private final ValuatorMasterRepository valuatorRepo;
    private final AdminUserRepository adminUserRepo;
    private final PasswordEncoder passwordEncoder;
    private final ValuatorMapper mapper;
    private final LoanApplicationRepository loanApplicationRepository;
    private final ApplicationStageHistoryRepository applicationStageHistoryRepository;
    private final SecurityUtil securityUtil;

    // -------------------------------------------------------------------------
    // CREATE VALUATOR
    // -------------------------------------------------------------------------
    @Override
    @Transactional
    public ValuatorResponse createValuator(ValuatorRequest request) {

        AdminUser loggedIn = securityUtil.getLoggedInAdmin();

        if (loggedIn.getRole() != Role.AGENCY) {
            throw new RuntimeException("Only agency admins can create valuators");
        }

        Long agencyId = loggedIn.getAgencyId();

        AgencyMaster agency = agencyRepo.findById(agencyId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("AgencyMaster", "id", agencyId));

        //  Create login user for valuator
        AdminUser valuatorLogin = AdminUser.builder()
                .id(IdGenerator.generateId("VAL"))
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .firstName(request.getName())
                .lastName(request.getLastName())
                .phoneNumber(request.getPhone())
                .role(Role.AGENCY_VALUATOR)
                .agencyId(agencyId)
                .banks(agency.getBanks())
                .createdDate(LocalDateTime.now())
                .build();

        valuatorLogin = adminUserRepo.save(valuatorLogin);

        //  Create valuator profile
        ValuatorMaster valuator =
                mapper.toEntity(request, agency, loggedIn, valuatorLogin);

        valuator = valuatorRepo.save(valuator);



        return mapper.toResponse(valuator);
    }

    // -------------------------------------------------------------------------
    //  UPDATE VALUATOR
    // -------------------------------------------------------------------------
    @Override
    @Transactional
    public ValuatorResponse updateValuator(Long valuatorId, ValuatorRequest request) {

        ValuatorMaster valuator = valuatorRepo.findById(valuatorId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("ValuatorMaster", "id", valuatorId));

        AdminUser updater = securityUtil.getLoggedInAdmin();

        mapper.updateEntity(request, valuator, updater);

        return mapper.toResponse(valuatorRepo.save(valuator));
    }

    // -------------------------------------------------------------------------
    //  GET VALUATOR BY ID
    // -------------------------------------------------------------------------
    @Override
    public ValuatorResponse getValuator(Long valuatorId) {

        ValuatorMaster valuator = valuatorRepo.findById(valuatorId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("ValuatorMaster", "id", valuatorId));

        return mapper.toResponse(valuator);
    }

    // -------------------------------------------------------------------------
    //  GET ALL VALUATORS OF AN AGENCY
    // -------------------------------------------------------------------------
    @Override
    public List<ValuatorResponse> getAllValuators() {

        AdminUser loggedIn = securityUtil.getLoggedInAdmin();

        if (loggedIn.getRole() != Role.AGENCY) {
            throw new RuntimeException("Only agency admins can view valuators");
        }

        Long agencyId = loggedIn.getAgencyId();

        AgencyMaster agency = agencyRepo.findById(agencyId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("AgencyMaster", "id", agencyId));

        return valuatorRepo.findByAgency(agency)
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    // -------------------------------------------------------------------------
    //  DELETE VALUATOR
    // -------------------------------------------------------------------------
    @Override
    @Transactional
    public void deleteValuator(Long valuatorId) {

        ValuatorMaster valuator = valuatorRepo.findById(valuatorId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("ValuatorMaster", "id", valuatorId));

        if (valuator.getLoginAccount() != null) {
            adminUserRepo.delete(valuator.getLoginAccount());
        }

        valuatorRepo.delete(valuator);


    }



    @Override
    public List<LoanApplicationResponse> getApplicationsForLoggedInValuator() {
        AdminUser loggedUser = securityUtil.getLoggedInAdmin();
        Role role = loggedUser.getRole();

        log.info("Role attempting access: {}", role);

        // FIX: Logical AND check. A role cannot be both at the same time.
        // If it is NOT Agency AND NOT Bank, then throw exception.
        if (role != Role.AGENCY_VALUATOR && role != Role.BANK_VALUATOR) {
            throw new RuntimeException("Unauthorized: Only valuators can access assigned applications.");
        }

        // This repository method should handle looking up either the internal_valuator_id
        // or the agency_assignment mapping depending on how you wrote the query.
        List<LoanApplication> apps = loanApplicationRepository
                .findApplicationsByValuatorId(loggedUser.getId());

        log.info("Found {} applications for valuator {}", apps.size(), loggedUser.getId());

        return apps.stream()
                .map(app -> {
                    // Fetch the current stage status
                    String status = applicationStageHistoryRepository
                            .findByApplication(app)
                            .map(h -> h.getStatus().name())
                            .orElse("NOT_STARTED");

                    return LoanApplicationResponse.builder()
                            .applicationId(app.getId())
                            .active(app.getActive())
                            .status(status)

                            // NEW CHANGE: Include assignment type so frontend knows if it's INTERNAL/AGENCY
                            .assignmentType(app.getAssignmentType() != null ? app.getAssignmentType().name() : null)

                            .clientId(app.getClient() != null ? app.getClient().getId() : null)
                            .clientName(app.getClient() != null
                                    ? app.getClient().getFirstName() + " " + app.getClient().getLastName()
                                    : null)

                            .createdByAdminId(app.getCreatedBy() != null ? app.getCreatedBy().getId() : null)
                            .createdByName(app.getCreatedBy() != null
                                    ? app.getCreatedBy().getFirstName() + " " + app.getCreatedBy().getLastName()
                                    : null)

                            .bankId(app.getBankId())
                            .bankName(app.getBank() != null ? app.getBank().getBankName() : null)
                            .createdDate(app.getCreatedDate())
                            .updatedDate(app.getUpdatedDate())
                            .build();
                })
                .toList();
    }
}
