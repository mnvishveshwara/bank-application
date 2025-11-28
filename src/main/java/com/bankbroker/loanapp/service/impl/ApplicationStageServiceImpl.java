package com.bankbroker.loanapp.service.impl;

import com.bankbroker.loanapp.dto.stage.*;
import com.bankbroker.loanapp.entity.*;
import com.bankbroker.loanapp.entity.enums.ApplicationHistoryStatus;
import com.bankbroker.loanapp.entity.enums.ApplicationStageType;
import com.bankbroker.loanapp.entity.enums.Role;
import com.bankbroker.loanapp.entity.stage.*;
import com.bankbroker.loanapp.exception.ResourceNotFoundException;
import com.bankbroker.loanapp.mapper.ApplicationAgencyAssignmentMapper;
import com.bankbroker.loanapp.mapper.ApplicationCustomerDetailsMapper;
import com.bankbroker.loanapp.mapper.ApplicationDocumentDetailsMapper;
import com.bankbroker.loanapp.mapper.ApplicationPropertyDetailsMapper;
import com.bankbroker.loanapp.repository.*;
import com.bankbroker.loanapp.repository.stage.*;
import com.bankbroker.loanapp.service.ApplicationStageService;
import com.bankbroker.loanapp.service.storage.FileStorageService;
import com.bankbroker.loanapp.util.IdGenerator;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
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

    private final ApplicationCustomerDetailsRepository applicationCustomerDetailsRepository;
    private final ApplicationPropertyDetailsRepository applicationPropertyDetailsRepository;
    private final ApplicationDocumentDetailsRepository applicationDocumentDetailsRepository;
    private final ApplicationAgencyAssignmentRepository applicationAgencyAssignmentRepository;
    private final ApplicationSummaryRepository applicationSummaryRepository;
    private final CustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder;
    private final ApplicationPropertyDetailsMapper propertyDetailsMapper;
    private final ApplicationCustomerDetailsMapper customerDetailsMapper;
    private final FileStorageService fileStorageService;
    private final ApplicationDocumentDetailsMapper documentMapper;
    private final AgencyMasterRepository agencyMasterRepository;
    private final ApplicationAgencyAssignmentMapper agencyAssignmentMapper;



    @Override
    public ApplicationStageCurrentResponse saveCurrentStage(String applicationId,
                                                            ApplicationStageCurrentRequest request) {
        LoanApplication app = getApplicationOrThrow(applicationId);
        AdminUser updatedBy = getAdminOrThrow(request.getUpdatedByAdminId());

        ApplicationStageType stageType = ApplicationStageType.valueOf(request.getStage().toUpperCase());

        ApplicationStageCurrent stage = ApplicationStageCurrent.builder()
                .application(app)
                .stage(stageType)
                .updatedDate(LocalDateTime.now())
                .remark(request.getRemark())
                .updatedBy(updatedBy)
                .build();

        stage = applicationStageCurrentRepository.save(stage);

        return mapCurrentStageToResponse(stage);
    }

    @Override
    public List<ApplicationStageCurrentResponse> getCurrentStages(String applicationId) {
        LoanApplication app = getApplicationOrThrow(applicationId);

        return applicationStageCurrentRepository.findByApplication(app)
                .stream()
                .map(this::mapCurrentStageToResponse)
                .toList();
    }

    @Override
    public ApplicationHistoryResponse addHistory(String applicationId,
                                                 ApplicationHistoryRequest request) {
        LoanApplication app = getApplicationOrThrow(applicationId);
        AdminUser updatedBy = getAdminOrThrow(request.getUpdatedByAdminId());

        ApplicationHistoryStatus status = ApplicationHistoryStatus
                .valueOf(request.getStatus().toUpperCase());

        ApplicationStageHistory history = ApplicationStageHistory.builder()
                .application(app)
                .status(status)
                .createdDate(LocalDateTime.now())
                .updatedDate(LocalDateTime.now())
                .updatedBy(updatedBy)
                .remarks(request.getRemarks())
                .build();

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
            String applicationId,
            ApplicationCustomerDetailsRequest request) {

        LoanApplication app = getApplicationOrThrow(applicationId);

        String adminId = getLoggedInAdminId();
        AdminUser admin = getAdminOrThrow(adminId);

        ApplicationCustomerDetails entity =
                applicationCustomerDetailsRepository.findByApplication(app)
                        .orElse(null);

        if (entity == null) {

            entity = customerDetailsMapper.toEntity(request);
            entity.setApplication(app);
        } else {
            // Update existing entity
            customerDetailsMapper.updateEntityFromRequest(request, entity);
        }

        entity = applicationCustomerDetailsRepository.save(entity);

        updateStage(app, ApplicationStageType.CUSTOMER_DETAILS, admin);

        return customerDetailsMapper.toResponse(entity);
    }

    @Override
    public ApplicationCustomerDetailsResponse getCustomerDetails(String applicationId) {
        LoanApplication app = getApplicationOrThrow(applicationId);

        ApplicationCustomerDetails entity = applicationCustomerDetailsRepository
                .findByApplication(app)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "ApplicationCustomerDetails", "applicationId", applicationId));

        return mapCustomerDetailsToResponse(entity);
    }



    @Override
    public ApplicationPropertyDetailsResponse savePropertyDetails(
            String applicationId,
            ApplicationPropertyDetailsRequest request) {

        LoanApplication app = getApplicationOrThrow(applicationId);

        String adminId = getLoggedInAdminId();
        AdminUser admin = getAdminOrThrow(adminId);

        ApplicationPropertyDetails entity =
                applicationPropertyDetailsRepository
                        .findByApplication(app)
                        .orElse(ApplicationPropertyDetails.builder()
                                .application(app)
                                .build());

        if (entity.getId() == null) {
            // create new
            ApplicationPropertyDetails mapped = propertyDetailsMapper.toEntity(request);
            mapped.setApplication(app);
            entity = mapped;
        } else {
            // update existing
            propertyDetailsMapper.updateEntityFromRequest(request, entity);
        }

        entity = applicationPropertyDetailsRepository.save(entity);

        // 🔥 NEW: update property stage
        updateStage(app, ApplicationStageType.PROPERTY_DETAILS, admin);

        return propertyDetailsMapper.toResponse(entity);
    }


    @Override
    public ApplicationPropertyDetailsResponse getPropertyDetails(String applicationId) {
        LoanApplication app = getApplicationOrThrow(applicationId);

        ApplicationPropertyDetails entity =
                applicationPropertyDetailsRepository.findByApplication(app)
                        .orElseThrow(() -> new ResourceNotFoundException(
                                "ApplicationPropertyDetails", "applicationId", applicationId));

        return propertyDetailsMapper.toResponse(entity);
    }


    @Override
    @Transactional
    public ApplicationDocumentDetailsResponse uploadDocuments(
            String applicationId,
            List<MultipartFile> files,
            List<String> documentTypes) {

        LoanApplication app = getApplicationOrThrow(applicationId);

        if (files.size() != documentTypes.size())
            throw new IllegalArgumentException("Each file must have a document type");

        if (files.size() > 5)
            throw new IllegalArgumentException("Maximum 5 files allowed");

        ApplicationDocumentDetails details =
                applicationDocumentDetailsRepository.findByApplication(app)
                        .orElse(ApplicationDocumentDetails.builder()
                                .application(app)
                                .documents(new ArrayList<>())
                                .build());

        // Clear old documents
        details.getDocuments().clear();

        for (int i = 0; i < files.size(); i++) {

            MultipartFile file = files.get(i);
            String type = documentTypes.get(i);

            String url = fileStorageService.store(file);

            ApplicationUploadedDocument doc = ApplicationUploadedDocument.builder()
                    .documentDetails(details)
                    .fileName(file.getOriginalFilename())
                    .fileType(file.getContentType())
                    .fileSizeKB(file.getSize() / 1024)
                    .documentType(type)
                    .fileUrl(url)
                    .build();

            details.getDocuments().add(doc);
        }

        details = applicationDocumentDetailsRepository.save(details);

        // Update stage
        String adminId = getLoggedInAdminId();
        AdminUser admin = getAdminOrThrow(adminId);
        updateStage(app, ApplicationStageType.DOCUMENTS_UPLOADED, admin);

        return documentMapper.toResponse(details);
    }


    @Override
    public ApplicationDocumentDetailsResponse getDocumentDetails(String applicationId) {
        LoanApplication app = getApplicationOrThrow(applicationId);

        ApplicationDocumentDetails entity = applicationDocumentDetailsRepository
                .findByApplication(app)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "ApplicationDocumentDetails", "applicationId", applicationId));
        return mapDocumentDetailsToResponse(entity);
    }


    @Override
    @Transactional
    public ApplicationAgencyAssignmentResponse saveAgencyAssignment(
            String applicationId,
            ApplicationAgencyAssignmentRequest request) {

        LoanApplication app = getApplicationOrThrow(applicationId);

        String adminId = getLoggedInAdminId();
        log.info("admin details: {}",adminId);
        AdminUser admin = getAdminOrThrow(adminId);

        AgencyMaster agency = agencyMasterRepository.findById(request.getAgencyId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "AgencyMaster", "id", request.getAgencyId()
                ));

        ApplicationAgencyAssignment entity =
                applicationAgencyAssignmentRepository.findByApplication(app)
                        .orElse(ApplicationAgencyAssignment.builder()
                                .application(app)
                                .createdBy(admin)
                                .build());

        entity.setAgency(agency);
        entity.setRemarks(request.getRemarks());
        entity.setUpdatedBy(admin);

        entity = applicationAgencyAssignmentRepository.save(entity);
        updateStage(app, ApplicationStageType.ASSIGN_AGENCY, admin);
        return agencyAssignmentMapper.toResponse(entity);
    }



    @Override
    public ApplicationAgencyAssignmentResponse getAgencyAssignment(String applicationId) {
        LoanApplication app = getApplicationOrThrow(applicationId);

        ApplicationAgencyAssignment entity = applicationAgencyAssignmentRepository
                .findByApplication(app)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "ApplicationAgencyAssignment", "applicationId", applicationId));

        return agencyAssignmentMapper.toResponse(entity);
    }


    @Override
    public ApplicationSummaryResponse saveSummary(String applicationId,
                                                  ApplicationSummaryRequest request) {
        LoanApplication app = getApplicationOrThrow(applicationId);
        AdminUser reviewedBy = getAdminOrThrow(request.getReviewedByAdminId());

        ApplicationSummary entity = applicationSummaryRepository
                .findByApplication(app)
                .orElse(ApplicationSummary.builder().application(app).build());

        entity.setSummaryText(request.getSummaryText());
        entity.setFinalApprovedAmount(request.getFinalApprovedAmount());
        entity.setReviewedBy(reviewedBy);
        entity.setReviewedDate(LocalDateTime.now());

        entity = applicationSummaryRepository.save(entity);

        return mapSummaryToResponse(entity);
    }

    @Override
    public ApplicationSummaryResponse getSummary(String applicationId) {
        LoanApplication app = getApplicationOrThrow(applicationId);

        ApplicationSummary entity = applicationSummaryRepository
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

    private ApplicationStageCurrentResponse mapCurrentStageToResponse(ApplicationStageCurrent stage) {
        return ApplicationStageCurrentResponse.builder()
                .id(stage.getId())
                .applicationId(stage.getApplication().getId())
                .stage(stage.getStage().name())
                .remark(stage.getRemark())
                .updatedDate(stage.getUpdatedDate())
                .updatedByAdminId(stage.getUpdatedBy().getId())
                .build();
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


    private ApplicationAgencyAssignmentResponse mapAgencyAssignmentToResponse(ApplicationAgencyAssignment e) {
        return ApplicationAgencyAssignmentResponse.builder()
                .id(e.getId())
                .applicationId(e.getApplication().getId())
                .agencyId(e.getAgency().getId())
                .agencyName(e.getAgency().getAgencyName())
                .createdByAdminId(e.getCreatedBy().getId())
                .updatedByAdminId(e.getUpdatedBy() != null ? e.getUpdatedBy().getId() : null)
                .createdAt(e.getCreatedAt())
                .updatedAt(e.getUpdatedAt())
                .remarks(e.getRemarks())
                .build();
    }


    private ApplicationSummaryResponse mapSummaryToResponse(ApplicationSummary e) {
        return ApplicationSummaryResponse.builder()
                .id(e.getId())
                .applicationId(e.getApplication().getId())
                .summaryText(e.getSummaryText())
                .finalApprovedAmount(e.getFinalApprovedAmount())
                .reviewedByAdminId(e.getReviewedBy() != null ? e.getReviewedBy().getId() : null)
                .reviewedDate(e.getReviewedDate())
                .build();
    }

private void updateStage(LoanApplication app, ApplicationStageType stageType, AdminUser adminUser) {

    // ⭐ 1️⃣ UPDATE application_stage_current
    ApplicationStageCurrent current =
            applicationStageCurrentRepository.findByApplication(app).orElse(null);

    if (current == null) {
        current = ApplicationStageCurrent.builder()
                .application(app)
                .stage(stageType)
                .remark("Updated to: " + stageType.name())
                .updatedDate(LocalDateTime.now())
                .updatedBy(adminUser)
                .build();
    } else {
        current.setStage(stageType);
        current.setRemark("Updated to: " + stageType.name());
        current.setUpdatedDate(LocalDateTime.now());
        current.setUpdatedBy(adminUser);
    }

    applicationStageCurrentRepository.save(current);


    // ⭐ 2️⃣ UPDATE application_stage_history (NO NEW INSERTS)
    ApplicationStageHistory history =
            applicationStageHistoryRepository.findByApplication(app).orElse(null);

    if (history == null) {
        // Only first time, create one row
        history = ApplicationStageHistory.builder()
                .application(app)
                .status(ApplicationHistoryStatus.IN_PROGRESS)
                .createdDate(LocalDateTime.now())
                .updatedDate(LocalDateTime.now())
                .updatedBy(adminUser)
                .remarks("Stage updated to: " + stageType.name())
                .build();
    } else {
        // Always update existing row
        history.setStatus(ApplicationHistoryStatus.IN_PROGRESS);
        history.setUpdatedDate(LocalDateTime.now());
        history.setUpdatedBy(adminUser);
        history.setRemarks("Stage updated to: " + stageType.name());
    }

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

        Customer customer = customerRepository.findByEmail(request.getEmail())
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

            customerRepository.save(customer);
        }


        // 4️⃣ STEP 4: Update Application with customer

        app.setClient(customer);
        app.setAssociatedBank(customer.getBank());
        app = loanApplicationRepository.save(app);


        // 5️⃣ STEP 5: Save customer details linked to this application

        ApplicationCustomerDetails details = customerDetailsMapper.toEntity(request);
        details.setApplication(app);

        details = applicationCustomerDetailsRepository.save(details);


        // 6️⃣ STEP 6: Update stage history

        updateStage(app, ApplicationStageType.CUSTOMER_DETAILS, createdBy);


        // 7️⃣ DONE → return final response

        return customerDetailsMapper.toResponse(details);
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
