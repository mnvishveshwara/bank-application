package com.bankbroker.loanapp.mapper.core;

import com.bankbroker.loanapp.dto.admin.BankMasterRequest;
import com.bankbroker.loanapp.dto.admin.BankMasterResponse;
import com.bankbroker.loanapp.entity.core.BankMaster;
import org.mapstruct.*;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface BankMasterMapper {

    // ===== CREATE =====
    BankMaster toEntity(BankMasterRequest request);

    // ===== RESPONSE =====
    BankMasterResponse toResponse(BankMaster entity);

    // ===== UPDATE (IMPORTANT) =====
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromRequest(
            BankMasterRequest request,
            @MappingTarget BankMaster entity);
}
