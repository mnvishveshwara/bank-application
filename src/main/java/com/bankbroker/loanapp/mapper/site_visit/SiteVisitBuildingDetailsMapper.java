package com.bankbroker.loanapp.mapper.site_visit;

import com.bankbroker.loanapp.dto.site_visit.SiteVisitBuildingDetailsRequest;
import com.bankbroker.loanapp.dto.site_visit.SiteVisitBuildingDetailsResponse;
import com.bankbroker.loanapp.entity.AdminUser;
import com.bankbroker.loanapp.entity.LoanApplication;
import com.bankbroker.loanapp.entity.site_visit.SiteVisitBuildingDetails;
import org.mapstruct.*;

import java.time.LocalDateTime;

@Mapper(componentModel = "spring", imports = LocalDateTime.class)
public interface SiteVisitBuildingDetailsMapper {

    // -------- CREATE --------
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "application", source = "application")
    @Mapping(target = "createdBy", source = "user")
    @Mapping(target = "createdDate", expression = "java(LocalDateTime.now())")
    @Mapping(target = "updatedBy", ignore = true)
    @Mapping(target = "updatedDate", ignore = true)
    SiteVisitBuildingDetails toEntity(
            SiteVisitBuildingDetailsRequest request,
            LoanApplication application,
            AdminUser user
    );

    // -------- UPDATE --------
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "application", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "updatedBy", ignore = true)
    @Mapping(target = "updatedDate", expression = "java(LocalDateTime.now())")
    void updateEntity(
            SiteVisitBuildingDetailsRequest request,
            @MappingTarget SiteVisitBuildingDetails entity
    );

    // -------- RESPONSE --------
    @Mapping(target = "applicationId", source = "application.id")
    SiteVisitBuildingDetailsResponse toResponse(
            SiteVisitBuildingDetails entity
    );
}
