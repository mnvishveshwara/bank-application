package com.bankbroker.loanapp.service.impl;

import com.bankbroker.loanapp.dto.valuator.ValuatorRequest;
import com.bankbroker.loanapp.dto.valuator.ValuatorResponse;
import com.bankbroker.loanapp.entity.AdminUser;
import com.bankbroker.loanapp.entity.AgencyMaster;
import com.bankbroker.loanapp.entity.enums.Role;
import com.bankbroker.loanapp.entity.valuator.ValuatorMaster;
import com.bankbroker.loanapp.exception.ResourceNotFoundException;
import com.bankbroker.loanapp.mapper.ValuatorMapper;
import com.bankbroker.loanapp.repository.AdminUserRepository;
import com.bankbroker.loanapp.repository.AgencyMasterRepository;
import com.bankbroker.loanapp.repository.valuator.ValuatorMasterRepository;
import com.bankbroker.loanapp.service.ValuatorMasterService;
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
                .password(passwordEncoder.encode("valuator123"))   // default password
                .firstName(request.getName())
                .lastName("")                   // optional
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
}
