package com.bankbroker.loanapp.mapper.core;

import com.bankbroker.loanapp.dto.customer.CustomerDTO;
import com.bankbroker.loanapp.dto.customer.CustomerRequest;
import com.bankbroker.loanapp.dto.customer.CustomerResponse;
import com.bankbroker.loanapp.entity.core.Customer;
import com.bankbroker.loanapp.entity.enums.Role;

import java.time.LocalDateTime;

public class CustomerMapper {

    public static Customer toEntity(CustomerRequest request, String encodedPassword, String generatedId) {
        return Customer.builder()
                .id(generatedId)
                .email(request.getEmail())
                .password(encodedPassword)
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .phoneNumber(request.getPhoneNumber())
                .role(Role.USER)
                .bank(request.getBank())
                .createdDate(LocalDateTime.now())
                .build();
    }

    public static CustomerResponse toResponse(Customer entity) {
        return CustomerResponse.builder()
                .id(entity.getId())
                .email(entity.getEmail())
                .firstName(entity.getFirstName())
                .lastName(entity.getLastName())
                .phoneNumber(entity.getPhoneNumber())
                .bank(entity.getBank())
                .createdDate(entity.getCreatedDate())
                .build();
    }

    public static CustomerDTO toDTO(Customer entity) {
        return CustomerDTO.builder()
                .id(entity.getId())
                .email(entity.getEmail())
                .firstName(entity.getFirstName())
                .lastName(entity.getLastName())
                .phoneNumber(entity.getPhoneNumber())
                .bank(entity.getBank())
                .role(entity.getRole().name())
                .createdDate(entity.getCreatedDate())
                .build();
    }
}
