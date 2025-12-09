package com.bankbroker.loanapp.controller.api;

import com.bankbroker.loanapp.dto.valuator.ValuatorRequest;
import com.bankbroker.loanapp.dto.valuator.ValuatorResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/agency/valuators")
public interface ValuatorMasterControllerApi {

    // 1️⃣ CREATE VALUATOR
    @PostMapping
    ResponseEntity<ValuatorResponse> createValuator(
            @RequestBody ValuatorRequest request
    );

    // 2️⃣ UPDATE VALUATOR
    @PutMapping("/{valuatorId}")
    ResponseEntity<ValuatorResponse> updateValuator(
            @PathVariable Long valuatorId,
            @RequestBody ValuatorRequest request
    );

    // 3️⃣ GET SINGLE VALUATOR
    @GetMapping("/{valuatorId}")
    ResponseEntity<ValuatorResponse> getValuator(
            @PathVariable Long valuatorId
    );

    // 4️⃣ GET ALL VALUATORS FOR AGENCY
    @GetMapping
    ResponseEntity<List<ValuatorResponse>> getAllValuators();

    // 5️⃣ DELETE VALUATOR
    @DeleteMapping("/{valuatorId}")
    ResponseEntity<Void> deleteValuator(@PathVariable Long valuatorId);
}
