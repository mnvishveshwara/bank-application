package com.bankbroker.loanapp.mapper;

import com.bankbroker.loanapp.dto.stage.ApplicationAgencyAssignmentResponse;
import com.bankbroker.loanapp.entity.AdminUser;
import com.bankbroker.loanapp.entity.AgencyMaster;
import com.bankbroker.loanapp.entity.LoanApplication;
import com.bankbroker.loanapp.entity.stage.ApplicationAgencyAssignment;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-11-28T16:04:33+0530",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.8 (Oracle Corporation)"
)
@Component
public class ApplicationAgencyAssignmentMapperImpl implements ApplicationAgencyAssignmentMapper {

    @Override
    public ApplicationAgencyAssignmentResponse toResponse(ApplicationAgencyAssignment entity) {
        if ( entity == null ) {
            return null;
        }

        ApplicationAgencyAssignmentResponse.ApplicationAgencyAssignmentResponseBuilder applicationAgencyAssignmentResponse = ApplicationAgencyAssignmentResponse.builder();

        applicationAgencyAssignmentResponse.applicationId( entityApplicationId( entity ) );
        applicationAgencyAssignmentResponse.agencyId( entityAgencyId( entity ) );
        applicationAgencyAssignmentResponse.agencyName( entityAgencyAgencyName( entity ) );
        applicationAgencyAssignmentResponse.createdByAdminId( entityCreatedById( entity ) );
        applicationAgencyAssignmentResponse.updatedByAdminId( entityUpdatedById( entity ) );
        applicationAgencyAssignmentResponse.remarks( entity.getRemarks() );
        applicationAgencyAssignmentResponse.createdAt( entity.getCreatedAt() );
        applicationAgencyAssignmentResponse.updatedAt( entity.getUpdatedAt() );
        applicationAgencyAssignmentResponse.id( entity.getId() );

        return applicationAgencyAssignmentResponse.build();
    }

    private String entityApplicationId(ApplicationAgencyAssignment applicationAgencyAssignment) {
        if ( applicationAgencyAssignment == null ) {
            return null;
        }
        LoanApplication application = applicationAgencyAssignment.getApplication();
        if ( application == null ) {
            return null;
        }
        String id = application.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private Long entityAgencyId(ApplicationAgencyAssignment applicationAgencyAssignment) {
        if ( applicationAgencyAssignment == null ) {
            return null;
        }
        AgencyMaster agency = applicationAgencyAssignment.getAgency();
        if ( agency == null ) {
            return null;
        }
        Long id = agency.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private String entityAgencyAgencyName(ApplicationAgencyAssignment applicationAgencyAssignment) {
        if ( applicationAgencyAssignment == null ) {
            return null;
        }
        AgencyMaster agency = applicationAgencyAssignment.getAgency();
        if ( agency == null ) {
            return null;
        }
        String agencyName = agency.getAgencyName();
        if ( agencyName == null ) {
            return null;
        }
        return agencyName;
    }

    private String entityCreatedById(ApplicationAgencyAssignment applicationAgencyAssignment) {
        if ( applicationAgencyAssignment == null ) {
            return null;
        }
        AdminUser createdBy = applicationAgencyAssignment.getCreatedBy();
        if ( createdBy == null ) {
            return null;
        }
        String id = createdBy.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private String entityUpdatedById(ApplicationAgencyAssignment applicationAgencyAssignment) {
        if ( applicationAgencyAssignment == null ) {
            return null;
        }
        AdminUser updatedBy = applicationAgencyAssignment.getUpdatedBy();
        if ( updatedBy == null ) {
            return null;
        }
        String id = updatedBy.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
