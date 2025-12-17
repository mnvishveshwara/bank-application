package com.bankbroker.loanapp.service.core.impl;

import com.bankbroker.loanapp.dto.stage.*;
import com.bankbroker.loanapp.entity.core.*;
import com.bankbroker.loanapp.entity.enums.ApplicationHistoryStatus;
import com.bankbroker.loanapp.entity.enums.ApplicationStageType;
import com.bankbroker.loanapp.entity.enums.Role;
import com.bankbroker.loanapp.entity.stage.*;
import com.bankbroker.loanapp.exception.ResourceNotFoundException;
import com.bankbroker.loanapp.mapper.core.ApplicationAgencyAssignmentMapper;
import com.bankbroker.loanapp.mapper.core.ApplicationCustomerDetailsMapper;
import com.bankbroker.loanapp.mapper.core.ApplicationDocumentDetailsMapper;
import com.bankbroker.loanapp.mapper.core.ApplicationPropertyDetailsMapper;
import com.bankbroker.loanapp.repository.core.*;
import com.bankbroker.loanapp.repository.stage.*;
import com.bankbroker.loanapp.service.core.api.ApplicationStageService;
import com.bankbroker.loanapp.service.storage.FileStorageService;
import com.bankbroker.loanapp.util.IdGenerator;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ApplicationStageServiceImpl implements ApplicationStageService {

    private final LoanApplicationRepository loanApplicationRepository;
    private final AdminUserRepository adminUserRepository;
    private final ApplicationStageCurrentRepository applicationStageCurrentRepository;
    private final ApplicationStageHistoryRepository applicationStageHistoryRepository;
    private final PasswordEncoder passwordEncoder;
    private final FileStorageService fileStorageService;
    private final ApplicationDocumentDetailsMapper documentMapper;
    private final CustomerRepository customerRepo;
    private final ApplicationCustomerDetailsRepository customerDetailsRepo;
    private final ApplicationCustomerDetailsMapper customerMapper;
    private final ApplicationPropertyDetailsMapper propertyMapper;
    private final ApplicationPropertyDetailsRepository propertyDetailsRepo;
    private final ApplicationDocumentDetailsRepository documentRepo;
    private final ApplicationSummaryRepository summaryRepo;
    private final ApplicationAgencyAssignmentMapper agencyMapper;
    private final ApplicationAgencyAssignmentRepository agencyAssignmentRepo;
    private final AgencyMasterRepository agencyRepo;



    private LoanApplication getApplication(String id) {
        return loanApplicationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("LoanApplication", "id", id));
    }


    private AdminUser getAdmin(String id) {
        return adminUserRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("AdminUser", "id", id));
    }

    private String currentAdminId() {
        return (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    @Override
    @Transactional
    public ApplicationHistoryResponse addHistory(
            String applicationId,
            ApplicationHistoryRequest request) {

        LoanApplication app = getApplicationOrThrow(applicationId);
        AdminUser updatedBy = getAdminOrThrow(request.getUpdatedByAdminId());

        ApplicationHistoryStatus status = ApplicationHistoryStatus
                .valueOf(request.getStatus().toUpperCase());

        // 🔑 UPSERT LOGIC
        ApplicationStageHistory history =
                applicationStageHistoryRepository
                        .findByApplication(app)
                        .orElseGet(() -> ApplicationStageHistory.builder()
                                .application(app)
                                .createdDate(LocalDateTime.now())
                                .build()
                        );

        // UPDATE FIELDS
        history.setStatus(status);
        history.setRemarks(request.getRemarks());
        history.setUpdatedBy(updatedBy);
        history.setUpdatedDate(LocalDateTime.now());

        // SAVE (INSERT or UPDATE automatically)
        history = applicationStageHistoryRepository.save(history);

        return mapHistoryToResponse(history);
    }


    @Override
    public List<ApplicationHistoryResponse> getHistory(String applicationId) {
        LoanApplication app = getApplicationOrThrow(applicationId);

        return applicationStageHistoryRepository.findByApplication(app)
                .stream()
                .map(this::mapHistoryToResponse)
                .toList();
    }


    @Override
    public ApplicationCustomerDetailsResponse saveCustomerDetails(
            String applicationId, ApplicationCustomerDetailsRequest request) {

        LoanApplication app = getApplication(applicationId);
        AdminUser admin = getAdmin(currentAdminId());

        ApplicationCustomerDetails entity =
                customerDetailsRepo.findByApplication(app)
                        .map(existing -> {
                            customerMapper.updateEntityFromRequest(request, existing);
                            return existing;
                        })
                        .orElseGet(() -> {
                            ApplicationCustomerDetails newEntity = customerMapper.toEntity(request);
                            newEntity.setApplication(app);
                            return newEntity;
                        });

        customerDetailsRepo.save(entity);
        updateStage(app, ApplicationStageType.CUSTOMER_DETAILS, admin);

        return customerMapper.toResponse(entity);
    }

    @Override
    public ApplicationCustomerDetailsResponse getCustomerDetails(String applicationId) {
        LoanApplication app = getApplicationOrThrow(applicationId);

        ApplicationCustomerDetails entity = customerDetailsRepo
                .findByApplication(app)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "ApplicationCustomerDetails", "applicationId", applicationId));

        return mapCustomerDetailsToResponse(entity);
    }



    @Override
    public ApplicationPropertyDetailsResponse savePropertyDetails(
            String applicationId, ApplicationPropertyDetailsRequest request) {

        LoanApplication app = getApplication(applicationId);
        AdminUser admin = getAdmin(currentAdminId());

        ApplicationPropertyDetails entity =
                propertyDetailsRepo.findByApplication(app)
                        .map(existing -> {
                            propertyMapper.updateEntityFromRequest(request, existing);
                            return existing;
                        })
                        .orElseGet(() -> {
                            ApplicationPropertyDetails pd = propertyMapper.toEntity(request);
                            pd.setApplication(app);
                            return pd;
                        });

        propertyDetailsRepo.save(entity);
        updateStage(app, ApplicationStageType.PROPERTY_DETAILS, admin);

        return propertyMapper.toResponse(entity);
    }

    @Override
    public ApplicationPropertyDetailsResponse getPropertyDetails(String applicationId) {
        LoanApplication app = getApplicationOrThrow(applicationId);

        ApplicationPropertyDetails entity =
                propertyDetailsRepo.findByApplication(app)
                        .orElseThrow(() -> new ResourceNotFoundException(
                                "ApplicationPropertyDetails", "applicationId", applicationId));

        return propertyMapper.toResponse(entity);
    }



    @Override
    @Transactional
    public ApplicationDocumentDetailsResponse uploadDocuments(
            String applicationId, List<MultipartFile> files, List<String> types) {

        LoanApplication app = getApplication(applicationId);
        AdminUser admin = getAdmin(currentAdminId());

        if (files.size() != types.size())
            throw new IllegalArgumentException("Each file must match a document type");

        ApplicationDocumentDetails docDetails =
                documentRepo.findByApplication(app)
                        .orElseGet(() -> {
                            ApplicationDocumentDetails d = new ApplicationDocumentDetails();
                            d.setApplication(app);
                            d.setDocuments(new ArrayList<>());
                            return d;
                        });

        docDetails.getDocuments().clear();

        for (int i = 0; i < files.size(); i++) {
            MultipartFile file = files.get(i);

            ApplicationUploadedDocument doc = ApplicationUploadedDocument.builder()
                    .documentDetails(docDetails)
                    .fileName(file.getOriginalFilename())
                    .fileType(file.getContentType())
                    .fileSizeKB(file.getSize() / 1024)
                    .documentType(types.get(i))
//                    .fileUrl(fileStorageService.store(file))
                    .fileUrl(fileStorageService.store(file,applicationId,
                            "application-documents",
                            types.get(i) + fileStorageService.getExtension(file.getOriginalFilename())))
                    .build();

            docDetails.getDocuments().add(doc);
        }

        documentRepo.save(docDetails);
        updateStage(app, ApplicationStageType.DOCUMENTS_UPLOADED, admin);

        return documentMapper.toResponse(docDetails);
    }


    @Override
    public ApplicationDocumentDetailsResponse getDocumentDetails(String applicationId) {
        LoanApplication app = getApplicationOrThrow(applicationId);

        ApplicationDocumentDetails entity = documentRepo
                .findByApplication(app)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "ApplicationDocumentDetails", "applicationId", applicationId));
        return mapDocumentDetailsToResponse(entity);
    }


    @Override
    public ApplicationAgencyAssignmentResponse saveAgencyAssignment(
            String applicationId, ApplicationAgencyAssignmentRequest request) {

        LoanApplication app = getApplication(applicationId);
        AdminUser admin = getAdmin(currentAdminId());

        // Fetch agency
        AgencyMaster agency = agencyRepo.findById(request.getAgencyId())
                .orElseThrow(() -> new ResourceNotFoundException("AgencyMaster", "id", request.getAgencyId()));

        // Create / Update assignment entry
        ApplicationAgencyAssignment entity =
                agencyAssignmentRepo.findByApplication(app)
                        .orElseGet(() -> ApplicationAgencyAssignment.builder()
                                .application(app)
                                .createdBy(admin)
                                .build()
                        );

        entity.setAgency(agency);
        entity.setUpdatedBy(admin);
        entity.setRemarks(request.getRemarks());
        agencyAssignmentRepo.save(entity);

        AdminUser agencyUser = adminUserRepository
                .findByAgencyIdAndRole(agency.getId(), Role.AGENCY)
                .orElseThrow(() -> new IllegalArgumentException("No agency admin user found for this agency"));

        app.setAssignedTo(agencyUser);
        app.setUpdatedDate(LocalDateTime.now());
        loanApplicationRepository.save(app);

        log.info("Application {} is now assigned to agency admin {}",
                app.getId(), agencyUser.getId());

        // Update stage
        updateStage(app, ApplicationStageType.ASSIGN_AGENCY, admin);

        return agencyMapper.toResponse(entity);
    }



    @Override
    public ApplicationAgencyAssignmentResponse getAgencyAssignment(String applicationId) {
        LoanApplication app = getApplicationOrThrow(applicationId);

        ApplicationAgencyAssignment entity = agencyAssignmentRepo
                .findByApplication(app)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "ApplicationAgencyAssignment", "applicationId", applicationId));

        return agencyMapper.toResponse(entity);
    }

    @Override
    public ApplicationSummaryResponse saveSummary(String appId, ApplicationSummaryRequest request) {

        LoanApplication app = getApplication(appId);
        String adminId = getLoggedInAdminId();
        AdminUser reviewer = getAdminOrThrow(adminId);

        ApplicationSummary entity =
                summaryRepo.findByApplication(app)
                        .orElseGet(() -> ApplicationSummary.builder()
                                .application(app)
                                .build()
                        );

        entity.setSummaryText(request.getSummary());
        entity.setReviewedBy(reviewer);
        entity.setReviewedDate(LocalDateTime.now());

        summaryRepo.save(entity);
        updateStage(app, ApplicationStageType.APPLICATION_APPLIED, reviewer);

        return mapSummary(entity);
    }

    private ApplicationSummaryResponse mapSummary(ApplicationSummary e) {
        return ApplicationSummaryResponse.builder()
                .id(e.getId())
                .applicationId(e.getApplication().getId())
                .summaryText(e.getSummaryText())
                .reviewedByAdminId(e.getReviewedBy() != null ? e.getReviewedBy().getId() : null)
                .reviewedDate(e.getReviewedDate())
                .build();
    }

    @Override
    public ApplicationSummaryResponse getSummary(String applicationId) {
        LoanApplication app = getApplicationOrThrow(applicationId);

        ApplicationSummary entity = summaryRepo
                .findByApplication(app)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "ApplicationSummary", "applicationId", applicationId));

        return mapSummaryToResponse(entity);
    }

    private LoanApplication getApplicationOrThrow(String applicationId) {
        return loanApplicationRepository.findById(applicationId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "LoanApplication", "id", applicationId));
    }

    private AdminUser getAdminOrThrow(String adminId) {
        return adminUserRepository.findById(adminId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "AdminUser", "id", adminId));
    }


    private ApplicationHistoryResponse mapHistoryToResponse(ApplicationStageHistory history) {
        return ApplicationHistoryResponse.builder()
                .id(history.getId())
                .applicationId(history.getApplication().getId())
                .status(history.getStatus().name())
                .createdDate(history.getCreatedDate())
                .updatedDate(history.getUpdatedDate())
                .updatedByAdminId(history.getUpdatedBy() != null ? history.getUpdatedBy().getId() : null)
                .remarks(history.getRemarks())
                .build();
    }


    private ApplicationCustomerDetailsResponse mapCustomerDetailsToResponse(ApplicationCustomerDetails e) {
        return ApplicationCustomerDetailsResponse.builder()
                .id(e.getId())
                .applicationId(e.getApplication().getId())

                .spockName(e.getSpockName())
                .leadSource(e.getLeadSource())

                .firstName(e.getFirstName())
                .middleName(e.getMiddleName())
                .lastName(e.getLastName())

                .primaryContactNumber(e.getPrimaryContactNumber())
                .secondaryContactNumber(e.getSecondaryContactNumber())
                .email(e.getEmail())
                .propertyReferenceNo(e.getPropertyReferenceNo())
                .propertyType(e.getPropertyType())
                .propertySubType(e.getPropertySubType())

                .loanType(e.getLoanType())
                .remarks(e.getRemarks())
                .createdDate(e.getCreatedDate())
                .build();
    }



    private ApplicationDocumentDetailsResponse mapDocumentDetailsToResponse(ApplicationDocumentDetails e) {

        List<ApplicationUploadedDocumentResponse> docs = e.getDocuments().stream()
                .map(doc -> ApplicationUploadedDocumentResponse.builder()
                        .id(doc.getId())
                        .fileName(doc.getFileName())
                        .fileType(doc.getFileType())
                        .fileSizeKB(doc.getFileSizeKB())
                        .documentType(doc.getDocumentType())
                        .fileUrl(doc.getFileUrl())
                        .build()
                )
                .toList();

        return ApplicationDocumentDetailsResponse.builder()
                .id(e.getId())
                .applicationId(e.getApplication().getId())
                .documents(docs)
                .build();
    }



    private ApplicationSummaryResponse mapSummaryToResponse(ApplicationSummary e) {
        return ApplicationSummaryResponse.builder()
                .id(e.getId())
                .applicationId(e.getApplication().getId())
                .summaryText(e.getSummaryText())
                .reviewedByAdminId(e.getReviewedBy() != null ? e.getReviewedBy().getId() : null)
                .reviewedDate(e.getReviewedDate())
                .build();
    }

