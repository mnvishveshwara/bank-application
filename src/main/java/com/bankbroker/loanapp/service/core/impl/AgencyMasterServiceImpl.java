package com.bankbroker.loanapp.service.core.impl;

import com.bankbroker.loanapp.dto.application.LoanApplicationResponse;
import com.bankbroker.loanapp.dto.master.AgencyMasterRequest;
import com.bankbroker.loanapp.dto.master.AgencyMasterResponse;
import com.bankbroker.loanapp.dto.stage.ApplicationDecisionRequest;
import com.bankbroker.loanapp.dto.stage.ApplicationHistoryRequest;
import com.bankbroker.loanapp.dto.stage.ApplicationHistoryResponse;
import com.bankbroker.loanapp.entity.core.AdminUser;
import com.bankbroker.loanapp.entity.core.AgencyMaster;
import com.bankbroker.loanapp.entity.core.LoanApplication;
import com.bankbroker.loanapp.entity.enums.ApplicationHistoryStatus;
import com.bankbroker.loanapp.entity.enums.Role;
import com.bankbroker.loanapp.exception.ResourceNotFoundException;
import com.bankbroker.loanapp.repository.core.AdminUserRepository;
import com.bankbroker.loanapp.repository.core.AgencyMasterRepository;
import com.bankbroker.loanapp.repository.core.ApplicationStageHistoryRepository;
import com.bankbroker.loanapp.repository.core.LoanApplicationRepository;
import com.bankbroker.loanapp.service.core.api.AgencyMasterService;
import com.bankbroker.loanapp.service.core.api.ApplicationStageService;
import com.bankbroker.loanapp.util.IdGenerator;
import com.bankbroker.loanapp.util.SecurityUtil;
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
    private final ApplicationStageHistoryRepository applicationStageHistoryRepository;
    private final ApplicationStageService applicationStageService;
    private final SecurityUtil securityUtil;

    @Override
    @Transactional
    public AgencyMasterResponse createAgency(AgencyMasterRequest req) {

        if (repository.existsByAgencyName(req.getAgencyName())) {
            throw new IllegalArgumentException("Agency name already exists.");
        }

        if (adminUserRepository.existsByEmail(req.getEmail())) {
            throw new IllegalArgumentException("Email already exists: " + req.getEmail());
        }

        AdminUser loggedInAdmin = securityUtil.getLoggedInAdmin();



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


        return toResponse(agency);
    }

    @Override
    public AgencyMasterResponse updateAgency(Long id, AgencyMasterRequest req) {
        AgencyMaster agency = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("AgencyMaster", "id", id));

        AdminUser loggedInAdmin = securityUtil.getLoggedInAdmin();


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

        AdminUser loggedUser = securityUtil.getLoggedInAdmin();


        if (loggedUser.getRole() != Role.AGENCY) {
            throw new IllegalArgumentException("Only agency users can access this");
        }

        Long agencyId = loggedUser.getAgencyId();

        // 🔥 Fetch applications assigned to this agency
        List<LoanApplication> apps = loanApplicationRepository.findApplicationsByAgencyId(agencyId);
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
                            .build();
                })
                .toList();

    }

    @Override
    @Transactional
    public ApplicationHistoryResponse updateApplicationStatus(
            String applicationId,
            ApplicationDecisionRequest request) {

        AdminUser loggedUser = securityUtil.getLoggedInAdmin();

        if (loggedUser.getRole() != Role.AGENCY) {
            throw new IllegalArgumentException("Only agency admins can update application status");
        }

        LoanApplication app = loanApplicationRepository.findById(applicationId)
                .orElseThrow(() -> new ResourceNotFoundException("LoanApplication", "id", applicationId));

        // 🔐 Agency ownership check
        if (!loggedUser.getAgencyId().equals(app.getAssignedTo().getAgencyId())) {
            throw new IllegalArgumentException("You cannot update another agency's application");
        }

        ApplicationHistoryStatus newStatus =
                ApplicationHistoryStatus.valueOf(request.getStatus().toUpperCase());

        // 🔒 VALID TRANSITIONS
        ApplicationHistoryStatus currentStatus =
                applicationStageHistoryRepository
                        .findByApplication(app)
                        .map(h -> h.getStatus())
                        .orElseThrow(() ->
                                new IllegalStateException("Site visit not completed yet"));

        if (currentStatus != ApplicationHistoryStatus.SITE_VISIT_COMPLETED
                && currentStatus != ApplicationHistoryStatus.REVIEWING_APPLICATION) {
            throw new IllegalStateException(
                    "Application must be in Site Visit Completed or REVIEWING state");
        }

        // 🚫 Prevent invalid final transitions
        if (currentStatus == ApplicationHistoryStatus.SITE_VISIT_COMPLETED
                && newStatus != ApplicationHistoryStatus.REVIEWING_APPLICATION) {
            throw new IllegalStateException("First move application to REVIEWING");
        }

        // ✅ UPSERT status
        return applicationStageService.addHistory(
                applicationId,
                new ApplicationHistoryRequest(
                        newStatus.name(),
                        request.getRemarks(),
                        loggedUser.getId()
                )
        );
    }

}
