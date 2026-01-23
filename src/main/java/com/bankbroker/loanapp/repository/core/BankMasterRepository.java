package com.bankbroker.loanapp.repository.core;

import com.bankbroker.loanapp.entity.core.BankMaster;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BankMasterRepository extends JpaRepository<BankMaster, Long> {

    Optional<BankMaster> findByBankCode(String bankCode);

    List<BankMaster> findByIsActiveTrue();
}