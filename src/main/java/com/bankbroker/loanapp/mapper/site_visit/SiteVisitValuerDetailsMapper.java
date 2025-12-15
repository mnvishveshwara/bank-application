package com.bankbroker.loanapp.mapper.site_visit;

import com.bankbroker.loanapp.dto.site_visit.SiteVisitValuerDetailsResponse;
import com.bankbroker.loanapp.entity.site_visit.SiteVisitValuerDetails;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SiteVisitValuerDetailsMapper {

    @Mapping(source = "application.id", target = "applicationId")
    SiteVisitValuerDetailsResponse toResponse(
            SiteVisitValuerDetails entity
    );
}