package com.bankbroker.loanapp.controller.api;


import com.bankbroker.loanapp.dto.master.AgencyMasterRequest;
import com.bankbroker.loanapp.dto.master.AgencyMasterResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/agency-master")
public interface AgencyMasterControllerApi {

    @PostMapping
    ResponseEntity<AgencyMasterResponse> createAgency(
            @RequestBody AgencyMasterRequest request
    );

    @PutMapping("/{id}")
    ResponseEntity<AgencyMasterResponse> updateAgency(
            @PathVariable Long id,
            @RequestBody AgencyMasterRequest request
    );

    @GetMapping("/{id}")
    ResponseEntity<AgencyMasterResponse> getAgency(
            @PathVariable Long id
    );

    @GetMapping
    ResponseEntity<List<AgencyMasterResponse>> getAllAgencies();

    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteAgency(
            @PathVariable Long id
    );
}
