package com.bankbroker.loanapp.service.core.api;

import com.bankbroker.loanapp.dto.admin.AdminRequest;
import com.bankbroker.loanapp.dto.admin.AdminResponse;
import com.bankbroker.loanapp.dto.application.LoanApplicationResponse;

import java.util.List;

public interface AdminUserService {
    AdminResponse createAdmin(AdminRequest request);
    AdminResponse getAdminById(String id);
    List<AdminResponse> getAllAdmins();
    AdminResponse updateAdmin(String id, AdminRequest request);
    void deleteAdmin(String id);

    List<LoanApplicationResponse> getIncompleteApplication();

    List<LoanApplicationResponse> getCompleteApplication();

}
