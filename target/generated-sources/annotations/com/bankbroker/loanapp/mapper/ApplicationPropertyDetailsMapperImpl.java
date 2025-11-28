package com.bankbroker.loanapp.mapper;

import com.bankbroker.loanapp.dto.stage.ApplicationPropertyDetailsRequest;
import com.bankbroker.loanapp.dto.stage.ApplicationPropertyDetailsResponse;
import com.bankbroker.loanapp.dto.stage.PropertyAddressDTO;
import com.bankbroker.loanapp.dto.stage.ResidentialAddressDTO;
import com.bankbroker.loanapp.entity.LoanApplication;
import com.bankbroker.loanapp.entity.stage.ApplicationPropertyAddress;
import com.bankbroker.loanapp.entity.stage.ApplicationPropertyDetails;
import com.bankbroker.loanapp.entity.stage.ApplicationResidentialAddress;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-11-28T17:37:58+0530",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.8 (Oracle Corporation)"
)
@Component
public class ApplicationPropertyDetailsMapperImpl implements ApplicationPropertyDetailsMapper {

    @Override
    public ApplicationPropertyDetailsResponse toResponse(ApplicationPropertyDetails entity) {
        if ( entity == null ) {
            return null;
        }

        ApplicationPropertyDetailsResponse.ApplicationPropertyDetailsResponseBuilder applicationPropertyDetailsResponse = ApplicationPropertyDetailsResponse.builder();

        applicationPropertyDetailsResponse.applicationId( entityApplicationId( entity ) );
        applicationPropertyDetailsResponse.id( entity.getId() );
        applicationPropertyDetailsResponse.residentialAddress( toResidentialAddressDTO( entity.getResidentialAddress() ) );
        applicationPropertyDetailsResponse.propertyAddress( toPropertyAddressDTO( entity.getPropertyAddress() ) );

        return applicationPropertyDetailsResponse.build();
    }

    @Override
    public ResidentialAddressDTO toResidentialAddressDTO(ApplicationResidentialAddress entity) {
        if ( entity == null ) {
            return null;
        }

        ResidentialAddressDTO.ResidentialAddressDTOBuilder residentialAddressDTO = ResidentialAddressDTO.builder();

        residentialAddressDTO.doorOrApartmentNumber( entity.getDoorOrApartmentNumber() );
        residentialAddressDTO.buildingOrApartmentName( entity.getBuildingOrApartmentName() );
        residentialAddressDTO.streetLine1( entity.getStreetLine1() );
        residentialAddressDTO.streetLine2( entity.getStreetLine2() );
        residentialAddressDTO.pinCode( entity.getPinCode() );
        residentialAddressDTO.city( entity.getCity() );
        residentialAddressDTO.state( entity.getState() );
        residentialAddressDTO.country( entity.getCountry() );

        return residentialAddressDTO.build();
    }

    @Override
    public PropertyAddressDTO toPropertyAddressDTO(ApplicationPropertyAddress entity) {
        if ( entity == null ) {
            return null;
        }

        PropertyAddressDTO.PropertyAddressDTOBuilder propertyAddressDTO = PropertyAddressDTO.builder();

        propertyAddressDTO.doorOrApartmentNumber( entity.getDoorOrApartmentNumber() );
        propertyAddressDTO.buildingOrApartmentName( entity.getBuildingOrApartmentName() );
        propertyAddressDTO.streetLine1( entity.getStreetLine1() );
        propertyAddressDTO.streetLine2( entity.getStreetLine2() );
        propertyAddressDTO.pinCode( entity.getPinCode() );
        propertyAddressDTO.city( entity.getCity() );
        propertyAddressDTO.state( entity.getState() );
        propertyAddressDTO.country( entity.getCountry() );

        return propertyAddressDTO.build();
    }

    @Override
    public ApplicationPropertyDetails toEntity(ApplicationPropertyDetailsRequest request) {
        if ( request == null ) {
            return null;
        }

        ApplicationPropertyDetails.ApplicationPropertyDetailsBuilder applicationPropertyDetails = ApplicationPropertyDetails.builder();

        applicationPropertyDetails.residentialAddress( toResidentialAddress( request.getResidentialAddress() ) );
        applicationPropertyDetails.propertyAddress( toPropertyAddress( request.getPropertyAddress() ) );

        return applicationPropertyDetails.build();
    }

    @Override
    public ApplicationResidentialAddress toResidentialAddress(ResidentialAddressDTO dto) {
        if ( dto == null ) {
            return null;
        }

        ApplicationResidentialAddress.ApplicationResidentialAddressBuilder applicationResidentialAddress = ApplicationResidentialAddress.builder();

        applicationResidentialAddress.doorOrApartmentNumber( dto.getDoorOrApartmentNumber() );
        applicationResidentialAddress.buildingOrApartmentName( dto.getBuildingOrApartmentName() );
        applicationResidentialAddress.streetLine1( dto.getStreetLine1() );
        applicationResidentialAddress.streetLine2( dto.getStreetLine2() );
        applicationResidentialAddress.pinCode( dto.getPinCode() );
        applicationResidentialAddress.city( dto.getCity() );
        applicationResidentialAddress.state( dto.getState() );
        applicationResidentialAddress.country( dto.getCountry() );

        return applicationResidentialAddress.build();
    }

    @Override
    public ApplicationPropertyAddress toPropertyAddress(PropertyAddressDTO dto) {
        if ( dto == null ) {
            return null;
        }

        ApplicationPropertyAddress.ApplicationPropertyAddressBuilder applicationPropertyAddress = ApplicationPropertyAddress.builder();

        applicationPropertyAddress.doorOrApartmentNumber( dto.getDoorOrApartmentNumber() );
        applicationPropertyAddress.buildingOrApartmentName( dto.getBuildingOrApartmentName() );
        applicationPropertyAddress.streetLine1( dto.getStreetLine1() );
        applicationPropertyAddress.streetLine2( dto.getStreetLine2() );
        applicationPropertyAddress.pinCode( dto.getPinCode() );
        applicationPropertyAddress.city( dto.getCity() );
        applicationPropertyAddress.state( dto.getState() );
        applicationPropertyAddress.country( dto.getCountry() );

        return applicationPropertyAddress.build();
    }

    @Override
    public void updateEntityFromRequest(ApplicationPropertyDetailsRequest request, ApplicationPropertyDetails entity) {
        if ( request == null ) {
            return;
        }

        if ( request.getResidentialAddress() != null ) {
            entity.setResidentialAddress( toResidentialAddress( request.getResidentialAddress() ) );
        }
        if ( request.getPropertyAddress() != null ) {
            entity.setPropertyAddress( toPropertyAddress( request.getPropertyAddress() ) );
        }
    }

    private String entityApplicationId(ApplicationPropertyDetails applicationPropertyDetails) {
        if ( applicationPropertyDetails == null ) {
            return null;
        }
        LoanApplication application = applicationPropertyDetails.getApplication();
        if ( application == null ) {
            return null;
        }
        String id = application.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
