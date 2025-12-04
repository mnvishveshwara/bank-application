package com.bankbroker.loanapp.mapper;

import com.bankbroker.loanapp.dto.stage.ApplicationDocumentDetailsResponse;
import com.bankbroker.loanapp.dto.stage.ApplicationUploadedDocumentResponse;
import com.bankbroker.loanapp.entity.LoanApplication;
import com.bankbroker.loanapp.entity.stage.ApplicationDocumentDetails;
import com.bankbroker.loanapp.entity.stage.ApplicationUploadedDocument;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-12-04T14:08:58+0530",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.8 (Oracle Corporation)"
)
@Component
public class ApplicationDocumentDetailsMapperImpl implements ApplicationDocumentDetailsMapper {

    @Override
    public ApplicationDocumentDetailsResponse toResponse(ApplicationDocumentDetails entity) {
        if ( entity == null ) {
            return null;
        }

        ApplicationDocumentDetailsResponse.ApplicationDocumentDetailsResponseBuilder applicationDocumentDetailsResponse = ApplicationDocumentDetailsResponse.builder();

        applicationDocumentDetailsResponse.applicationId( entityApplicationId( entity ) );
        applicationDocumentDetailsResponse.id( entity.getId() );
        applicationDocumentDetailsResponse.documents( applicationUploadedDocumentListToApplicationUploadedDocumentResponseList( entity.getDocuments() ) );

        return applicationDocumentDetailsResponse.build();
    }

    @Override
    public ApplicationUploadedDocumentResponse toDocument(ApplicationUploadedDocument document) {
        if ( document == null ) {
            return null;
        }

        ApplicationUploadedDocumentResponse.ApplicationUploadedDocumentResponseBuilder applicationUploadedDocumentResponse = ApplicationUploadedDocumentResponse.builder();

        applicationUploadedDocumentResponse.id( document.getId() );
        applicationUploadedDocumentResponse.fileName( document.getFileName() );
        applicationUploadedDocumentResponse.fileType( document.getFileType() );
        applicationUploadedDocumentResponse.fileSizeKB( document.getFileSizeKB() );
        applicationUploadedDocumentResponse.documentType( document.getDocumentType() );
        applicationUploadedDocumentResponse.fileUrl( document.getFileUrl() );

        return applicationUploadedDocumentResponse.build();
    }

    private String entityApplicationId(ApplicationDocumentDetails applicationDocumentDetails) {
        if ( applicationDocumentDetails == null ) {
            return null;
        }
        LoanApplication application = applicationDocumentDetails.getApplication();
        if ( application == null ) {
            return null;
        }
        String id = application.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    protected List<ApplicationUploadedDocumentResponse> applicationUploadedDocumentListToApplicationUploadedDocumentResponseList(List<ApplicationUploadedDocument> list) {
        if ( list == null ) {
            return null;
        }

        List<ApplicationUploadedDocumentResponse> list1 = new ArrayList<ApplicationUploadedDocumentResponse>( list.size() );
        for ( ApplicationUploadedDocument applicationUploadedDocument : list ) {
            list1.add( toDocument( applicationUploadedDocument ) );
        }

        return list1;
    }
}
