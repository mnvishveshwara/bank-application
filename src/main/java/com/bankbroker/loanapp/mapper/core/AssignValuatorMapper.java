package com.bankbroker.loanapp.mapper.core;

import com.bankbroker.loanapp.dto.valuator.AssignValuatorRequest;
import com.bankbroker.loanapp.dto.valuator.AssignValuatorResponse;
import com.bankbroker.loanapp.entity.core.AdminUser;
import com.bankbroker.loanapp.entity.core.LoanApplication;
import com.bankbroker.loanapp.entity.valuator.AssignValuator;
import org.mapstruct.*;

import java.time.LocalDateTime;

@Mapper(componentModel = "spring", imports = {LocalDateTime.class})
public interface AssignValuatorMapper {

    // CREATE ENTITY
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "application", source = "application")
    @Mapping(target = "valuator", source = "valuator")
    @Mapping(target = "assignedBy", source = "assignedBy")
    @Mapping(target = "assignedDate", expression = "java(LocalDateTime.now())")
    @Mapping(target = "updatedDate", expression = "java(LocalDateTime.now())")
    AssignValuator toEntity(
            AssignValuatorRequest request,
            LoanApplication application,
            AdminUser valuator,
            AdminUser assignedBy
    );

    // UPDATE ENTITY — UPDATE ONLY REMARKS
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "remarks", source = "request.remarks")
    @Mapping(target = "updatedDate", expression = "java(LocalDateTime.now())")
    void updateEntity(
            AssignValuatorRequest request,
            @MappingTarget AssignValuator entity
    );

    // ENTITY → RESPONSE
    @Mapping(target = "applicationId", source = "application.id")
    @Mapping(target = "valuatorId", source = "valuator.id")
    @Mapping(target = "valuatorName",
            expression = "java(entity.getValuator().getFirstName() + \" \" + entity.getValuator().getLastName())")
    @Mapping(target = "assignedBy", source = "assignedBy.id")
    AssignValuatorResponse toResponse(AssignValuator entity);
}