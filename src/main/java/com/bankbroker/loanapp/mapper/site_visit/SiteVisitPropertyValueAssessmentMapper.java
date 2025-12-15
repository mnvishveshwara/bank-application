package com.bankbroker.loanapp.mapper.site_visit;

import com.bankbroker.loanapp.dto.site_visit.SiteVisitPropertyValueAssessmentRequest;
import com.bankbroker.loanapp.dto.site_visit.SiteVisitPropertyValueAssessmentResponse;
import com.bankbroker.loanapp.entity.site_visit.SiteVisitPropertyValueAssessment;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface SiteVisitPropertyValueAssessmentMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "application", ignore = true)
    SiteVisitPropertyValueAssessment toEntity(
            SiteVisitPropertyValueAssessmentRequest request
    );

    @Mapping(source = "application.id", target = "applicationId")
    SiteVisitPropertyValueAssessmentResponse toResponse(
            SiteVisitPropertyValueAssessment entity
    );

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntity(
            SiteVisitPropertyValueAssessmentRequest request,
            @MappingTarget SiteVisitPropertyValueAssessment entity
    );
}
