
package com.bankbroker.loanapp.controller.core.api;

import com.bankbroker.loanapp.dto.master.AgencyMasterResponse;
import com.bankbroker.loanapp.dto.stage.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/applications")
public interface ApplicationStageControllerApi {

    // 🔹 History
    @PostMapping("/{applicationId}/history")
    ResponseEntity<ApplicationHistoryResponse> addHistory(
            @PathVariable String applicationId,
            @RequestBody ApplicationHistoryRequest request);

    @GetMapping("/{applicationId}/history")
    ResponseEntity<List<ApplicationHistoryResponse>> getHistory(
            @PathVariable String applicationId);

    // 🔹 Create Application + Save Customer Details
    @PostMapping("/customer-details")
    ResponseEntity<ApplicationCustomerDetailsResponse> saveCustomerDetails(
            @RequestBody ApplicationCustomerDetailsRequest request);

    @GetMapping("/{applicationId}/customer-details")
    ResponseEntity<ApplicationCustomerDetailsResponse> getCustomerDetails(
            @PathVariable String applicationId);

    // 🔹 Property Details
    @PostMapping("/{applicationId}/property-details")
    ResponseEntity<ApplicationPropertyDetailsResponse> savePropertyDetails(
            @PathVariable String applicationId,
            @RequestBody ApplicationPropertyDetailsRequest request);

    @GetMapping("/{applicationId}/property-details")
    ResponseEntity<ApplicationPropertyDetailsResponse> getPropertyDetails(
            @PathVariable String applicationId);



    @GetMapping("/{applicationId}/documents")
    ResponseEntity<ApplicationDocumentDetailsResponse> getDocumentDetails(
            @PathVariable String applicationId);

    // 🔹 Agency Assignment
    @PostMapping("/{applicationId}/agency-assignment")
    ResponseEntity<ApplicationAgencyAssignmentResponse> saveAgencyAssignment(
            @PathVariable String applicationId,
            @RequestBody ApplicationAgencyAssignmentRequest request);

    @GetMapping("/{applicationId}/agency-assignment")
    ResponseEntity<AgencyMasterResponse> getAgencyAssignment(
            @PathVariable String applicationId);

    // 🔹 Summary
    @PostMapping("/{applicationId}/summary")
    ResponseEntity<ApplicationSummaryResponse> saveSummary(
            @PathVariable String applicationId,
            @RequestBody ApplicationSummaryRequest request);

    @GetMapping("/{applicationId}/summary")
    ResponseEntity<ApplicationSummaryResponse> getSummary(
            @PathVariable String applicationId);
}
