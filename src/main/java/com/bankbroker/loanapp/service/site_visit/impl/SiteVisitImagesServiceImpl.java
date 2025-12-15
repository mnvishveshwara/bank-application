package com.bankbroker.loanapp.service.site_visit.impl;


import com.bankbroker.loanapp.dto.site_visit.SiteVisitImageResponse;
import com.bankbroker.loanapp.entity.LoanApplication;
import com.bankbroker.loanapp.entity.enums.SiteVisitImageCategory;
import com.bankbroker.loanapp.entity.site_visit.*;
import com.bankbroker.loanapp.repository.LoanApplicationRepository;
import com.bankbroker.loanapp.repository.site_visit.SiteVisitImageGroupRepository;
import com.bankbroker.loanapp.repository.site_visit.SiteVisitImageRepository;
import com.bankbroker.loanapp.service.site_visit.SiteVisitImagesService;
import com.bankbroker.loanapp.service.storage.FileStorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class SiteVisitImagesServiceImpl implements SiteVisitImagesService {

    private final SiteVisitImageGroupRepository groupRepository;
    private final SiteVisitImageRepository imageRepository;
    private final LoanApplicationRepository loanApplicationRepository;
    private final FileStorageService fileStorageService;

    @Override
    public void uploadAll(
            String applicationId,
            List<MultipartFile> propertyImages,
            List<MultipartFile> unitImages,
            List<MultipartFile> comparisonImages) {

        LoanApplication application = loanApplicationRepository.findById(applicationId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid application id"));

        SiteVisitImageGroup group = groupRepository
                .findByApplication_Id(applicationId)
                .orElseGet(() -> groupRepository.save(
                        SiteVisitImageGroup.builder()
                                .application(application)
                                .build()
                ));

        saveImages(applicationId, group, SiteVisitImageCategory.PROPERTY_SPECIFIC, propertyImages);
        saveImages(applicationId, group, SiteVisitImageCategory.UNIT_SPECIFIC, unitImages);
        saveImages(applicationId, group, SiteVisitImageCategory.COMPARISON, comparisonImages);
    }

    private void saveImages(
            String applicationId,
            SiteVisitImageGroup group,
            SiteVisitImageCategory category,
            List<MultipartFile> files) {

        if (files == null || files.isEmpty()) return;

        for (MultipartFile file : files) {

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

    @Override
    @Transactional(readOnly = true)
    public List<SiteVisitImageResponse> getImages(String applicationId) {

        SiteVisitImageGroup group = groupRepository.findByApplication_Id(applicationId)
                .orElseThrow(() -> new IllegalArgumentException("Images not found"));

        List<SiteVisitImageResponse> responses = new ArrayList<>();

        for (SiteVisitImage image : group.getImages()) {
            responses.add(
                    SiteVisitImageResponse.builder()
                            .id(image.getId())
                            .category(image.getCategory().name())
                            .fileName(image.getFileName())
                            .filePath(image.getFilePath())
                            .build()
            );
        }

        return responses;
    }
}