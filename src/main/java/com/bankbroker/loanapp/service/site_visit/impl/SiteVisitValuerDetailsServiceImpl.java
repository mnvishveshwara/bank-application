//package com.bankbroker.loanapp.service.site_visit.impl;
//
//import com.bankbroker.loanapp.dto.site_visit.SiteVisitValuerDetailsRequest;
//import com.bankbroker.loanapp.dto.site_visit.SiteVisitValuerDetailsResponse;
//import com.bankbroker.loanapp.entity.core.LoanApplication;
//import com.bankbroker.loanapp.entity.site_visit.SiteVisitValuerDetails;
//import com.bankbroker.loanapp.mapper.site_visit.SiteVisitValuerDetailsMapper;
//import com.bankbroker.loanapp.repository.core.LoanApplicationRepository;
//import com.bankbroker.loanapp.repository.site_visit.SiteVisitValuerDetailsRepository;
//import com.bankbroker.loanapp.service.site_visit.api.SiteVisitValuerDetailsService;
//import com.bankbroker.loanapp.service.storage.FileStorageService;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//import org.springframework.web.multipart.MultipartFile;
//
//@Slf4j
//@Service
//@RequiredArgsConstructor
//@Transactional
//public class SiteVisitValuerDetailsServiceImpl
//        implements SiteVisitValuerDetailsService {
//
//    private final SiteVisitValuerDetailsRepository repository;
//    private final LoanApplicationRepository loanApplicationRepository;
//    private final SiteVisitValuerDetailsMapper mapper;
//    private final FileStorageService fileStorageService;
//
//    @Override
//    public SiteVisitValuerDetailsResponse upload(
//            String applicationId,
//            SiteVisitValuerDetailsRequest request) {
//
//        LoanApplication application = loanApplicationRepository.findById(applicationId)
//                .orElseThrow(() -> new IllegalArgumentException("Invalid application id"));
//
//        SiteVisitValuerDetails entity = repository.findByApplication_Id(applicationId)
//                .orElseGet(() -> SiteVisitValuerDetails.builder()
//                        .application(application)
//                        .build());
//
//        // ---------------- Organisation Seal ----------------
//        MultipartFile seal = request.getOrganisationSeal();
//        if (seal != null && !seal.isEmpty()) {
//
//            String sealFileName = seal.getOriginalFilename();
//
//            String sealPath = fileStorageService.store(
//                    seal,
//                    applicationId,
//                    "site-visit/valuer-details",
//                    sealFileName
//            );
//
//            entity.setOrganisationSealFileName(sealFileName);
//            entity.setOrganisationSealFilePath(sealPath);
//            entity.setOrganisationSealFileType(seal.getContentType());
//            entity.setOrganisationSealFileSize(seal.getSize());
//        }
//
//        // ---------------- Valuer Signature ----------------
//        MultipartFile signature = request.getValuerSignature();
//        if (signature != null && !signature.isEmpty()) {
//
//            String signatureFileName = signature.getOriginalFilename();
//
//            String signaturePath = fileStorageService.store(
//                    signature,
//                    applicationId,
//                    "site-visit/valuer-details",
//                    signatureFileName
//            );
//
//            entity.setValuerSignatureFileName(signatureFileName);
//            entity.setValuerSignatureFilePath(signaturePath);
//            entity.setValuerSignatureFileType(signature.getContentType());
//            entity.setValuerSignatureFileSize(signature.getSize());
//        }
//
//        SiteVisitValuerDetails saved = repository.save(entity);
//        return mapper.toResponse(saved);
//    }
//
//    @Override
//    @Transactional(readOnly = true)
//    public SiteVisitValuerDetailsResponse getByApplicationId(
//            String applicationId) {
//
//        SiteVisitValuerDetails entity = repository.findByApplication_Id(applicationId)
//                .orElseThrow(() -> new IllegalArgumentException("Valuer details not found"));
//
//        return mapper.toResponse(entity);
//    }
//}

package com.bankbroker.loanapp.service.site_visit.impl;

