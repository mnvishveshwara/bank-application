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
//            // 🔥 Fallback for roles not explicitly mapped
//            default -> IdGenerator.generateId("GEN");
//        };
//    }
//
//
//}

package com.bankbroker.loanapp.service.core.impl;

import com.bankbroker.loanapp.dto.admin.AdminRequest;
import com.bankbroker.loanapp.dto.admin.AdminResponse;
import com.bankbroker.loanapp.entity.core.AdminUser;
import com.bankbroker.loanapp.entity.enums.Role;
import com.bankbroker.loanapp.exception.ResourceNotFoundException;
import com.bankbroker.loanapp.repository.core.AdminUserRepository;
import com.bankbroker.loanapp.service.core.api.AdminUserService;
import com.bankbroker.loanapp.util.IdGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminUserServiceImpl implements AdminUserService {

    private final AdminUserRepository adminUserRepository;
    private final PasswordEncoder passwordEncoder;

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

        AdminUser admin = AdminUser.builder()
                .id(generatedId)
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .phoneNumber(request.getPhoneNumber())
                .role(role)
                .createdDate(LocalDateTime.now())
                .bank(request.getBank())
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
        admin.setBank(request.getBank());

        if (request.getRole() != null) {
            try {
                admin.setRole(Role.valueOf(request.getRole().toUpperCase()));
            } catch (IllegalArgumentException ex) {
                throw new IllegalArgumentException("Invalid role: " + request.getRole());
            }
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

    private AdminResponse mapToResponse(AdminUser admin) {
        return AdminResponse.builder()
                .id(admin.getId())
                .email(admin.getEmail())
                .firstName(admin.getFirstName())
                .lastName(admin.getLastName())
                .phoneNumber(admin.getPhoneNumber())
                .bank(admin.getBank())
                .role(admin.getRole().name())
                .createdDate(admin.getCreatedDate())
                .build();
    }

    private String generateRoleBasedId(Role role) {
        return switch (role) {
            case ADMIN -> IdGenerator.generateId("ADM");
            case AGENCY -> IdGenerator.generateId("AGN");
            case AGENT -> IdGenerator.generateId("AGT");
            case AGENCY_VALUATOR -> IdGenerator.generateId("VAL");
            case EMPLOYEE -> IdGenerator.generateId("EMP");
            case USER -> IdGenerator.generateId("USR");
            default -> IdGenerator.generateId("GEN"); // fallback for any other role
        };
    }
}
