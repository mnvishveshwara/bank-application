package com.bankbroker.loanapp.mapper.site_visit;

import com.bankbroker.loanapp.dto.site_visit.SiteVisitTechnicalPlotRequest;
import com.bankbroker.loanapp.dto.site_visit.SiteVisitTechnicalPlotResponse;
import com.bankbroker.loanapp.entity.core.AdminUser;
import com.bankbroker.loanapp.entity.core.LoanApplication;
import com.bankbroker.loanapp.entity.site_visit.SiteVisitTechnicalPlot;
import org.mapstruct.*;

import java.time.LocalDateTime;

@Mapper(componentModel = "spring", imports = LocalDateTime.class)
public interface SiteVisitTechnicalPlotMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "application", source = "application")
    @Mapping(target = "createdBy", source = "createdBy")
    @Mapping(target = "createdDate", expression = "java(LocalDateTime.now())")
    @Mapping(target = "updatedBy", ignore = true)
    @Mapping(target = "updatedDate", ignore = true)
    @Mapping(target = "eastMatch", ignore = true)
    @Mapping(target = "westMatch", ignore = true)
    @Mapping(target = "northMatch", ignore = true)
    @Mapping(target = "southMatch", ignore = true)
    SiteVisitTechnicalPlot toEntity(
            SiteVisitTechnicalPlotRequest request,
            LoanApplication application,
            AdminUser createdBy
    );

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "application", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "updatedBy", ignore = true)
    @Mapping(target = "updatedDate", expression = "java(LocalDateTime.now())")
    @Mapping(target = "eastMatch", ignore = true)
    @Mapping(target = "westMatch", ignore = true)
    @Mapping(target = "northMatch", ignore = true)
    @Mapping(target = "southMatch", ignore = true)
    void updateEntity(
            SiteVisitTechnicalPlotRequest request,
            @MappingTarget SiteVisitTechnicalPlot entity
    );

    @Mapping(target = "applicationId", source = "application.id")
    SiteVisitTechnicalPlotResponse toResponse(SiteVisitTechnicalPlot entity);
}
