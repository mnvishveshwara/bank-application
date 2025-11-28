package com.bankbroker.loanapp.service;

import com.bankbroker.loanapp.dto.stage.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ApplicationStageService {

    ApplicationStageCurrentResponse saveCurrentStage(String applicationId,
                                                     ApplicationStageCurrentRequest request);
    List<ApplicationStageCurrentResponse> getCurrentStages(String applicationId);

    ApplicationHistoryResponse addHistory(String applicationId,
                                          ApplicationHistoryRequest request);
    List<ApplicationHistoryResponse> getHistory(String applicationId);

    ApplicationCustomerDetailsResponse saveCustomerDetails(String applicationId,
                                                           ApplicationCustomerDetailsRequest request);
    ApplicationCustomerDetailsResponse getCustomerDetails(String applicationId);

    ApplicationPropertyDetailsResponse savePropertyDetails(String applicationId,
                                                           ApplicationPropertyDetailsRequest request);
    ApplicationPropertyDetailsResponse getPropertyDetails(String applicationId);

ApplicationDocumentDetailsResponse uploadDocuments(
        String applicationId,
        List<MultipartFile> files,
        List<String> documentTypes);

    ApplicationDocumentDetailsResponse getDocumentDetails(String applicationId);

    ApplicationAgencyAssignmentResponse saveAgencyAssignment(String applicationId,
                                                             ApplicationAgencyAssignmentRequest request);
    ApplicationAgencyAssignmentResponse getAgencyAssignment(String applicationId);

    ApplicationSummaryResponse saveSummary(String applicationId,
                                           ApplicationSummaryRequest request);
    ApplicationSummaryResponse getSummary(String applicationId);

    ApplicationCustomerDetailsResponse createApplicationThenSaveCustomerDetails(
            ApplicationCustomerDetailsRequest request
    );

}
