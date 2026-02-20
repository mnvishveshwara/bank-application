//package com.bankbroker.loanapp.service.core.impl;
//
//import com.bankbroker.loanapp.dto.admin.AdminRequest;
//import com.bankbroker.loanapp.dto.admin.AdminResponse;
//import com.bankbroker.loanapp.entity.core.AdminUser;
//import com.bankbroker.loanapp.entity.enums.Role;
//import com.bankbroker.loanapp.exception.ResourceNotFoundException;
//import com.bankbroker.loanapp.repository.core.AdminUserRepository;
//import com.bankbroker.loanapp.service.core.api.AdminUserService;
//import com.bankbroker.loanapp.util.IdGenerator;
//import lombok.RequiredArgsConstructor;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//
//import java.time.LocalDateTime;
//import java.util.List;
//
//@Service
//@RequiredArgsConstructor
//public class AdminUserServiceImpl implements AdminUserService {
//
//    private final AdminUserRepository adminUserRepository;
//    private final PasswordEncoder passwordEncoder;
//
//    @Override
//    public AdminResponse createAdmin(AdminRequest request) {
//        String generatedId = IdGenerator.generateId();
//        Role role = Role.valueOf(request.getRole().toUpperCase());
//
//        AdminUser admin = AdminUser.builder()
//                .id(generatedId)
//                .email(request.getEmail())
//                .password(passwordEncoder.encode(request.getPassword()))
//                .firstName(request.getFirstName())
//                .lastName(request.getLastName())
//                .phoneNumber(request.getPhoneNumber())
//                .role(role)
//
//                .createdDate(LocalDateTime.now())
//                .bank(request.getBank())
//                .build();
//
//        admin = adminUserRepository.save(admin);
//        return mapToResponse(admin);
//    }
//
//    @Override
//    public AdminResponse getAdminById(String id) {
//        AdminUser admin = adminUserRepository.findById(id)
//                .orElseThrow(() -> new ResourceNotFoundException("AdminUser", "id", id));
//        return mapToResponse(admin);
//    }
//
//    @Override
//    public List<AdminResponse> getAllAdmins() {
//        return adminUserRepository.findAll()
//                .stream()
//                .map(this::mapToResponse)
//                .toList();
//    }
//
//    @Override
//    public AdminResponse updateAdmin(String id, AdminRequest request) {
//        AdminUser admin = adminUserRepository.findById(id)
//                .orElseThrow(() -> new ResourceNotFoundException("AdminUser", "id", id));
//
//        admin.setFirstName(request.getFirstName());
//        admin.setLastName(request.getLastName());
//        admin.setPhoneNumber(request.getPhoneNumber());
//        admin.setBank(request.getBank());
//        admin.setRole(Role.valueOf(request.getRole().toUpperCase()));
//
//        admin = adminUserRepository.save(admin);
//        return mapToResponse(admin);
//    }
//
//    @Override
//    public void deleteAdmin(String id) {
//        if (!adminUserRepository.existsById(id)) {
//            throw new ResourceNotFoundException("AdminUser", "id", id);
//        }
//        adminUserRepository.deleteById(id);
//    }
//
//    private AdminResponse mapToResponse(AdminUser admin) {
//        return AdminResponse.builder()
//                .id(admin.getId())
//                .email(admin.getEmail())
//                .firstName(admin.getFirstName())
//                .lastName(admin.getLastName())
//                .phoneNumber(admin.getPhoneNumber())
//                .bank(admin.getBank())
//                .role(admin.getRole().name())
//                .createdDate(admin.getCreatedDate())
//                .build();
//    }
//
//    private String generateRoleBasedId(Role role) {
//        return switch (role) {
//            case ADMIN -> IdGenerator.generateId("ADM");
//            case AGENCY -> IdGenerator.generateId("AGN");
//            case AGENT -> IdGenerator.generateId("AGT");
//            case VALUATOR -> IdGenerator.generateId("VAL");
//            case EMPLOYEE -> IdGenerator.generateId("EMP");
//            case USER -> IdGenerator.generateId("USR");
//
//            //   Fallback for roles not explicitly mapped
//            default -> IdGenerator.generateId("GEN");
//        };
//    }
//
//
//}

package com.bankbroker.loanapp.service.core.impl;

