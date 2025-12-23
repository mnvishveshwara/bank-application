package com.bankbroker.loanapp.mapper.site_visit;

import com.bankbroker.loanapp.dto.site_visit.SiteVisitTechnicalSbuaRequest;
import com.bankbroker.loanapp.dto.site_visit.SiteVisitTechnicalSbuaResponse;
import com.bankbroker.loanapp.entity.core.AdminUser;
import com.bankbroker.loanapp.entity.core.LoanApplication;
import com.bankbroker.loanapp.entity.site_visit.SiteVisitTechnicalSbua;
import org.mapstruct.*;

import java.time.LocalDateTime;

@Mapper(componentModel = "spring", imports = LocalDateTime.class)
public interface SiteVisitTechnicalSbuaMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "application", source = "application")
    @Mapping(target = "createdBy", source = "user")
    @Mapping(target = "createdDate", expression = "java(LocalDateTime.now())")
    @Mapping(target = "updatedBy", ignore = true)
    @Mapping(target = "updatedDate", ignore = true)
    SiteVisitTechnicalSbua toEntity(
            SiteVisitTechnicalSbuaRequest request,
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
            SiteVisitTechnicalSbuaRequest request,
            @MappingTarget SiteVisitTechnicalSbua entity
    );

    @Mapping(target = "applicationId", source = "application.id")
    @Mapping(
            target = "match",
            expression = "java(" +
                    "entity.getSbuaAsPerActual() != null && " +
                    "entity.getSbuaAsPerDocument() != null && " +
                    "entity.getSbuaAsPerApprovedPlan() != null && " +
                    "entity.getSbuaAsPerActual().equals(entity.getSbuaAsPerDocument()) && " +
                    "entity.getSbuaAsPerActual().equals(entity.getSbuaAsPerApprovedPlan())" +
                    ")"
    )
    SiteVisitTechnicalSbuaResponse toResponse(
            SiteVisitTechnicalSbua entity
    );
}
