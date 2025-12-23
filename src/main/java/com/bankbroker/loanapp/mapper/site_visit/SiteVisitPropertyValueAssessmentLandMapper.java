package com.bankbroker.loanapp.mapper.site_visit;


import com.bankbroker.loanapp.dto.site_visit.SiteVisitPropertyValueAssessmentLandRequest;
import com.bankbroker.loanapp.dto.site_visit.SiteVisitPropertyValueAssessmentLandResponse;
import com.bankbroker.loanapp.entity.core.AdminUser;
import com.bankbroker.loanapp.entity.core.LoanApplication;
import com.bankbroker.loanapp.entity.site_visit.SiteVisitPropertyValueAssessmentLand;
import org.mapstruct.*;

import java.time.LocalDateTime;

@Mapper(componentModel = "spring", imports = LocalDateTime.class)
public interface SiteVisitPropertyValueAssessmentLandMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "application", source = "application")
    @Mapping(target = "createdBy", source = "user")
    @Mapping(target = "createdDate", expression = "java(LocalDateTime.now())")
    @Mapping(target = "updatedBy", ignore = true)
    @Mapping(target = "updatedDate", ignore = true)
    @Mapping(target = "govtTotalValueActual", ignore = true)
    @Mapping(target = "govtTotalValueDocument", ignore = true)
    @Mapping(target = "govtTotalValueLayout", ignore = true)
    @Mapping(target = "considerationTotalValueActual", ignore = true)
    @Mapping(target = "considerationTotalValueDocument", ignore = true)
    @Mapping(target = "considerationTotalValueLayout", ignore = true)
    SiteVisitPropertyValueAssessmentLand toEntity(
            SiteVisitPropertyValueAssessmentLandRequest request,
            LoanApplication application,
            AdminUser user
    );

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "application", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "updatedBy", ignore = true)
    @Mapping(target = "updatedDate", expression = "java(LocalDateTime.now())")
    @Mapping(target = "govtTotalValueActual", ignore = true)
    @Mapping(target = "govtTotalValueDocument", ignore = true)
    @Mapping(target = "govtTotalValueLayout", ignore = true)
    @Mapping(target = "considerationTotalValueActual", ignore = true)
    @Mapping(target = "considerationTotalValueDocument", ignore = true)
    @Mapping(target = "considerationTotalValueLayout", ignore = true)
    void updateEntity(
            SiteVisitPropertyValueAssessmentLandRequest request,
            @MappingTarget SiteVisitPropertyValueAssessmentLand entity
    );

    @Mapping(target = "applicationId", source = "application.id")
    SiteVisitPropertyValueAssessmentLandResponse toResponse(
            SiteVisitPropertyValueAssessmentLand entity
    );
}