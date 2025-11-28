package com.bankbroker.loanapp.controller;

import com.bankbroker.loanapp.controller.api.CustomerControllerApi;
import com.bankbroker.loanapp.dto.customer.CustomerRequest;
import com.bankbroker.loanapp.dto.customer.CustomerResponse;
import com.bankbroker.loanapp.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CustomerController implements CustomerControllerApi {

    private final CustomerService customerService;

    @Override
    public ResponseEntity<CustomerResponse> createCustomer(CustomerRequest request) {
        return ResponseEntity.ok(customerService.createCustomer(request));
    }

    @Override
    public ResponseEntity<CustomerResponse> getCustomerById(String id) {
        return ResponseEntity.ok(customerService.getCustomerById(id));
    }

    @Override
    public ResponseEntity<List<CustomerResponse>> getAllCustomers() {
        return ResponseEntity.ok(customerService.getAllCustomers());
    }

    @Override
    public ResponseEntity<CustomerResponse> updateCustomer(String id, CustomerRequest request) {
        return ResponseEntity.ok(customerService.updateCustomer(id, request));
    }

    @Override
    public ResponseEntity<Void> deleteCustomer(String id) {
        customerService.deleteCustomer(id);
        return ResponseEntity.noContent().build();
    }
}
