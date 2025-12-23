package com.bankbroker.loanapp.mapper.site_visit;

import com.bankbroker.loanapp.dto.site_visit.SiteVisitPropertyValueAssessmentAmenityItemRequest;
import com.bankbroker.loanapp.dto.site_visit.SiteVisitPropertyValueAssessmentAmenityItemResponse;
import com.bankbroker.loanapp.entity.site_visit.SiteVisitPropertyValueAssessmentAmenityItem;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface SiteVisitPropertyValueAssessmentAmenityItemMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "amenities", ignore = true)
    SiteVisitPropertyValueAssessmentAmenityItem toEntity(
            SiteVisitPropertyValueAssessmentAmenityItemRequest request
    );

    SiteVisitPropertyValueAssessmentAmenityItemResponse toResponse(
            SiteVisitPropertyValueAssessmentAmenityItem entity
    );
}
