package com.bankbroker.loanapp.mapper.site_visit;

import com.bankbroker.loanapp.dto.site_visit.SiteVisitValuerRemarksRequest;
import com.bankbroker.loanapp.dto.site_visit.SiteVisitValuerRemarksResponse;
import com.bankbroker.loanapp.entity.site_visit.SiteVisitValuerRemarks;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface SiteVisitValuerRemarksMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "application", ignore = true)
    SiteVisitValuerRemarks toEntity(SiteVisitValuerRemarksRequest request);

    @Mapping(source = "application.id", target = "applicationId")
    SiteVisitValuerRemarksResponse toResponse(SiteVisitValuerRemarks entity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntity(
            SiteVisitValuerRemarksRequest request,
            @MappingTarget SiteVisitValuerRemarks entity
    );
}
