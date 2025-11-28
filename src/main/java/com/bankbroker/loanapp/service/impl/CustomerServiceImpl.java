package com.bankbroker.loanapp.service.impl;

import com.bankbroker.loanapp.dto.customer.CustomerRequest;
import com.bankbroker.loanapp.dto.customer.CustomerResponse;
import com.bankbroker.loanapp.entity.Customer;
import com.bankbroker.loanapp.exception.ResourceNotFoundException;
import com.bankbroker.loanapp.mapper.CustomerMapper;
import com.bankbroker.loanapp.repository.CustomerRepository;
import com.bankbroker.loanapp.service.CustomerService;
import com.bankbroker.loanapp.util.IdGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder;

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
}
