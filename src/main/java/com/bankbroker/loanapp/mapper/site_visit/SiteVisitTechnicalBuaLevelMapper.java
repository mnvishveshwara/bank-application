package com.bankbroker.loanapp.mapper.site_visit;

import com.bankbroker.loanapp.dto.site_visit.SiteVisitTechnicalBuaLevelRequest;
import com.bankbroker.loanapp.dto.site_visit.SiteVisitTechnicalBuaLevelResponse;
import com.bankbroker.loanapp.entity.site_visit.SiteVisitTechnicalBuaLevel;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface SiteVisitTechnicalBuaLevelMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "bua", ignore = true)
    SiteVisitTechnicalBuaLevel toEntity(
            SiteVisitTechnicalBuaLevelRequest request
    );

    @Mapping(target = "match",
            expression = "java(" +
                    "entity.getAreaActual() != null && " +
                    "entity.getAreaDocument() != null && " +
                    "entity.getAreaApproved() != null && " +
                    "entity.getAreaActual().equals(entity.getAreaDocument()) && " +
                    "entity.getAreaActual().equals(entity.getAreaApproved())" +
                    ")")
    SiteVisitTechnicalBuaLevelResponse toResponse(
            SiteVisitTechnicalBuaLevel entity
    );
}
