package com.bankbroker.loanapp.mapper.core;

import com.bankbroker.loanapp.dto.stage.ApplicationCustomerDetailsRequest;
import com.bankbroker.loanapp.dto.stage.ApplicationCustomerDetailsResponse;
import com.bankbroker.loanapp.entity.stage.ApplicationCustomerDetails;
import org.mapstruct.*;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ApplicationCustomerDetailsMapper {

    //   Entity → Response
    @Mapping(source = "application.id", target = "applicationId")

    // Bank Mapping
    @Mapping(source = "bankId", target = "bankId")
    @Mapping(source = "bank.bankName", target = "bankName")

    ApplicationCustomerDetailsResponse toResponse(ApplicationCustomerDetails entity);


    //   Request → Entity
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "application", ignore = true)
    @Mapping(target = "bank", ignore = true) // important because bank is derived from bankId
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "updatedDate", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "updatedBy", ignore = true)
    ApplicationCustomerDetails toEntity(ApplicationCustomerDetailsRequest request);


    //   Update Existing Entity
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "application", ignore = true)
    @Mapping(target = "bank", ignore = true)
    void updateEntityFromRequest(ApplicationCustomerDetailsRequest request,
                                 @MappingTarget ApplicationCustomerDetails entity);
}
