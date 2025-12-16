package com.bankbroker.loanapp.mapper.core;

import com.bankbroker.loanapp.dto.valuator.ValuatorRequest;
import com.bankbroker.loanapp.dto.valuator.ValuatorResponse;
import com.bankbroker.loanapp.entity.core.AdminUser;
import com.bankbroker.loanapp.entity.core.AgencyMaster;
import com.bankbroker.loanapp.entity.valuator.ValuatorMaster;
import org.springframework.stereotype.Component;

@Component
public class ValuatorMapper {

    // 1️⃣ Convert Request → Entity (for Creation)
    public ValuatorMaster toEntity(ValuatorRequest request, AgencyMaster agency, AdminUser creator, AdminUser loginUser) {
        return ValuatorMaster.builder()
                .valuatorName(request.getName())
                .valuatorLastName(request.getLastName())
                .contactNumber(request.getPhone())
                .email(request.getEmail())
                .agency(agency)
                .createdBy(creator)
                .updatedBy(creator)
                .loginAccount(loginUser)
                .build();
    }

    // 2️⃣ Update Existing Entity
    public void updateEntity(ValuatorRequest request, ValuatorMaster entity, AdminUser updater) {
        entity.setValuatorName(request.getName());
        entity.setContactNumber(request.getPhone());
        entity.setEmail(request.getEmail());
        entity.setUpdatedBy(updater);
    }

    // 3️⃣ Convert Entity → Response DTO
    public ValuatorResponse toResponse(ValuatorMaster v) {
        return ValuatorResponse.builder()
                .id(v.getId())
                .valuatorName(v.getValuatorName())
                .contactNumber(v.getContactNumber())
                .email(v.getEmail())
                .agencyId(v.getAgency().getId())
                .agencyName(v.getAgency().getAgencyName())
                .loginUserId(v.getLoginAccount().getId())
                .build();
    }
}