import com.bankbroker.loanapp.dto.admin.AdminRequest;
import com.bankbroker.loanapp.dto.admin.AdminResponse;
import com.bankbroker.loanapp.dto.admin.CreateBankValuatorRequest;
import com.bankbroker.loanapp.dto.application.LoanApplicationResponse;
import com.bankbroker.loanapp.entity.core.AdminUser;
import com.bankbroker.loanapp.entity.core.BankMaster;
import com.bankbroker.loanapp.entity.core.LoanApplication;
import com.bankbroker.loanapp.entity.enums.ApplicationStageType;
import com.bankbroker.loanapp.entity.enums.Role;
import com.bankbroker.loanapp.exception.ResourceNotFoundException;
import com.bankbroker.loanapp.repository.core.AdminUserRepository;
import com.bankbroker.loanapp.repository.core.ApplicationStageCurrentRepository;
import com.bankbroker.loanapp.repository.core.BankMasterRepository;
import com.bankbroker.loanapp.service.core.api.AdminUserService;
import com.bankbroker.loanapp.util.IdGenerator;
import com.bankbroker.loanapp.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.bankbroker.loanapp.util.IdGenerator.generateId;

@Service
@RequiredArgsConstructor
public class AdminUserServiceImpl implements AdminUserService {

    private final AdminUserRepository adminUserRepository;
    private final PasswordEncoder passwordEncoder;
    private final ApplicationStageCurrentRepository applicationStageCurrentRepository;
    private final BankMasterRepository bankRepository;
    private final SecurityUtil securityUtil;

    @Override
    public AdminResponse createAdmin(AdminRequest request) {
        // duplicate email check
        if (adminUserRepository.existsByEmail(request.getEmail())) {
            throw new IllegalArgumentException("Email already exists: " + request.getEmail());
        }

        // parse role safely
        Role role;
        try {
            role = Role.valueOf(request.getRole().toUpperCase());
        } catch (IllegalArgumentException ex) {
            throw new IllegalArgumentException("Invalid role: " + request.getRole());
        }

        // generate id using role-based prefix
        String generatedId = generateRoleBasedId(role);

        Set<BankMaster> banks = bankRepository.findAllById(request.getBankIds())
                .stream()
                .collect(Collectors.toSet());

        AdminUser admin = AdminUser.builder()
                .id(generatedId)
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .phoneNumber(request.getPhoneNumber())
                .role(role)
                .createdDate(LocalDateTime.now())
                .banks(banks)
                .build();

        admin = adminUserRepository.save(admin);
        return mapToResponse(admin);
    }

