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
    private final AdminUserRepository adminUserRepository;
    private final ApplicationStageCurrentRepository applicationStageCurrentRepository;
    private final ApplicationStageHistoryRepository applicationStageHistoryRepository;


    private String getLoggedInAdminId() {
        return (String) org.springframework.security.core.context.SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();
    }
    private AdminUser getLoggedInAdmin() {
        String id = (String) org.springframework.security.core.context.SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();
        return adminUserRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Invalid logged-in admin id"));
    }

    // -------------------------------------------------------------------------
    // 1️⃣ CREATE VALUATOR
    // -------------------------------------------------------------------------
    @Override
    @Transactional
    public ValuatorResponse createValuator(ValuatorRequest request) {



        AdminUser loggedIn = getLoggedInAdmin();
        if (loggedIn.getRole() != Role.AGENCY) {
            throw new RuntimeException("Only agency admins can create valuators");
        }
        Long agencyId = loggedIn.getAgencyId();
        log.info("agencyId: {}", agencyId);
        AgencyMaster agency = agencyRepo.findById(agencyId)
                .orElseThrow(() -> new ResourceNotFoundException("AgencyMaster", "id", agencyId));

        AdminUser creator = getLoggedInAdmin();

        // 1️⃣ Create login user for valuator
        AdminUser valuatorLogin = AdminUser.builder()
                .id(IdGenerator.generateId("VAL"))
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))   // default password
                .firstName(request.getName())
                .lastName(request.getLastName())                   // optional
                .phoneNumber(request.getPhone())
                .role(Role.AGENCY_VALUATOR)
                .agencyId(agencyId)
                .bank(agency.getBank())         // ⭐ SAME BANK AS AGENCY
                .createdDate(LocalDateTime.now())
                .build();

        valuatorLogin = adminUserRepo.save(valuatorLogin);

        // 2️⃣ Create valuator profile
        ValuatorMaster valuator = mapper.toEntity(request, agency, creator, valuatorLogin);

        valuator = valuatorRepo.save(valuator);

        log.info("Valuator created: {}, Login User ID: {}", valuator.getId(), valuatorLogin.getId());

        return mapper.toResponse(valuator);
    }


    // -------------------------------------------------------------------------
    // 2️⃣ UPDATE VALUATOR
    // -------------------------------------------------------------------------
    @Override
    @Transactional
    public ValuatorResponse updateValuator(Long valuatorId, ValuatorRequest request) {

        ValuatorMaster valuator = valuatorRepo.findById(valuatorId)
                .orElseThrow(() -> new ResourceNotFoundException("ValuatorMaster", "id", valuatorId));

        AdminUser updater = getLoggedInAdmin();

        mapper.updateEntity(request, valuator, updater);

        valuator = valuatorRepo.save(valuator);

        return mapper.toResponse(valuator);
    }

    // -------------------------------------------------------------------------
    // 3️⃣ GET VALUATOR BY ID
    // -------------------------------------------------------------------------
    @Override
    public ValuatorResponse getValuator(Long valuatorId) {

        ValuatorMaster valuator = valuatorRepo.findById(valuatorId)
                .orElseThrow(() -> new ResourceNotFoundException("ValuatorMaster", "id", valuatorId));

        return mapper.toResponse(valuator);
    }

    // -------------------------------------------------------------------------
    // 4️⃣ GET ALL VALUATORS OF AN AGENCY
    // -------------------------------------------------------------------------
    @Override
    public List<ValuatorResponse> getAllValuators() {

        AdminUser loggedIn = getLoggedInAdmin();
        if (loggedIn.getRole() != Role.AGENCY) {
            throw new RuntimeException("Only agency admins can create valuators");
        }
        Long agencyId = loggedIn.getAgencyId();
        log.info("agencyId: {}", agencyId);

        AgencyMaster agency = agencyRepo.findById(agencyId)
                .orElseThrow(() -> new ResourceNotFoundException("AgencyMaster", "id", agencyId));

        return valuatorRepo.findByAgency(agency)
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    // -------------------------------------------------------------------------
    // 5️⃣ DELETE VALUATOR
    // -------------------------------------------------------------------------
    @Override
    @Transactional
    public void deleteValuator(Long valuatorId) {

        ValuatorMaster valuator = valuatorRepo.findById(valuatorId)
                .orElseThrow(() -> new ResourceNotFoundException("ValuatorMaster", "id", valuatorId));

        // delete login account as well
        if (valuator.getLoginAccount() != null) {
            adminUserRepo.delete(valuator.getLoginAccount());
        }

        valuatorRepo.delete(valuator);

        log.info("Deleted valuator {} and login user {}", valuatorId, valuator.getLoginAccount().getId());
    }

    @Override
    public List<LoanApplicationResponse> getApplicationsForLoggedInValuator() {

        String adminId = getLoggedInAdminId();

        AdminUser loggedUser = adminUserRepository.findById(adminId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user"));

        // 🔐 ROLE CHECK
        if (loggedUser.getRole() != Role.AGENCY_VALUATOR) {
            throw new IllegalArgumentException("Only agency valuators can access this");
        }

        // 🔥 Fetch applications assigned to this valuator
        List<LoanApplication> apps =
                loanApplicationRepository.findApplicationsByValuatorId(adminId);

        return apps.stream()
                .map(app -> {

                    String status = applicationStageHistoryRepository
                            .findByApplication(app)
                            .map(h -> h.getStatus().name())
                            .orElse("NOT_STARTED");

                    return LoanApplicationResponse.builder()
                            .applicationId(app.getId())
                            .active(app.getActive())
                            .status(status)

                            // Client details
                            .clientId(app.getClient() != null ? app.getClient().getId() : null)
                            .clientName(app.getClient() != null
                                    ? app.getClient().getFirstName() + " " + app.getClient().getLastName()
                                    : null)

                            // Created by admin
                            .createdByAdminId(app.getCreatedBy() != null ? app.getCreatedBy().getId() : null)
                            .createdByName(app.getCreatedBy() != null
                                    ? app.getCreatedBy().getFirstName() + " " + app.getCreatedBy().getLastName()
                                    : null)

                            // Assigned valuator
                            .assignedToAdminId(app.getAssignedTo() != null ? app.getAssignedTo().getId() : null)
                            .assignedToName(app.getAssignedTo() != null
                                    ? app.getAssignedTo().getFirstName() + " " + app.getAssignedTo().getLastName()
                                    : null)

                            .associatedBank(app.getAssociatedBank())
                            .createdDate(app.getCreatedDate())
                            .updatedDate(app.getUpdatedDate())
                            .build();
                })
                .toList();
    }
}