import com.bankbroker.loanapp.dto.site_visit.SiteVisitValuerDetailsRequest;
import com.bankbroker.loanapp.dto.site_visit.SiteVisitValuerDetailsResponse;
import com.bankbroker.loanapp.entity.core.AdminUser;
import com.bankbroker.loanapp.entity.core.LoanApplication;
import com.bankbroker.loanapp.entity.enums.AssignmentType;
import com.bankbroker.loanapp.entity.enums.Role;
import com.bankbroker.loanapp.entity.site_visit.SiteVisitValuerDetails;
import com.bankbroker.loanapp.exception.ResourceNotFoundException;
import com.bankbroker.loanapp.mapper.site_visit.SiteVisitValuerDetailsMapper;
import com.bankbroker.loanapp.repository.core.LoanApplicationRepository;
import com.bankbroker.loanapp.repository.site_visit.SiteVisitValuerDetailsRepository;
import com.bankbroker.loanapp.repository.stage.ApplicationAgencyAssignmentRepository;
import com.bankbroker.loanapp.service.site_visit.api.SiteVisitValuerDetailsService;
import com.bankbroker.loanapp.service.storage.FileStorageService;
import com.bankbroker.loanapp.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class SiteVisitValuerDetailsServiceImpl
        implements SiteVisitValuerDetailsService {

    private final SiteVisitValuerDetailsRepository repository;
    private final LoanApplicationRepository loanApplicationRepository;
    private final ApplicationAgencyAssignmentRepository agencyAssignmentRepo; // Added
    private final SiteVisitValuerDetailsMapper mapper;
    private final FileStorageService fileStorageService;
    private final SecurityUtil securityUtil; // Added

    @Override
    public SiteVisitValuerDetailsResponse upload(
            String applicationId,
            SiteVisitValuerDetailsRequest request) {

        AdminUser user = securityUtil.getLoggedInAdmin();
        Role role = user.getRole();

        // 1. Role Validation
        if (role != Role.AGENCY_VALUATOR && role != Role.BANK_VALUATOR) {
            throw new RuntimeException("Unauthorized: Only assigned valuators can upload signature/seal");
        }

        LoanApplication application = loanApplicationRepository.findById(applicationId)
                .orElseThrow(() -> new ResourceNotFoundException("LoanApplication", "id", applicationId));

        // 2. Assignment Validation (Crucial for Legal Binding)
        validateAssignment(application, user);

        SiteVisitValuerDetails entity = repository.findByApplication_Id(applicationId)
                .orElseGet(() -> SiteVisitValuerDetails.builder()
                        .application(application)
                        .createdBy(user)
                        .createdDate(LocalDateTime.now())
                        .build());

        // Update Audit info
        entity.setUpdatedBy(user);
        entity.setUpdatedDate(LocalDateTime.now());

        // ---------------- Organisation Seal ----------------
        MultipartFile seal = request.getOrganisationSeal();
        if (seal != null && !seal.isEmpty()) {
            String sealFileName = seal.getOriginalFilename();
            String sealPath = fileStorageService.store(seal, applicationId, "site-visit/valuer-details", sealFileName);

            entity.setOrganisationSealFileName(sealFileName);
            entity.setOrganisationSealFilePath(sealPath);
            entity.setOrganisationSealFileType(seal.getContentType());
            entity.setOrganisationSealFileSize(seal.getSize());
        }

        // ---------------- Valuer Signature ----------------
        MultipartFile signature = request.getValuerSignature();
        if (signature != null && !signature.isEmpty()) {
            String signatureFileName = signature.getOriginalFilename();
            String signaturePath = fileStorageService.store(signature, applicationId, "site-visit/valuer-details", signatureFileName);

            entity.setValuerSignatureFileName(signatureFileName);
            entity.setValuerSignatureFilePath(signaturePath);
            entity.setValuerSignatureFileType(signature.getContentType());
            entity.setValuerSignatureFileSize(signature.getSize());
        }

        SiteVisitValuerDetails saved = repository.save(entity);
        return mapper.toResponse(saved);
    }

    /**
     * Shared validation to check if the user is authorized for this specific application
     */
    private void validateAssignment(LoanApplication app, AdminUser user) {
        boolean isAssigned = false;

        if (app.getAssignmentType() == AssignmentType.INTERNAL) {
            isAssigned = app.getInternalValuator() != null &&
                    app.getInternalValuator().getId().equals(user.getId());
        } else if (app.getAssignmentType() == AssignmentType.AGENCY) {
            isAssigned = agencyAssignmentRepo.existsByApplicationAndAgency(app, user.getAgency());
        }

        if (!isAssigned) {
            log.warn("Unauthorized signature attempt on app {} by user {}", app.getId(), user.getId());
            throw new RuntimeException("Access Denied: You are not authorized to sign off on this application.");
        }
    }

    @Override
    @Transactional(readOnly = true)
    public SiteVisitValuerDetailsResponse getByApplicationId(String applicationId) {
        SiteVisitValuerDetails entity = repository.findByApplication_Id(applicationId)
                .orElseThrow(() -> new ResourceNotFoundException("ValuerDetails", "applicationId", applicationId));

        return mapper.toResponse(entity);
    }
}