private void updateStage(LoanApplication app, ApplicationStageType stage, AdminUser admin) {

    // ----- Update Current Stage -----
    ApplicationStageCurrent current = applicationStageCurrentRepository
            .findByApplication(app)
            .orElse(new ApplicationStageCurrent());

    current.setApplication(app);
    current.setStage(stage);
    current.setUpdatedBy(admin);
    current.setRemark("Updated to: " + stage.name());
    current.setUpdatedDate(LocalDateTime.now());

    applicationStageCurrentRepository.save(current);

    // ----- Update History (always update same row) -----
    ApplicationStageHistory history = applicationStageHistoryRepository
            .findByApplication(app)
            .orElse(new ApplicationStageHistory());

    history.setApplication(app);
    history.setStatus(ApplicationHistoryStatus.IN_PROGRESS);
    history.setUpdatedBy(admin);
    history.setUpdatedDate(LocalDateTime.now());
    history.setRemarks("Stage updated to: " + stage.name());

    if (history.getCreatedDate() == null)
        history.setCreatedDate(LocalDateTime.now());

    applicationStageHistoryRepository.save(history);
}


    private String getLoggedInAdminId() {
        return (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }


    @Override
    @Transactional
    public ApplicationCustomerDetailsResponse createApplicationThenSaveCustomerDetails(
            ApplicationCustomerDetailsRequest request) {

        // 1️⃣ STEP 1: Create Application FIRST (client NULL temporarily)

        String applicationId = IdGenerator.generateId();

        String adminId = getLoggedInAdminId();
        AdminUser createdBy = getAdminOrThrow(adminId);

        LoanApplication app = LoanApplication.builder()
                .id(applicationId)
                .client(null)           // customer decided later
                .createdBy(createdBy)
                .createdDate(LocalDateTime.now())
                .updatedDate(LocalDateTime.now())
                .active(true)
                .associatedBank("HDFC")   // set after customer creation
                .build();

        app = loanApplicationRepository.save(app);


        // 2️⃣ STEP 2: Check if customer exists by email from request

        Customer customer = customerRepo.findByEmail(request.getEmail())
                .orElse(null);


        // 3️⃣ STEP 3: If customer NOT found → create new one

        if (customer == null) {

            String newCustomerId = IdGenerator.generateId();
            String rawPassword = generateCustomerPassword(
                    request.getFirstName(),
                    request.getPrimaryContactNumber()
            );

            String encodedPassword = passwordEncoder.encode(rawPassword);

            customer = Customer.builder()
                    .id(newCustomerId)
                    .email(request.getEmail())
                    .password(encodedPassword)
                    .firstName(request.getFirstName())
                    .lastName(request.getLastName())
                    .phoneNumber(request.getPrimaryContactNumber())
                    .role(Role.USER)
                    .bank(request.getLeadSource())     // or your bank source
                    .createdDate(LocalDateTime.now())
                    .build();

            customerRepo.save(customer);
        }


        // 4️⃣ STEP 4: Update Application with customer

        app.setClient(customer);
        app.setAssociatedBank(customer.getBank());
        app = loanApplicationRepository.save(app);


        // 5️⃣ STEP 5: Save customer details linked to this application

        ApplicationCustomerDetails details = customerMapper.toEntity(request);
        details.setApplication(app);

        details = customerDetailsRepo.save(details);


        // 6️⃣ STEP 6: Update stage history

        updateStage(app, ApplicationStageType.CUSTOMER_DETAILS, createdBy);


        // 7️⃣ DONE → return final response

        return customerMapper.toResponse(details);
    }

    private String generateCustomerPassword(String firstName, String phone) {
        String f = firstName != null ? firstName.trim() : "user";
        String p = phone != null ? phone.trim() : "00000";

        String lastFive = p.length() >= 5
                ? p.substring(p.length() - 5)
                : p;

        return f + lastFive;
    }

}
