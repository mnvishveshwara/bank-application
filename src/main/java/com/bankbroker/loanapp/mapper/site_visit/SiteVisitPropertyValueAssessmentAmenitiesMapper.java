package com.bankbroker.loanapp.mapper.site_visit;

import com.bankbroker.loanapp.dto.site_visit.SiteVisitPropertyValueAssessmentAmenitiesResponse;
import com.bankbroker.loanapp.entity.site_visit.SiteVisitPropertyValueAssessmentAmenities;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface SiteVisitPropertyValueAssessmentAmenitiesMapper {

    @Mapping(target = "applicationId", source = "application.id")
    SiteVisitPropertyValueAssessmentAmenitiesResponse toResponse(
            SiteVisitPropertyValueAssessmentAmenities entity
    );
}
