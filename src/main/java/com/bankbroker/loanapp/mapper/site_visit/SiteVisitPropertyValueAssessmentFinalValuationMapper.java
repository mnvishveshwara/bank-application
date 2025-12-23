package com.bankbroker.loanapp.mapper.site_visit;

import com.bankbroker.loanapp.dto.site_visit.SiteVisitPropertyValueAssessmentFinalValuationRequest;
import com.bankbroker.loanapp.dto.site_visit.SiteVisitPropertyValueAssessmentFinalValuationResponse;
import com.bankbroker.loanapp.entity.core.AdminUser;
import com.bankbroker.loanapp.entity.core.LoanApplication;
import com.bankbroker.loanapp.entity.site_visit.SiteVisitPropertyValueAssessmentFinalValuation;
import org.mapstruct.*;

import java.time.LocalDateTime;

@Mapper(componentModel = "spring", imports = LocalDateTime.class)
public interface SiteVisitPropertyValueAssessmentFinalValuationMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "application", source = "application")
    @Mapping(target = "createdBy", source = "user")
    @Mapping(target = "createdDate", expression = "java(LocalDateTime.now())")
    @Mapping(target = "updatedBy", ignore = true)
    @Mapping(target = "updatedDate", ignore = true)
    SiteVisitPropertyValueAssessmentFinalValuation toEntity(
            SiteVisitPropertyValueAssessmentFinalValuationRequest request,
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
            SiteVisitPropertyValueAssessmentFinalValuationRequest request,
            @MappingTarget SiteVisitPropertyValueAssessmentFinalValuation entity
    );

    @Mapping(target = "applicationId", source = "application.id")
    SiteVisitPropertyValueAssessmentFinalValuationResponse toResponse(
            SiteVisitPropertyValueAssessmentFinalValuation entity
    );
}
