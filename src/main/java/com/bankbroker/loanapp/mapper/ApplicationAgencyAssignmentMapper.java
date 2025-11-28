package com.bankbroker.loanapp.mapper;

import com.bankbroker.loanapp.dto.stage.ApplicationAgencyAssignmentResponse;
import com.bankbroker.loanapp.entity.stage.ApplicationAgencyAssignment;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface ApplicationAgencyAssignmentMapper {

    @Mapping(target = "applicationId", source = "application.id")
    @Mapping(target = "agencyId", source = "agency.id")
    @Mapping(target = "agencyName", source = "agency.agencyName")
    @Mapping(target = "createdByAdminId", source = "createdBy.id")
    @Mapping(target = "updatedByAdminId", source = "updatedBy.id", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.SET_TO_NULL)
    @Mapping(target = "remarks", source = "remarks")
    @Mapping(target = "createdAt", source = "createdAt")
    @Mapping(target = "updatedAt", source = "updatedAt")
    ApplicationAgencyAssignmentResponse toResponse(ApplicationAgencyAssignment entity);
}
