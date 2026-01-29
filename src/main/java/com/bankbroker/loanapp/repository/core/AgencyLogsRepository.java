package com.bankbroker.loanapp.repository.core;

import com.bankbroker.loanapp.dto.master.AgencyApplicationLogResponse;
import com.bankbroker.loanapp.entity.core.ApplicationStageHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface AgencyLogsRepository extends JpaRepository<ApplicationStageHistory, Long> {

    @Query("""
        SELECT new com.bankbroker.loanapp.dto.master.AgencyApplicationLogResponse(
            app.id,
            bank.bankName,
            hist.status,
            COALESCE(CONCAT(val.firstName,' ',val.lastName), 'Not Assigned'),
            app.plannedSiteVisitDate,
            app.updatedDate
        )
        FROM ApplicationAgencyAssignment assign
        JOIN assign.application app
        JOIN app.bank bank
        LEFT JOIN app.valuator val

        LEFT JOIN ApplicationStageHistory hist
            ON hist.application.id = app.id

        WHERE assign.agency.id = :agencyId
          AND hist.createdDate = (
              SELECT MAX(h2.createdDate)
              FROM ApplicationStageHistory h2
              WHERE h2.application.id = app.id
          )

        ORDER BY app.updatedDate DESC
    """)
    List<AgencyApplicationLogResponse> getAgencyApplicationLogs(@Param("agencyId") Long agencyId);
}