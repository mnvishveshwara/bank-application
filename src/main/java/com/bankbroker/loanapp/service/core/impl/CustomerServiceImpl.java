package com.bankbroker.loanapp.service.core.impl;

import com.bankbroker.loanapp.dto.application.LoanApplicationResponse;
import com.bankbroker.loanapp.dto.customer.CustomerRequest;
import com.bankbroker.loanapp.dto.customer.CustomerResponse;
import com.bankbroker.loanapp.entity.core.Customer;
import com.bankbroker.loanapp.entity.core.LoanApplication;
import com.bankbroker.loanapp.exception.ResourceNotFoundException;
import com.bankbroker.loanapp.mapper.core.CustomerMapper;
import com.bankbroker.loanapp.repository.core.ApplicationStageHistoryRepository;
import com.bankbroker.loanapp.repository.core.CustomerRepository;
import com.bankbroker.loanapp.repository.core.LoanApplicationRepository;
import com.bankbroker.loanapp.service.core.api.CustomerService;
import com.bankbroker.loanapp.util.IdGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder;
    private final LoanApplicationRepository loanApplicationRepository;
    private final ApplicationStageHistoryRepository stageHistoryRepository;

    @Override
    public CustomerResponse createCustomer(CustomerRequest request) {
        String generatedId = IdGenerator.generateId();
        String encodedPassword = passwordEncoder.encode(request.getPassword());
        Customer customer = CustomerMapper.toEntity(request, encodedPassword, generatedId);
        customer = customerRepository.save(customer);
        return CustomerMapper.toResponse(customer);
    }

    @Override
    public CustomerResponse getCustomerById(String id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer", "id", id));
        return CustomerMapper.toResponse(customer);
    }

    @Override
    public List<CustomerResponse> getAllCustomers() {
        return customerRepository.findAll()
                .stream()
                .map(CustomerMapper::toResponse)
                .toList();
    }

    @Override
    public CustomerResponse updateCustomer(String id, CustomerRequest request) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer", "id", id));

        customer.setFirstName(request.getFirstName());
        customer.setLastName(request.getLastName());
        customer.setPhoneNumber(request.getPhoneNumber());
        customer.setBank(request.getBank());

        customer = customerRepository.save(customer);
        return CustomerMapper.toResponse(customer);
    }

    @Override
    public void deleteCustomer(String id) {
        if (!customerRepository.existsById(id)) {
            throw new ResourceNotFoundException("Customer", "id", id);
        }
        customerRepository.deleteById(id);
    }


    @Override
    public List<LoanApplicationResponse> getMyApplications() {

        String customerId = getLoggedInCustomerId();

        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid customer"));

        List<LoanApplication> applications =
                loanApplicationRepository.findByClient_Id(customer.getId());

        return applications.stream()
                .map(app -> {

                    String status = stageHistoryRepository
                            .findByApplication(app)

                            .map(h -> h.getStatus().name())
                            .orElse("NOT_STARTED");

                    return LoanApplicationResponse.builder()
                            .applicationId(app.getId())
                            .active(app.getActive())

                            // Client details
                            .clientId(customer.getId())
                            .clientName(customer.getFirstName())

                            // Admin-related fields (hidden for UI)
                            .createdByAdminId(null)
                            .createdByName(null)
                            .assignedToAdminId(null)
                            .assignedToName(null)

                            .associatedBank(app.getAssociatedBank())
                            .createdDate(app.getCreatedDate())
                            .updatedDate(app.getUpdatedDate())
                            .status(status)
                            .build();
                })
                .toList();
    }

    private String getLoggedInCustomerId() {
        return SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName(); // customerId from JWT
    }
}
