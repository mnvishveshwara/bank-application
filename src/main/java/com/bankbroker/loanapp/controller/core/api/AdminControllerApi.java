package com.bankbroker.loanapp.controller.core.api;

import com.bankbroker.loanapp.dto.admin.AdminRequest;
import com.bankbroker.loanapp.dto.admin.AdminResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/admins")
public interface AdminControllerApi {

    @PostMapping
    ResponseEntity<AdminResponse> createAdmin(@RequestBody AdminRequest request);

    @GetMapping("/{id}")
    ResponseEntity<AdminResponse> getAdminById(@PathVariable String id);

    @GetMapping
    ResponseEntity<List<AdminResponse>> getAllAdmins();

    @PutMapping("/{id}")
    ResponseEntity<AdminResponse> updateAdmin(@PathVariable String id,
                                              @RequestBody AdminRequest request);

    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteAdmin(@PathVariable String id);
}
