package com.bankbroker.loanapp.mapper.site_visit;

import com.bankbroker.loanapp.dto.site_visit.SiteVisitInfrastructureDetailsRequest;
import com.bankbroker.loanapp.dto.site_visit.SiteVisitInfrastructureDetailsResponse;
import com.bankbroker.loanapp.entity.AdminUser;
import com.bankbroker.loanapp.entity.LoanApplication;
import com.bankbroker.loanapp.entity.site_visit.SiteVisitInfrastructureDetails;
import org.mapstruct.*;

import java.time.LocalDateTime;
@Mapper(
        componentModel = "spring",
        imports = { java.time.LocalDateTime.class }
)
public interface SiteVisitInfrastructureDetailsMapper {

    // CREATE
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "application", source = "application")
    @Mapping(target = "createdBy", source = "valuator")
    @Mapping(target = "createdDate", expression = "java(LocalDateTime.now())")
    @Mapping(target = "updatedBy", ignore = true)
    @Mapping(target = "updatedDate", ignore = true)
    SiteVisitInfrastructureDetails toEntity(
            SiteVisitInfrastructureDetailsRequest request,
            LoanApplication application,
            AdminUser valuator
    );

    // UPDATE
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "application", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "updatedBy", ignore = true)
    @Mapping(target = "updatedDate", expression = "java(LocalDateTime.now())")
    void updateEntity(
            SiteVisitInfrastructureDetailsRequest request,
            @MappingTarget SiteVisitInfrastructureDetails entity
    );

    // RESPONSE
    @Mapping(target = "applicationId", source = "application.id")
    SiteVisitInfrastructureDetailsResponse toResponse(SiteVisitInfrastructureDetails entity);
}
