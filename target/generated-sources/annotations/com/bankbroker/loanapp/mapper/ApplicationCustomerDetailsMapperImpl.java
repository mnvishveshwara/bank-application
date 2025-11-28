package com.bankbroker.loanapp.mapper;

import com.bankbroker.loanapp.dto.stage.ApplicationCustomerDetailsRequest;
import com.bankbroker.loanapp.dto.stage.ApplicationCustomerDetailsResponse;
import com.bankbroker.loanapp.entity.LoanApplication;
import com.bankbroker.loanapp.entity.stage.ApplicationCustomerDetails;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-11-28T11:46:31+0530",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.8 (Oracle Corporation)"
)
@Component
public class ApplicationCustomerDetailsMapperImpl implements ApplicationCustomerDetailsMapper {

    @Override
    public ApplicationCustomerDetailsResponse toResponse(ApplicationCustomerDetails entity) {
        if ( entity == null ) {
            return null;
        }

        ApplicationCustomerDetailsResponse.ApplicationCustomerDetailsResponseBuilder applicationCustomerDetailsResponse = ApplicationCustomerDetailsResponse.builder();

        applicationCustomerDetailsResponse.applicationId( entityApplicationId( entity ) );
        applicationCustomerDetailsResponse.id( entity.getId() );
        applicationCustomerDetailsResponse.spockName( entity.getSpockName() );
        applicationCustomerDetailsResponse.leadSource( entity.getLeadSource() );
        applicationCustomerDetailsResponse.firstName( entity.getFirstName() );
        applicationCustomerDetailsResponse.middleName( entity.getMiddleName() );
        applicationCustomerDetailsResponse.lastName( entity.getLastName() );
        applicationCustomerDetailsResponse.primaryContactNumber( entity.getPrimaryContactNumber() );
        applicationCustomerDetailsResponse.secondaryContactNumber( entity.getSecondaryContactNumber() );
        applicationCustomerDetailsResponse.email( entity.getEmail() );
        applicationCustomerDetailsResponse.propertyReferenceNo( entity.getPropertyReferenceNo() );
        applicationCustomerDetailsResponse.propertyType( entity.getPropertyType() );
        applicationCustomerDetailsResponse.propertySubType( entity.getPropertySubType() );
        applicationCustomerDetailsResponse.loanType( entity.getLoanType() );
        applicationCustomerDetailsResponse.remarks( entity.getRemarks() );

        return applicationCustomerDetailsResponse.build();
    }

    @Override
    public ApplicationCustomerDetails toEntity(ApplicationCustomerDetailsRequest request) {
        if ( request == null ) {
            return null;
        }

        ApplicationCustomerDetails.ApplicationCustomerDetailsBuilder applicationCustomerDetails = ApplicationCustomerDetails.builder();

        applicationCustomerDetails.spockName( request.getSpockName() );
        applicationCustomerDetails.leadSource( request.getLeadSource() );
        applicationCustomerDetails.firstName( request.getFirstName() );
        applicationCustomerDetails.middleName( request.getMiddleName() );
        applicationCustomerDetails.lastName( request.getLastName() );
        applicationCustomerDetails.primaryContactNumber( request.getPrimaryContactNumber() );
        applicationCustomerDetails.secondaryContactNumber( request.getSecondaryContactNumber() );
        applicationCustomerDetails.email( request.getEmail() );
        applicationCustomerDetails.propertyReferenceNo( request.getPropertyReferenceNo() );
        applicationCustomerDetails.propertyType( request.getPropertyType() );
        applicationCustomerDetails.propertySubType( request.getPropertySubType() );
        applicationCustomerDetails.loanType( request.getLoanType() );
        applicationCustomerDetails.remarks( request.getRemarks() );

        return applicationCustomerDetails.build();
    }

    @Override
    public void updateEntityFromRequest(ApplicationCustomerDetailsRequest request, ApplicationCustomerDetails entity) {
        if ( request == null ) {
            return;
        }

        if ( request.getSpockName() != null ) {
            entity.setSpockName( request.getSpockName() );
        }
        if ( request.getLeadSource() != null ) {
            entity.setLeadSource( request.getLeadSource() );
        }
        if ( request.getFirstName() != null ) {
            entity.setFirstName( request.getFirstName() );
        }
        if ( request.getMiddleName() != null ) {
            entity.setMiddleName( request.getMiddleName() );
        }
        if ( request.getLastName() != null ) {
            entity.setLastName( request.getLastName() );
        }
        if ( request.getPrimaryContactNumber() != null ) {
            entity.setPrimaryContactNumber( request.getPrimaryContactNumber() );
        }
        if ( request.getSecondaryContactNumber() != null ) {
            entity.setSecondaryContactNumber( request.getSecondaryContactNumber() );
        }
        if ( request.getEmail() != null ) {
            entity.setEmail( request.getEmail() );
        }
        if ( request.getPropertyReferenceNo() != null ) {
            entity.setPropertyReferenceNo( request.getPropertyReferenceNo() );
        }
        if ( request.getPropertyType() != null ) {
            entity.setPropertyType( request.getPropertyType() );
        }
        if ( request.getPropertySubType() != null ) {
            entity.setPropertySubType( request.getPropertySubType() );
        }
        if ( request.getLoanType() != null ) {
            entity.setLoanType( request.getLoanType() );
        }
        if ( request.getRemarks() != null ) {
            entity.setRemarks( request.getRemarks() );
        }
    }

    private String entityApplicationId(ApplicationCustomerDetails applicationCustomerDetails) {
        if ( applicationCustomerDetails == null ) {
            return null;
        }
        LoanApplication application = applicationCustomerDetails.getApplication();
        if ( application == null ) {
            return null;
        }
        String id = application.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
