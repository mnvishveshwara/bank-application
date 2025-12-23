package com.bankbroker.loanapp.service.site_visit.impl;

import com.bankbroker.loanapp.dto.site_visit.SiteVisitTechnicalLandDetailsResponse;
import com.bankbroker.loanapp.dto.site_visit.SiteVisitTechnicalLandImageResponse;
import com.bankbroker.loanapp.dto.site_visit.SiteVisitTechnicalLandRequest;
import com.bankbroker.loanapp.entity.core.AdminUser;
import com.bankbroker.loanapp.entity.core.LoanApplication;
import com.bankbroker.loanapp.entity.site_visit.SiteVisitTechnicalLand;
import com.bankbroker.loanapp.entity.site_visit.SiteVisitTechnicalLandImage;
import com.bankbroker.loanapp.mapper.site_visit.SiteVisitTechnicalLandMapper;
import com.bankbroker.loanapp.repository.core.LoanApplicationRepository;
import com.bankbroker.loanapp.repository.site_visit.SiteVisitTechnicalLandImageRepository;
import com.bankbroker.loanapp.repository.site_visit.SiteVisitTechnicalLandRepository;
import com.bankbroker.loanapp.service.site_visit.api.SiteVisitTechnicalLandService;
import com.bankbroker.loanapp.service.storage.FileStorageService;
import com.bankbroker.loanapp.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class SiteVisitTechnicalLandServiceImpl
        implements SiteVisitTechnicalLandService {

    private final LoanApplicationRepository loanRepo;
    private final SiteVisitTechnicalLandRepository landRepo;
    private final SiteVisitTechnicalLandImageRepository imageRepo;
    private final SiteVisitTechnicalLandMapper mapper;
    private final SecurityUtil securityUtil;
    private final FileStorageService fileStorageService;


    @Override
    public SiteVisitTechnicalLandDetailsResponse save(
            String applicationId,
            SiteVisitTechnicalLandRequest request) {

        AdminUser user = securityUtil.getLoggedInAdmin();

        LoanApplication app = loanRepo.findById(applicationId)
                .orElseThrow(() -> new RuntimeException("Application not found"));

        SiteVisitTechnicalLand land = landRepo.findByApplication(app)
                .orElseGet(() -> mapper.toEntity(request, app, user));

        if (land.getId() != null) {
            mapper.updateEntity(request, land);
            land.setUpdatedBy(user);
        }

        land.setLandAreaMatch(
                land.getLandAreaAsPerActual() != null &&
                        land.getLandAreaAsPerDocument() != null &&
                        land.getLandAreaAsPerLayoutPlan() != null &&
                        land.getLandAreaAsPerActual()
                                .equals(land.getLandAreaAsPerDocument()) &&
                        land.getLandAreaAsPerActual()
                                .equals(land.getLandAreaAsPerLayoutPlan())
        );

        SiteVisitTechnicalLand saved = landRepo.save(land);

        return buildResponse(saved);
    }

    @Override
    public SiteVisitTechnicalLandDetailsResponse get(String applicationId) {

        LoanApplication app = loanRepo.findById(applicationId)
                .orElseThrow(() -> new RuntimeException("Application not found"));

        SiteVisitTechnicalLand land = landRepo.findByApplication(app)
                .orElseThrow(() -> new RuntimeException("Land not found"));

        return buildResponse(land);
    }

    @Override
    public void uploadImages(String applicationId, List<MultipartFile> files) {

        if (files == null || files.isEmpty()) {
            return;
        }

        AdminUser user = securityUtil.getLoggedInAdmin();

        LoanApplication app = loanRepo.findById(applicationId)
                .orElseThrow(() -> new RuntimeException("Application not found"));

        SiteVisitTechnicalLand land = landRepo.findByApplication(app)
                .orElseThrow(() ->
                        new RuntimeException("Save land details before uploading images")
                );

        for (MultipartFile file : files) {

            if (file == null || file.isEmpty()) continue;

            // 🔹 Generate unique filename
            String extension = fileStorageService.getExtension(file.getOriginalFilename());
            String fileName = System.currentTimeMillis() + extension;

            // 🔹 Store file using common service
            String filePath = fileStorageService.store(
                    file,
                    applicationId,
                    "TECHNICAL_LAND",
                    fileName
            );

            // 🔹 Persist DB record
            SiteVisitTechnicalLandImage image =
                    SiteVisitTechnicalLandImage.builder()
                            .technicalLand(land)
                            .fileName(fileName)
                            .filePath(filePath)
                            .contentType(file.getContentType())
                            .fileSize(file.getSize())
                            .uploadedBy(user)
                            .uploadedDate(LocalDateTime.now())
                            .build();

            imageRepo.save(image);
        }
    }

    private SiteVisitTechnicalLandDetailsResponse buildResponse(
            SiteVisitTechnicalLand land) {

        List<SiteVisitTechnicalLandImageResponse> images =
                imageRepo.findByTechnicalLand(land)
                        .stream()
                        .map(img -> SiteVisitTechnicalLandImageResponse.builder()
                                .id(img.getId())
                                .fileName(img.getFileName())
                                .filePath(img.getFilePath())
                                .contentType(img.getContentType())
                                .fileSize(img.getFileSize())
                                .build())
                        .toList();

        return SiteVisitTechnicalLandDetailsResponse.builder()
                .landId(land.getId())
                .applicationId(land.getApplication().getId())
                .landAreaAsPerActual(land.getLandAreaAsPerActual())
                .landAreaAsPerDocument(land.getLandAreaAsPerDocument())
                .landAreaAsPerLayoutPlan(land.getLandAreaAsPerLayoutPlan())
                .landAreaMatch(land.getLandAreaMatch())
                .holdingType(land.getHoldingType())
                .images(images)
                .build();
    }
}
