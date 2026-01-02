package com.bankbroker.loanapp.controller.core.impl;

import com.bankbroker.loanapp.controller.core.api.AdminControllerApi;
import com.bankbroker.loanapp.dto.admin.AdminRequest;
import com.bankbroker.loanapp.dto.admin.AdminResponse;
import com.bankbroker.loanapp.dto.application.LoanApplicationResponse;
import com.bankbroker.loanapp.service.core.api.AdminUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class AdminController implements AdminControllerApi {

    private final AdminUserService adminUserService;

    @Override
    public ResponseEntity<AdminResponse> createAdmin(AdminRequest request) {
        return ResponseEntity.ok(adminUserService.createAdmin(request));
    }

    @Override
    public ResponseEntity<AdminResponse> getAdminById(String id) {
        return ResponseEntity.ok(adminUserService.getAdminById(id));
    }

    @Override
    public ResponseEntity<List<AdminResponse>> getAllAdmins() {
        return ResponseEntity.ok(adminUserService.getAllAdmins());
    }

    @Override
    public ResponseEntity<AdminResponse> updateAdmin(String id, AdminRequest request) {
        return ResponseEntity.ok(adminUserService.updateAdmin(id, request));
    }

    @Override
    public ResponseEntity<Void> deleteAdmin(String id) {
        adminUserService.deleteAdmin(id);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<List<LoanApplicationResponse>> getIncompleteApplication() {
        return ResponseEntity.ok(adminUserService.getIncompleteApplication());
    }

    @Override
    public ResponseEntity<List<LoanApplicationResponse>> getCompleteApplication() {
        return ResponseEntity.ok(adminUserService.getCompleteApplication());
    }
}
