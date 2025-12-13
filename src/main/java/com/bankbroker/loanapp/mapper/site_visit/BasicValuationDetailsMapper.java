package com.bankbroker.loanapp.mapper.site_visit;

import com.bankbroker.loanapp.dto.site_visit.BasicValuationDetailsRequest;
import com.bankbroker.loanapp.dto.site_visit.BasicValuationDetailsResponse;
import com.bankbroker.loanapp.entity.AdminUser;
import com.bankbroker.loanapp.entity.LoanApplication;
import com.bankbroker.loanapp.entity.site_visit.BasicValuationDetails;
import org.mapstruct.*;

import java.time.LocalDateTime;

@Mapper(
        componentModel = "spring",
        imports = { LocalDateTime.class }
)
public interface BasicValuationDetailsMapper {

    // ---------- CREATE ----------
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "application", source = "application")
    @Mapping(target = "createdBy", source = "valuator")
    @Mapping(target = "updatedBy", ignore = true)
    @Mapping(target = "createdDate", expression = "java(LocalDateTime.now())")
    @Mapping(target = "updatedDate", ignore = true)
    BasicValuationDetails toEntity(
            BasicValuationDetailsRequest request,
            LoanApplication application,
            AdminUser valuator
    );

    // ---------- UPDATE ----------
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "application", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "updatedBy", ignore = true)
    @Mapping(target = "updatedDate", expression = "java(LocalDateTime.now())")
    void updateEntity(
            BasicValuationDetailsRequest request,
            @MappingTarget BasicValuationDetails entity
    );

    // ---------- RESPONSE ----------
    @Mapping(target = "applicationId", source = "application.id")
    BasicValuationDetailsResponse toResponse(BasicValuationDetails entity);
}
