package com.bankbroker.loanapp.mapper;

import com.bankbroker.loanapp.dto.stage.ApplicationDocumentDetailsResponse;
import com.bankbroker.loanapp.dto.stage.ApplicationUploadedDocumentResponse;
import com.bankbroker.loanapp.entity.stage.ApplicationDocumentDetails;
import com.bankbroker.loanapp.entity.stage.ApplicationUploadedDocument;
import org.mapstruct.*;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ApplicationDocumentDetailsMapper {

    @Mapping(source = "application.id", target = "applicationId")
    ApplicationDocumentDetailsResponse toResponse(ApplicationDocumentDetails entity);

    ApplicationUploadedDocumentResponse toDocument(ApplicationUploadedDocument document);
}
