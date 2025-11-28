package com.bankbroker.loanapp.mapper;

import com.bankbroker.loanapp.dto.stage.ApplicationCustomerDetailsRequest;
import com.bankbroker.loanapp.dto.stage.ApplicationCustomerDetailsResponse;
import com.bankbroker.loanapp.entity.stage.ApplicationCustomerDetails;
import org.mapstruct.*;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ApplicationCustomerDetailsMapper {

    @Mapping(source = "application.id", target = "applicationId")
    ApplicationCustomerDetailsResponse toResponse(ApplicationCustomerDetails entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "application", ignore = true)
    ApplicationCustomerDetails toEntity(ApplicationCustomerDetailsRequest request);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromRequest(ApplicationCustomerDetailsRequest request,
                                 @MappingTarget ApplicationCustomerDetails entity);
}
