package com.bankbroker.loanapp.service;

import com.bankbroker.loanapp.dto.application.LoanApplicationResponse;
import com.bankbroker.loanapp.dto.customer.CustomerRequest;
import com.bankbroker.loanapp.dto.customer.CustomerResponse;

import java.util.List;

public interface CustomerService {
    CustomerResponse createCustomer(CustomerRequest request);
    CustomerResponse getCustomerById(String id);
    List<CustomerResponse> getAllCustomers();
    CustomerResponse updateCustomer(String id, CustomerRequest request);
    void deleteCustomer(String id);

    List<LoanApplicationResponse> getMyApplications();
}
