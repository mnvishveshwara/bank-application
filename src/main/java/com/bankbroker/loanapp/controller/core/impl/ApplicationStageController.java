package com.bankbroker.loanapp.controller.core.impl;

import com.bankbroker.loanapp.controller.core.api.ApplicationStageControllerApi;
import com.bankbroker.loanapp.dto.master.AgencyMasterResponse;
import com.bankbroker.loanapp.dto.stage.*;
import com.bankbroker.loanapp.service.core.api.ApplicationStageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/applications")   // ADD THIS
public class ApplicationStageController implements ApplicationStageControllerApi {

    private final ApplicationStageService applicationStageService;

    // ========================= HISTORY =========================
    @Override
    public ResponseEntity<ApplicationHistoryResponse> addHistory(
            String applicationId, ApplicationHistoryRequest request) {

        return ResponseEntity.ok(applicationStageService.addHistory(applicationId, request));
    }

    @Override
    public ResponseEntity<List<ApplicationHistoryResponse>> getHistory(String applicationId) {
        return ResponseEntity.ok(applicationStageService.getHistory(applicationId));
    }

    // ========================= CUSTOMER =========================
    @Override
    public ResponseEntity<ApplicationCustomerDetailsResponse> saveCustomerDetails(
            ApplicationCustomerDetailsRequest request) {

        return ResponseEntity.ok(applicationStageService.createApplicationThenSaveCustomerDetails(request));
    }

    @Override
    public ResponseEntity<ApplicationCustomerDetailsResponse> getCustomerDetails(String applicationId) {
        return ResponseEntity.ok(applicationStageService.getCustomerDetails(applicationId));
    }

    // ========================= PROPERTY =========================
    @Override
    public ResponseEntity<ApplicationPropertyDetailsResponse> savePropertyDetails(
            String applicationId, ApplicationPropertyDetailsRequest request) {

        return ResponseEntity.ok(applicationStageService.savePropertyDetails(applicationId, request));
    }

    @Override
    public ResponseEntity<ApplicationPropertyDetailsResponse> getPropertyDetails(String applicationId) {
        return ResponseEntity.ok(applicationStageService.getPropertyDetails(applicationId));
    }


    @PostMapping(
            path = "/{applicationId}/upload-documents",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE
    )
    public ResponseEntity<ApplicationDocumentDetailsResponse> uploadDocuments(
            @PathVariable String applicationId,
            @RequestPart("files") List<MultipartFile> files,
            @RequestParam("documentTypes") List<String> documentTypes
    ) {
        log.info("Uploading documents");
        return ResponseEntity.ok(
                applicationStageService.uploadDocuments(applicationId, files, documentTypes)
        );
    }

    @Override
    public ResponseEntity<ApplicationDocumentDetailsResponse> getDocumentDetails(String applicationId) {
        return ResponseEntity.ok(applicationStageService.getDocumentDetails(applicationId));
    }

    // ========================= AGENCY =========================
    @Override
    public ResponseEntity<ApplicationAgencyAssignmentResponse> saveAgencyAssignment(
            @PathVariable String applicationId,
            @RequestBody ApplicationAgencyAssignmentRequest request) {

        return ResponseEntity.ok(applicationStageService.saveAgencyAssignment(applicationId, request));
    }

    @Override
    public ResponseEntity<AgencyMasterResponse> getAgencyAssignment(
            @PathVariable String applicationId) {

        return ResponseEntity.ok(applicationStageService.getAgencyAssignment(applicationId));
    }

    // ========================= SUMMARY =========================
    @Override
    public ResponseEntity<ApplicationSummaryResponse> saveSummary(
            String applicationId, ApplicationSummaryRequest request) {

        return ResponseEntity.ok(applicationStageService.saveSummary(applicationId, request));
    }

    @Override
    public ResponseEntity<ApplicationSummaryResponse> getSummary(String applicationId) {
        return ResponseEntity.ok(applicationStageService.getSummary(applicationId));
    }
}
