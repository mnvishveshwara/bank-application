package com.bankbroker.loanapp.service.site_visit.impl;

import com.bankbroker.loanapp.dto.site_visit.SiteVisitValuerDetailsRequest;
import com.bankbroker.loanapp.dto.site_visit.SiteVisitValuerDetailsResponse;
import com.bankbroker.loanapp.entity.core.LoanApplication;
import com.bankbroker.loanapp.entity.site_visit.SiteVisitValuerDetails;
import com.bankbroker.loanapp.mapper.site_visit.SiteVisitValuerDetailsMapper;
import com.bankbroker.loanapp.repository.core.LoanApplicationRepository;
import com.bankbroker.loanapp.repository.site_visit.SiteVisitValuerDetailsRepository;
import com.bankbroker.loanapp.service.site_visit.api.SiteVisitValuerDetailsService;
import com.bankbroker.loanapp.service.storage.FileStorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
@Transactional
public class SiteVisitValuerDetailsServiceImpl
        implements SiteVisitValuerDetailsService {

    private final SiteVisitValuerDetailsRepository repository;
    private final LoanApplicationRepository loanApplicationRepository;
    private final SiteVisitValuerDetailsMapper mapper;
    private final FileStorageService fileStorageService;

    @Override
    public SiteVisitValuerDetailsResponse upload(
            String applicationId,
            SiteVisitValuerDetailsRequest request) {

        LoanApplication application = loanApplicationRepository.findById(applicationId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid application id"));

        SiteVisitValuerDetails entity = repository.findByApplication_Id(applicationId)
                .orElseGet(() -> SiteVisitValuerDetails.builder()
                        .application(application)
                        .build());

        // ---------------- Organisation Seal ----------------
        MultipartFile seal = request.getOrganisationSeal();
        if (seal != null && !seal.isEmpty()) {

            String sealFileName = "organisation-seal"
                    + fileStorageService.getExtension(seal.getOriginalFilename());

            String sealPath = fileStorageService.store(
                    seal,
                    applicationId,
                    "site-visit/valuer-details",
                    sealFileName
            );

            entity.setOrganisationSealFileName(sealFileName);
            entity.setOrganisationSealFilePath(sealPath);
            entity.setOrganisationSealFileType(seal.getContentType());
            entity.setOrganisationSealFileSize(seal.getSize());
        }

        // ---------------- Valuer Signature ----------------
        MultipartFile signature = request.getValuerSignature();
        if (signature != null && !signature.isEmpty()) {

            String signatureFileName = "valuer-signature"
                    + fileStorageService.getExtension(signature.getOriginalFilename());

            String signaturePath = fileStorageService.store(
                    signature,
                    applicationId,
                    "site-visit/valuer-details",
                    signatureFileName
            );

            entity.setValuerSignatureFileName(signatureFileName);
            entity.setValuerSignatureFilePath(signaturePath);
            entity.setValuerSignatureFileType(signature.getContentType());
            entity.setValuerSignatureFileSize(signature.getSize());
        }

        SiteVisitValuerDetails saved = repository.save(entity);
        return mapper.toResponse(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public SiteVisitValuerDetailsResponse getByApplicationId(
            String applicationId) {

        SiteVisitValuerDetails entity = repository.findByApplication_Id(applicationId)
                .orElseThrow(() -> new IllegalArgumentException("Valuer details not found"));

        return mapper.toResponse(entity);
    }
}