    @Override
    public AdminResponse getAdminById(String id) {
        AdminUser admin = adminUserRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("AdminUser", "id", id));
        return mapToResponse(admin);
    }

    @Override
    public List<AdminResponse> getAllAdmins() {
        return adminUserRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public AdminResponse updateAdmin(String id, AdminRequest request) {
        AdminUser admin = adminUserRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("AdminUser", "id", id));

        admin.setFirstName(request.getFirstName());
        admin.setLastName(request.getLastName());
        admin.setPhoneNumber(request.getPhoneNumber());


        if (request.getRole() != null) {
            try {
                admin.setRole(Role.valueOf(request.getRole().toUpperCase()));
            } catch (IllegalArgumentException ex) {
                throw new IllegalArgumentException("Invalid role: " + request.getRole());
            }
        }

        if (request.getBankIds() != null && !request.getBankIds().isEmpty()) {

            Set<BankMaster> banks = bankRepository
                    .findAllById(request.getBankIds())
                    .stream()
                    .collect(Collectors.toSet());

            admin.setBanks(banks);
        }

        admin = adminUserRepository.save(admin);
        return mapToResponse(admin);
    }

    @Override
    public void deleteAdmin(String id) {
        if (!adminUserRepository.existsById(id)) {
            throw new ResourceNotFoundException("AdminUser", "id", id);
        }
        adminUserRepository.deleteById(id);
    }

    @Override
    public List<LoanApplicationResponse> getIncompleteApplication() {

        return applicationStageCurrentRepository
                .findByStageNot(ApplicationStageType.APPLICATION_APPLIED)
                .stream()
                .map(stage -> {

                    LoanApplication app = stage.getApplication();

                    return LoanApplicationResponse.builder()
                            .applicationId(app.getId())
                            .active(app.getActive())
                            .clientId(app.getClient() != null ? app.getClient().getId() : null)
                            .clientName(app.getClient() != null
                                    ? app.getClient().getFirstName() + " " + app.getClient().getLastName()
                                    : null)
                            .createdByAdminId(app.getCreatedBy() != null ? app.getCreatedBy().getId() : null)
                            .createdByName(app.getCreatedBy() != null
                                    ? app.getCreatedBy().getFirstName() + " " + app.getCreatedBy().getLastName()
                                    : null)
                            .assignedToAdminId(app.getAssignedTo() != null ? app.getAssignedTo().getId() : null)
                            .assignedToName(app.getAssignedTo() != null
                                    ? app.getAssignedTo().getFirstName() + " " + app.getAssignedTo().getLastName()
                                    : null)
                            .bankId(app.getBankId())
                            .bankName(app.getBank() != null ? app.getBank().getBankName() : null)
                            .createdDate(app.getCreatedDate())
                            .updatedDate(app.getUpdatedDate())
                            .status(stage.getStage().name())
                            .build();
                })
                .toList();
    }

    @Override
    public List<LoanApplicationResponse> getCompleteApplication() {

        return applicationStageCurrentRepository
                .findByStage(ApplicationStageType.APPLICATION_APPLIED)
                .stream()
                .map(stage -> {

                    LoanApplication app = stage.getApplication();

                    return LoanApplicationResponse.builder()
                            .applicationId(app.getId())
                            .active(app.getActive())
                            .clientId(app.getClient() != null ? app.getClient().getId() : null)
                            .clientName(app.getClient() != null
                                    ? app.getClient().getFirstName() + " " + app.getClient().getLastName()
                                    : null)
                            .createdByAdminId(app.getCreatedBy() != null ? app.getCreatedBy().getId() : null)
                            .createdByName(app.getCreatedBy() != null
                                    ? app.getCreatedBy().getFirstName() + " " + app.getCreatedBy().getLastName()
                                    : null)
                            .assignedToAdminId(app.getAssignedTo() != null ? app.getAssignedTo().getId() : null)
                            .assignedToName(app.getAssignedTo() != null
                                    ? app.getAssignedTo().getFirstName() + " " + app.getAssignedTo().getLastName()
                                    : null)
                            .bankId(app.getBankId())
                            .bankName(app.getBank() != null ? app.getBank().getBankName() : null)
                            .createdDate(app.getCreatedDate())
                            .updatedDate(app.getUpdatedDate())
                            .status(stage.getStage().name())
                            .build();
                })
                .toList();
    }

    @Override
    @Transactional
    public void createBankValuator(CreateBankValuatorRequest request) {
        // 1. Validate if user exists
        if (adminUserRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already exists");
        }

        // 2. Fetch the Bank entity
        BankMaster bank = bankRepository.findById(request.getBankId())
                .orElseThrow(() -> new RuntimeException("Bank not found"));

        // 3. Build the AdminUser
        AdminUser valuator = AdminUser.builder()
                .id(generateId("BVAL")) // e.g., "VAL" + random numeric string
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .phoneNumber(request.getPhoneNumber())
                .role(Role.BANK_VALUATOR) // Ensure BANK_VALUATOR is in your Role enum
                .createdDate(LocalDateTime.now())
                .banks(new HashSet<>()) // Initialize the set
                .build();

        // 4. Establish the relationship (This populates admin_bank_mapping)
        valuator.getBanks().add(bank);

        // 5. Save the user
        adminUserRepository.save(valuator);
    }



    @Override
    public List<AdminResponse> getInternalValuators() {
        AdminUser loggedInAdmin = securityUtil.getLoggedInAdmin();

        // A Manager is usually mapped to one bank. Get the first bank's ID.
        Long bankId = loggedInAdmin.getBanks().stream()
                .findFirst()
                .map(BankMaster::getId)
                .orElseThrow(() -> new RuntimeException("Logged in user is not associated with any bank"));

        List<AdminUser> valuators = adminUserRepository.findByBankIdAndRole(bankId, Role.BANK_VALUATOR);

        // Map the entities to DTOs for the frontend
        return valuators.stream()
                .map(user -> AdminResponse.builder()
                        .id(user.getId())
                        .firstName(user.getFirstName())
                        .lastName(user.getLastName())
                        .email(user.getEmail())
                        .phoneNumber(user.getPhoneNumber())
                        .build())
                .collect(Collectors.toList());
    }

    private AdminResponse mapToResponse(AdminUser admin) {
        return AdminResponse.builder()
                .id(admin.getId())
                .email(admin.getEmail())
                .firstName(admin.getFirstName())
                .lastName(admin.getLastName())
                .phoneNumber(admin.getPhoneNumber())
                .bankIds(
                        admin.getBanks().stream()
                                .map(BankMaster::getId)
                                .toList()
                )
                .bankNames(
                        admin.getBanks().stream()
                                .map(BankMaster::getBankName)
                                .toList()
                )

                .role(admin.getRole().name())
                .createdDate(admin.getCreatedDate())
                .build();
    }

    private String generateRoleBasedId(Role role) {
        return switch (role) {
            case ADMIN -> generateId("ADM");
            case AGENCY -> generateId("AGN");
            case AGENT -> generateId("AGT");
            case AGENCY_VALUATOR -> generateId("VAL");
            case EMPLOYEE -> generateId("EMP");
            case USER -> generateId("USR");
            default -> generateId("GEN"); // fallback for any other role
        };
    }
}
