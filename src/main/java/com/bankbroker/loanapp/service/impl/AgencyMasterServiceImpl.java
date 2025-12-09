package com.bankbroker.loanapp.service.impl;

import com.bankbroker.loanapp.dto.application.LoanApplicationResponse;
import com.bankbroker.loanapp.dto.master.AgencyMasterRequest;
import com.bankbroker.loanapp.dto.master.AgencyMasterResponse;
import com.bankbroker.loanapp.entity.AdminUser;
import com.bankbroker.loanapp.entity.AgencyMaster;
import com.bankbroker.loanapp.entity.LoanApplication;
import com.bankbroker.loanapp.entity.enums.Role;
import com.bankbroker.loanapp.exception.ResourceNotFoundException;
import com.bankbroker.loanapp.repository.AdminUserRepository;
import com.bankbroker.loanapp.repository.AgencyMasterRepository;
import com.bankbroker.loanapp.repository.LoanApplicationRepository;
import com.bankbroker.loanapp.service.AgencyMasterService;
import com.bankbroker.loanapp.util.IdGenerator;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class AgencyMasterServiceImpl implements AgencyMasterService {

    private final AgencyMasterRepository repository;
    private final AdminUserRepository adminUserRepository;
    private final LoanApplicationRepository loanApplicationRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public AgencyMasterResponse createAgency(AgencyMasterRequest req) {

        if (repository.existsByAgencyName(req.getAgencyName())) {
            throw new IllegalArgumentException("Agency name already exists.");
        }

        if (adminUserRepository.existsByEmail(req.getEmail())) {
            throw new IllegalArgumentException("Email already exists: " + req.getEmail());
        }

        String adminId = getLoggedInAdminId();

        AdminUser loggedInAdmin = adminUserRepository.findById(adminId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid admin ID: " + adminId));

        log.info("Creating agency. Requested by Admin: {}", adminId);

        AgencyMaster agency = AgencyMaster.builder()
                .agencyName(req.getAgencyName())
                .bank(req.getBank())
                .contactName(req.getContactName())
                .contactNumber(req.getContactNumber())
                .streetLine1(req.getStreetLine1())
                .streetLine2(req.getStreetLine2())
                .pinCode(req.getPinCode())
                .city(req.getCity())
                .state(req.getState())
                .latitude(req.getLatitude())
                .longitude(req.getLongitude())
                .mapURL(req.getMapURL())

                .createdBy(loggedInAdmin)
                .updatedBy(loggedInAdmin)

                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        agency = repository.save(agency);

        AdminUser agencyAdmin = AdminUser.builder()
                .id(IdGenerator.generateId("AGN"))
                .email(req.getEmail())
                .password(passwordEncoder.encode(req.getPassword()))
                .firstName(req.getContactName())
                .lastName("")
                .phoneNumber(req.getContactNumber())
                .role(Role.AGENCY)
                .agencyId(agency.getId())
                .bank(req.getBank())
                .createdDate(LocalDateTime.now())
                .build();

        adminUserRepository.save(agencyAdmin);

        log.info("Agency created with ID: {} and Login ID: {}", agency.getId(), agencyAdmin.getId());

        return toResponse(agency);
    }

    @Override
    public AgencyMasterResponse updateAgency(Long id, AgencyMasterRequest req) {
        AgencyMaster agency = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("AgencyMaster", "id", id));

        String adminId = getLoggedInAdminId();

        AdminUser loggedInAdmin = adminUserRepository.findById(adminId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid admin ID: " + adminId));

        agency.setAgencyName(req.getAgencyName());
        agency.setContactName(req.getContactName());
        agency.setContactNumber(req.getContactNumber());
        agency.setStreetLine1(req.getStreetLine1());
        agency.setStreetLine2(req.getStreetLine2());
        agency.setPinCode(req.getPinCode());
        agency.setCity(req.getCity());
        agency.setState(req.getState());
        agency.setLatitude(req.getLatitude());
        agency.setLongitude(req.getLongitude());
        agency.setMapURL(req.getMapURL());

        agency.setUpdatedBy(loggedInAdmin);

        agency = repository.save(agency);
        return toResponse(agency);
    }

    @Override
    public AgencyMasterResponse getAgency(Long id) {
        AgencyMaster agency = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("AgencyMaster", "id", id));
        return toResponse(agency);
    }

    @Override
    public List<AgencyMasterResponse> getAllAgencies() {
        return repository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    @Override
    public void deleteAgency(Long id) {
        if (!repository.existsById(id))
            throw new ResourceNotFoundException("AgencyMaster", "id", id);
        repository.deleteById(id);
    }

    private AgencyMasterResponse toResponse(AgencyMaster a) {
        return AgencyMasterResponse.builder()
                .id(a.getId())
                .agencyName(a.getAgencyName())
                .contactName(a.getContactName())
                .contactNumber(a.getContactNumber())
                .streetLine1(a.getStreetLine1())
                .streetLine2(a.getStreetLine2())
                .pinCode(a.getPinCode())
                .city(a.getCity())
                .state(a.getState())
                .latitude(a.getLatitude())
                .longitude(a.getLongitude())
                .mapURL(a.getMapURL())
                .createdAt(a.getCreatedAt())
                .updatedAt(a.getUpdatedAt())
                .createdBy(a.getCreatedBy().getId())
                .updatedBy(a.getUpdatedBy().getId())
                .build();
    }



    // ---------------------------------------------------------------------
    // 🚀 AGENCY DASHBOARD - FETCH ASSIGNED APPLICATIONS
    // ---------------------------------------------------------------------

    @Override
    public List<LoanApplicationResponse> getApplicationsForLoggedInAgency() {

        String adminId = currentAdminId();

        AdminUser loggedUser = adminUserRepository.findById(adminId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user"));

        if (loggedUser.getRole() != Role.AGENCY) {
            throw new IllegalArgumentException("Only agency users can access this");
        }

        Long agencyId = loggedUser.getAgencyId();

        // 🔥 Fetch applications assigned to this agency
        List<LoanApplication> apps = loanApplicationRepository.findApplicationsByAgencyId(agencyId);

        return apps.stream()
                .map(app -> LoanApplicationResponse.builder()
                        .applicationId(app.getId())
                        .active(app.getActive())

                        // Client details
                        .clientId(app.getClient() != null ? app.getClient().getId() : null)
                        .clientName(app.getClient() != null ? app.getClient().getFirstName() : null)

                        // Created by admin
                        .createdByAdminId(app.getCreatedBy() != null ? app.getCreatedBy().getId() : null)
                        .createdByName(app.getCreatedBy() != null
                                ? app.getCreatedBy().getFirstName() + " " + app.getCreatedBy().getLastName()
                                : null)

                        // Assigned to admin
                        .assignedToAdminId(app.getAssignedTo() != null ? app.getAssignedTo().getId() : null)
                        .assignedToName(app.getAssignedTo() != null
                                ? app.getAssignedTo().getFirstName() + " " + app.getAssignedTo().getLastName()
                                : null)

                        .associatedBank(app.getAssociatedBank())
                        .createdDate(app.getCreatedDate())
                        .updatedDate(app.getUpdatedDate())
                        .build()
                ).toList();
    }

    private String getLoggedInAdminId() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    private String currentAdminId() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }
}
