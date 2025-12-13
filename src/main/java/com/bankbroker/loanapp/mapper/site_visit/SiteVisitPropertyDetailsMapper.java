package com.bankbroker.loanapp.mapper.site_visit;

import com.bankbroker.loanapp.dto.site_visit.SiteVisitPropertyDetailsRequest;
import com.bankbroker.loanapp.dto.site_visit.SiteVisitPropertyDetailsResponse;
import com.bankbroker.loanapp.entity.AdminUser;
import com.bankbroker.loanapp.entity.LoanApplication;
import com.bankbroker.loanapp.entity.site_visit.SiteVisitPropertyDetails;
import org.mapstruct.*;

@Mapper(componentModel = "spring", imports = {java.time.LocalDateTime.class})
public interface SiteVisitPropertyDetailsMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "application", source = "application")
    @Mapping(target = "createdBy", source = "user")
    @Mapping(target = "createdDate", expression = "java(LocalDateTime.now())")
    @Mapping(target = "updatedBy", ignore = true)
    @Mapping(target = "updatedDate", ignore = true)
    SiteVisitPropertyDetails toEntity(
            SiteVisitPropertyDetailsRequest request,
            LoanApplication application,
            AdminUser user
    );

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "application", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "updatedBy", ignore = true)
    @Mapping(target = "updatedDate", expression = "java(LocalDateTime.now())")
    void updateEntity(
            SiteVisitPropertyDetailsRequest request,
            @MappingTarget SiteVisitPropertyDetails entity
    );

    @Mapping(target = "applicationId", source = "application.id")
    SiteVisitPropertyDetailsResponse toResponse(SiteVisitPropertyDetails entity);
}

