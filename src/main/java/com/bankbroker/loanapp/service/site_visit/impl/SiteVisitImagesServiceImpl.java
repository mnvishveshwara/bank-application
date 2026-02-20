//package com.bankbroker.loanapp.service.site_visit.impl;
//
//
//import com.bankbroker.loanapp.dto.site_visit.SiteVisitImageResponse;
//import com.bankbroker.loanapp.entity.core.LoanApplication;
//import com.bankbroker.loanapp.entity.enums.SiteVisitImageCategory;
//import com.bankbroker.loanapp.entity.site_visit.*;
//import com.bankbroker.loanapp.repository.core.LoanApplicationRepository;
//import com.bankbroker.loanapp.repository.site_visit.SiteVisitImageGroupRepository;
//import com.bankbroker.loanapp.repository.site_visit.SiteVisitImageRepository;
//import com.bankbroker.loanapp.service.site_visit.api.SiteVisitImagesService;
//import com.bankbroker.loanapp.service.storage.FileStorageService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Service
//@RequiredArgsConstructor
//@Transactional
//public class SiteVisitImagesServiceImpl implements SiteVisitImagesService {
//
//    private final SiteVisitImageGroupRepository groupRepository;
//    private final SiteVisitImageRepository imageRepository;
//    private final LoanApplicationRepository loanApplicationRepository;
//    private final FileStorageService fileStorageService;
//
//    @Override
//    public void uploadAll(
//            String applicationId,
//            List<MultipartFile> propertyImages,
//            List<MultipartFile> unitImages,
//            List<MultipartFile> comparisonImages) {
//
//        LoanApplication application = loanApplicationRepository.findById(applicationId)
//                .orElseThrow(() -> new IllegalArgumentException("Invalid application id"));
//
//        SiteVisitImageGroup group = groupRepository
//                .findByApplication_Id(applicationId)
//                .orElseGet(() -> groupRepository.save(
//                        SiteVisitImageGroup.builder()
//                                .application(application)
//                                .build()
//                ));
//
//        saveImages(applicationId, group, SiteVisitImageCategory.PROPERTY_SPECIFIC, propertyImages);
//        saveImages(applicationId, group, SiteVisitImageCategory.UNIT_SPECIFIC, unitImages);
//        saveImages(applicationId, group, SiteVisitImageCategory.COMPARISON, comparisonImages);
//    }
//
//    private void saveImages(
//            String applicationId,
//            SiteVisitImageGroup group,
//            SiteVisitImageCategory category,
//            List<MultipartFile> files) {
//
//        if (files == null || files.isEmpty()) return;
//
//        for (MultipartFile file : files) {
//
//            String fileName = category.name().toLowerCase()
//                    + "-" + System.currentTimeMillis()
//                    + fileStorageService.getExtension(file.getOriginalFilename());
//
//            String path = fileStorageService.store(
//                    file,
//                    applicationId,
//                    "site-visit/images/" + category.name().toLowerCase(),
//                    fileName
//            );
//
//            SiteVisitImage image = SiteVisitImage.builder()
//                    .imageGroup(group)
//                    .category(category)
//                    .fileName(fileName)
//                    .filePath(path)
//                    .fileType(file.getContentType())
//                    .fileSize(file.getSize())
//                    .build();
//
//            imageRepository.save(image);
//        }
//    }
//
//    @Override
//    @Transactional(readOnly = true)
//    public List<SiteVisitImageResponse> getImages(String applicationId) {
//
//        SiteVisitImageGroup group = groupRepository.findByApplication_Id(applicationId)
//                .orElseThrow(() -> new IllegalArgumentException("Images not found"));
//
//        List<SiteVisitImageResponse> responses = new ArrayList<>();
//
//        for (SiteVisitImage image : group.getImages()) {
//            responses.add(
//                    SiteVisitImageResponse.builder()
//                            .id(image.getId())
//                            .category(image.getCategory().name())
//                            .fileName(image.getFileName())
//                            .filePath(image.getFilePath())
//                            .build()
//            );
//        }
//
//        return responses;
//    }
//}

package com.bankbroker.loanapp.service.site_visit.impl;

