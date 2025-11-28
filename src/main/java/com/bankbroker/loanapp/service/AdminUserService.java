package com.bankbroker.loanapp.service;

import com.bankbroker.loanapp.dto.admin.AdminRequest;
import com.bankbroker.loanapp.dto.admin.AdminResponse;

import java.util.List;

public interface AdminUserService {
    AdminResponse createAdmin(AdminRequest request);
    AdminResponse getAdminById(String id);
    List<AdminResponse> getAllAdmins();
    AdminResponse updateAdmin(String id, AdminRequest request);
    void deleteAdmin(String id);
}
