//package com.bankbroker.loanapp.controller.api;
//
//import com.bankbroker.loanapp.dto.stage.*;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RequestMapping("/api/applications/")
//public interface ApplicationStageControllerApi {
////
////    @PostMapping("/stages/current")
////    ResponseEntity<ApplicationStageCurrentResponse> saveCurrentStage(
////            @PathVariable String applicationId,
////            @RequestBody ApplicationStageCurrentRequest request);
////
////    @GetMapping("/stages/current")
////    ResponseEntity<List<ApplicationStageCurrentResponse>> getCurrentStages(
////            @PathVariable String applicationId);
//
//    @PostMapping("/history")
//    ResponseEntity<ApplicationHistoryResponse> addHistory(
//            @PathVariable String applicationId,
//            @RequestBody ApplicationHistoryRequest request);
//
//    @GetMapping("/history")
//    ResponseEntity<List<ApplicationHistoryResponse>> getHistory(
//            @PathVariable String applicationId);
//
//    @PostMapping("/customer-details")
//    ResponseEntity<ApplicationCustomerDetailsResponse> saveCustomerDetails(
//            @RequestBody ApplicationCustomerDetailsRequest request);
//
//    @GetMapping("/{applicationId}/get-customer-details")
//    ResponseEntity<ApplicationCustomerDetailsResponse> getCustomerDetails(
//            @PathVariable String applicationId);
//
//    @PostMapping("/{applicationId}/property-details")
//    ResponseEntity<ApplicationPropertyDetailsResponse> savePropertyDetails(
//            @PathVariable String applicationId,
//            @RequestBody ApplicationPropertyDetailsRequest request);
//
//    @GetMapping("/{applicationId}/property-details")
//    ResponseEntity<ApplicationPropertyDetailsResponse> getPropertyDetails(
//            @PathVariable String applicationId);
//
//    @PostMapping("/documents")
//    ResponseEntity<ApplicationDocumentDetailsResponse> saveDocumentDetails(
//            @PathVariable String applicationId,
//            @RequestBody ApplicationDocumentDetailsRequest request);
//
//    @GetMapping("/documents")
//    ResponseEntity<ApplicationDocumentDetailsResponse> getDocumentDetails(
//            @PathVariable String applicationId);
//
//    @PostMapping("/agency-assignment")
//    ResponseEntity<ApplicationAgencyAssignmentResponse> saveAgencyAssignment(
//            @PathVariable String applicationId,
//            @RequestBody ApplicationAgencyAssignmentRequest request);
//
//    @GetMapping("/agency-assignment")
//    ResponseEntity<ApplicationAgencyAssignmentResponse> getAgencyAssignment(
//            @PathVariable String applicationId);
//
//    @PostMapping("/summary")
//    ResponseEntity<ApplicationSummaryResponse> saveSummary(
//            @PathVariable String applicationId,
//            @RequestBody ApplicationSummaryRequest request);
//
//    @GetMapping("/summary")
//    ResponseEntity<ApplicationSummaryResponse> getSummary(
//            @PathVariable String applicationId);
//}

package com.bankbroker.loanapp.controller.api;

import com.bankbroker.loanapp.dto.stage.*;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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

    // 🔥 DOCUMENT UPLOAD (MULTIPART)
//    @PostMapping(
//            path = "/{applicationId}/upload-documents",
//            consumes = MediaType.MULTIPART_FORM_DATA_VALUE
//    )
//    ResponseEntity<ApplicationDocumentDetailsResponse> uploadDocuments(
//            @PathVariable String applicationId,
//            @RequestPart("files") List<MultipartFile> files,
//            @RequestPart("documentTypes") List<String> documentTypes
//    );


    @GetMapping("/{applicationId}/documents")
    ResponseEntity<ApplicationDocumentDetailsResponse> getDocumentDetails(
            @PathVariable String applicationId);

    // 🔹 Agency Assignment
    @PostMapping("/{applicationId}/agency-assignment")
    ResponseEntity<ApplicationAgencyAssignmentResponse> saveAgencyAssignment(
            @PathVariable String applicationId,
            @RequestBody ApplicationAgencyAssignmentRequest request);

    @GetMapping("/{applicationId}/agency-assignment")
    ResponseEntity<ApplicationAgencyAssignmentResponse> getAgencyAssignment(
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