import com.bankbroker.loanapp.dto.site_visit.SiteVisitImageResponse;
import com.bankbroker.loanapp.entity.core.AdminUser;
import com.bankbroker.loanapp.entity.core.LoanApplication;
import com.bankbroker.loanapp.entity.enums.AssignmentType;
import com.bankbroker.loanapp.entity.enums.Role;
import com.bankbroker.loanapp.entity.enums.SiteVisitImageCategory;
import com.bankbroker.loanapp.entity.site_visit.*;
import com.bankbroker.loanapp.exception.ResourceNotFoundException;
import com.bankbroker.loanapp.repository.core.LoanApplicationRepository;
import com.bankbroker.loanapp.repository.site_visit.SiteVisitImageGroupRepository;
import com.bankbroker.loanapp.repository.site_visit.SiteVisitImageRepository;
import com.bankbroker.loanapp.repository.stage.ApplicationAgencyAssignmentRepository;
import com.bankbroker.loanapp.service.site_visit.api.SiteVisitImagesService;
import com.bankbroker.loanapp.service.storage.FileStorageService;
import com.bankbroker.loanapp.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class SiteVisitImagesServiceImpl implements SiteVisitImagesService {

    private final SiteVisitImageGroupRepository groupRepository;
    private final SiteVisitImageRepository imageRepository;
    private final LoanApplicationRepository loanApplicationRepository;
    private final ApplicationAgencyAssignmentRepository agencyAssignmentRepo;
    private final FileStorageService fileStorageService;
    private final SecurityUtil securityUtil;

    @Override
    public void uploadAll(
            String applicationId,
            List<MultipartFile> propertyImages,
            List<MultipartFile> unitImages,
            List<MultipartFile> comparisonImages) {

        AdminUser user = securityUtil.getLoggedInAdmin();
        Role role = user.getRole();

        // 1. Role Validation
        if (role != Role.AGENCY_VALUATOR && role != Role.BANK_VALUATOR) {
            throw new RuntimeException("Unauthorized: Only assigned valuators can upload site images");
        }

        LoanApplication application = loanApplicationRepository.findById(applicationId)
                .orElseThrow(() -> new ResourceNotFoundException("LoanApplication", "id", applicationId));

        // 2. Assignment Validation
        validateAssignment(application, user);

        SiteVisitImageGroup group = groupRepository
                .findByApplication_Id(applicationId)
                .orElseGet(() -> groupRepository.save(
                        SiteVisitImageGroup.builder()
                                .application(application)
                                .build()
                ));

        // Update group audit info


        saveImages(applicationId, group, SiteVisitImageCategory.PROPERTY_SPECIFIC, propertyImages, user);
        saveImages(applicationId, group, SiteVisitImageCategory.UNIT_SPECIFIC, unitImages, user);
        saveImages(applicationId, group, SiteVisitImageCategory.COMPARISON, comparisonImages, user);
    }

    private void saveImages(
            String applicationId,
            SiteVisitImageGroup group,
            SiteVisitImageCategory category,
            List<MultipartFile> files,
            AdminUser user) {

        if (files == null || files.isEmpty()) return;

        for (MultipartFile file : files) {
            if (file == null || file.isEmpty()) continue;

            String fileName = category.name().toLowerCase()
                    + "-" + System.currentTimeMillis()
                    + fileStorageService.getExtension(file.getOriginalFilename());

            String path = fileStorageService.store(
                    file,
                    applicationId,
                    "site-visit/images/" + category.name().toLowerCase(),
                    fileName
            );

            SiteVisitImage image = SiteVisitImage.builder()
                    .imageGroup(group)
                    .category(category)
                    .fileName(fileName)
                    .filePath(path)
                    .fileType(file.getContentType())
                    .fileSize(file.getSize())
                    .build();

            imageRepository.save(image);
        }
    }

    private void validateAssignment(LoanApplication app, AdminUser user) {
        boolean isAssigned = false;

        if (app.getAssignmentType() == AssignmentType.INTERNAL) {
            isAssigned = app.getInternalValuator() != null &&
                    app.getInternalValuator().getId().equals(user.getId());
        } else if (app.getAssignmentType() == AssignmentType.AGENCY) {
            isAssigned = agencyAssignmentRepo.existsByApplicationAndAgency(app, user.getAgency());
        }

        if (!isAssigned) {
            log.warn("Unauthorized image upload attempt for app {} by user {}", app.getId(), user.getId());
            throw new RuntimeException("Access Denied: You are not authorized to upload images for this application.");
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<SiteVisitImageResponse> getImages(String applicationId) {
        SiteVisitImageGroup group = groupRepository.findByApplication_Id(applicationId)
                .orElseThrow(() -> new ResourceNotFoundException("SiteVisitImageGroup", "applicationId", applicationId));

        return group.getImages().stream()
                .map(image -> SiteVisitImageResponse.builder()
                        .id(image.getId())
                        .category(image.getCategory().name())
                        .fileName(image.getFileName())
                        .filePath(image.getFilePath())
                        .build())
                .toList();
    }
}