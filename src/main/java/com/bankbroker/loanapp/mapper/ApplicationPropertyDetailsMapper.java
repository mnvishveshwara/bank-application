package com.bankbroker.loanapp.mapper;

import com.bankbroker.loanapp.dto.stage.*;
import com.bankbroker.loanapp.entity.stage.ApplicationPropertyAddress;
import com.bankbroker.loanapp.entity.stage.ApplicationPropertyDetails;
import com.bankbroker.loanapp.entity.stage.ApplicationResidentialAddress;
import org.mapstruct.*;

//@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
//public interface ApplicationPropertyDetailsMapper {
//
//    // DTO → Entity
//    @Mapping(target = "id", ignore = true)
//    @Mapping(target = "application", ignore = true)
//    ApplicationPropertyDetails toEntity(ApplicationPropertyDetailsRequest request);
//
//    ApplicationResidentialAddress toResidentialAddress(ResidentialAddressDTO dto);
//    ApplicationPropertyAddress toPropertyAddress(PropertyAddressDTO dto);
//
//    // Entity → DTO
//    @Mapping(source = "application.id", target = "applicationId")
//    ApplicationPropertyDetailsResponse toResponse(ApplicationPropertyDetails entity);
//
//    ResidentialAddressDTO toResidentialAddressDTO(ApplicationResidentialAddress entity);
//    PropertyAddressDTO toPropertyAddressDTO(ApplicationPropertyAddress entity);
//
//    // Update existing entity
//    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
//    void updateEntityFromRequest(ApplicationPropertyDetailsRequest request,
//                                 @MappingTarget ApplicationPropertyDetails entity);
//}


@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ApplicationPropertyDetailsMapper {

    @Mapping(source = "application.id", target = "applicationId")
    ApplicationPropertyDetailsResponse toResponse(ApplicationPropertyDetails entity);

    ResidentialAddressDTO toResidentialAddressDTO(ApplicationResidentialAddress entity);
    PropertyAddressDTO toPropertyAddressDTO(ApplicationPropertyAddress entity);

    ApplicationPropertyDetails toEntity(ApplicationPropertyDetailsRequest request);

    ApplicationResidentialAddress toResidentialAddress(ResidentialAddressDTO dto);
    ApplicationPropertyAddress toPropertyAddress(PropertyAddressDTO dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromRequest(ApplicationPropertyDetailsRequest request,
                                 @MappingTarget ApplicationPropertyDetails entity);
}
