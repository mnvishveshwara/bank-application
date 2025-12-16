package com.bankbroker.loanapp.mapper.site_visit;

import com.bankbroker.loanapp.dto.site_visit.SiteVisitTechnicalDetailsRequest;
import com.bankbroker.loanapp.dto.site_visit.SiteVisitTechnicalDetailsResponse;
import com.bankbroker.loanapp.entity.core.AdminUser;
import com.bankbroker.loanapp.entity.core.LoanApplication;
import com.bankbroker.loanapp.entity.site_visit.SiteVisitTechnicalDetails;
import org.mapstruct.*;

@Mapper(componentModel = "spring", imports = {java.time.LocalDateTime.class})
public interface SiteVisitTechnicalDetailsMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "application", source = "application")
    @Mapping(target = "createdBy", source = "valuator")
    @Mapping(target = "createdDate", expression = "java(LocalDateTime.now())")
    @Mapping(target = "updatedBy", ignore = true)
    @Mapping(target = "updatedDate", ignore = true)
    SiteVisitTechnicalDetails toEntity(
            SiteVisitTechnicalDetailsRequest request,
            LoanApplication application,
            AdminUser valuator
    );

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "application", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "updatedBy", ignore = true)
    @Mapping(target = "updatedDate", expression = "java(LocalDateTime.now())")
    void updateEntity(
            SiteVisitTechnicalDetailsRequest request,
            @MappingTarget SiteVisitTechnicalDetails entity
    );

    @Mapping(target = "applicationId", source = "application.id")
    SiteVisitTechnicalDetailsResponse toResponse(SiteVisitTechnicalDetails entity);
}
