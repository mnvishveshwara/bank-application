package com.bankbroker.loanapp.controller.core.api;

import com.bankbroker.loanapp.dto.application.LoanApplicationResponse;
import com.bankbroker.loanapp.dto.customer.CustomerRequest;
import com.bankbroker.loanapp.dto.customer.CustomerResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/customers")
public interface CustomerControllerApi {

    @PostMapping
    ResponseEntity<CustomerResponse> createCustomer(@RequestBody CustomerRequest request);

    @GetMapping("/{id}")
    ResponseEntity<CustomerResponse> getCustomerById(@PathVariable String id);

    @GetMapping
    ResponseEntity<List<CustomerResponse>> getAllCustomers();

    @PutMapping("/{id}")
    ResponseEntity<CustomerResponse> updateCustomer(@PathVariable String id,
                                                    @RequestBody CustomerRequest request);

    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteCustomer(@PathVariable String id);

    @GetMapping("/myApplications")
    ResponseEntity<List<LoanApplicationResponse>> getMyApplications();
}
