package com.bankbroker.loanapp.mapper.site_visit;

import com.bankbroker.loanapp.dto.site_visit.SiteVisitTechnicalBuaResponse;
import com.bankbroker.loanapp.entity.site_visit.SiteVisitTechnicalBua;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface SiteVisitTechnicalBuaMapper {

    @Mapping(target = "applicationId", source = "application.id")
    @Mapping(target = "totalMatch",
            expression = "java(" +
                    "entity.getTotalBuaActual() != null && " +
                    "entity.getTotalBuaDocument() != null && " +
                    "entity.getTotalBuaApproved() != null && " +
                    "entity.getTotalBuaActual().equals(entity.getTotalBuaDocument()) && " +
                    "entity.getTotalBuaActual().equals(entity.getTotalBuaApproved())" +
                    ")")
    SiteVisitTechnicalBuaResponse toResponse(SiteVisitTechnicalBua entity);
}
