package com.bankbroker.loanapp.repository.core;

import com.bankbroker.loanapp.dto.admin.DashboardLatestApplicationResponse;
import com.bankbroker.loanapp.dto.admin.DashboardMonthlyTrendResponse;
import com.bankbroker.loanapp.dto.admin.DashboardStatusSummaryResponse;
import com.bankbroker.loanapp.entity.core.ApplicationStageHistory;
import com.bankbroker.loanapp.entity.core.LoanApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ApplicationStageHistoryRepository extends JpaRepository<ApplicationStageHistory, Long> {
    Optional<ApplicationStageHistory> findByApplication(LoanApplication application);

    // ✅ PIE CHART STATUS COUNT (Latest stage only)
    @Query("""
        SELECT new com.bankbroker.loanapp.dto.admin.DashboardStatusSummaryResponse(
            hist.status,
            COUNT(hist)
        )
        FROM ApplicationStageHistory hist
        WHERE hist.createdDate = (
            SELECT MAX(h2.createdDate)
            FROM ApplicationStageHistory h2
            WHERE h2.application.id = hist.application.id
        )
        GROUP BY hist.status
    """)
    List<DashboardStatusSummaryResponse> getLatestStatusSummary();


    // ✅ MONTHLY TREND (Applications per day)
    @Query("""
        SELECT new com.bankbroker.loanapp.dto.admin.DashboardMonthlyTrendResponse(
            DAY(hist.createdDate),
            COUNT(hist)
        )
        FROM ApplicationStageHistory hist
        WHERE MONTH(hist.createdDate) = MONTH(CURRENT_DATE)
        GROUP BY DAY(hist.createdDate)
        ORDER BY DAY(hist.createdDate)
    """)
    List<DashboardMonthlyTrendResponse> getMonthlyTrends();


    // ✅ Latest Completed Applications
    @Query("""
        SELECT new com.bankbroker.loanapp.dto.admin.DashboardLatestApplicationResponse(
            app.id,
            CONCAT(cust.firstName,' ',cust.lastName),
            bank.bankName,
            agency.agencyName,
            hist.createdDate
        )
        FROM ApplicationStageHistory hist
        JOIN hist.application app
        JOIN app.client cust
        JOIN app.bank bank
        LEFT JOIN ApplicationAgencyAssignment assign ON assign.application.id = app.id
        LEFT JOIN assign.agency agency

        WHERE hist.status = com.bankbroker.loanapp.entity.enums.ApplicationHistoryStatus.SITE_VISIT_COMPLETED

        ORDER BY hist.createdDate DESC
    """)
    List<DashboardLatestApplicationResponse> getLatestCompleted();


    // ✅ Latest Rejected Applications
    @Query("""
        SELECT new com.bankbroker.loanapp.dto.admin.DashboardLatestApplicationResponse(
            app.id,
            CONCAT(cust.firstName,' ',cust.lastName),
            bank.bankName,
            agency.agencyName,
            hist.createdDate
        )
        FROM ApplicationStageHistory hist
        JOIN hist.application app
        JOIN app.client cust
        JOIN app.bank bank
        LEFT JOIN ApplicationAgencyAssignment assign ON assign.application.id = app.id
        LEFT JOIN assign.agency agency

        WHERE hist.status = com.bankbroker.loanapp.entity.enums.ApplicationHistoryStatus.REJECTED

        ORDER BY hist.createdDate DESC
    """)
    List<DashboardLatestApplicationResponse> getLatestRejected();


    @Query("""
    SELECT new com.bankbroker.loanapp.dto.admin.DashboardStatusSummaryResponse(
        hist.status,
        COUNT(hist)
    )
    FROM ApplicationStageHistory hist
    JOIN hist.application app
    WHERE app.bankId IN :bankIds
      AND hist.createdDate = (
          SELECT MAX(h2.createdDate)
          FROM ApplicationStageHistory h2
          WHERE h2.application.id = hist.application.id
      )
    GROUP BY hist.status
""")
    List<DashboardStatusSummaryResponse> getManagerLatestStatusSummary(
            List<Long> bankIds
    );


    @Query("""
    SELECT new com.bankbroker.loanapp.dto.admin.DashboardMonthlyTrendResponse(
        DAY(hist.createdDate),
        COUNT(hist)
    )
    FROM ApplicationStageHistory hist
    JOIN hist.application app
    WHERE MONTH(hist.createdDate) = MONTH(CURRENT_DATE)
      AND app.bankId IN :bankIds
    GROUP BY DAY(hist.createdDate)
    ORDER BY DAY(hist.createdDate)
""")
    List<DashboardMonthlyTrendResponse> getManagerMonthlyTrends(
            List<Long> bankIds
    );


    @Query("""
    SELECT new com.bankbroker.loanapp.dto.admin.DashboardLatestApplicationResponse(
        app.id,
        CONCAT(cust.firstName,' ',cust.lastName),
        bank.bankName,
        agency.agencyName,
        hist.createdDate
    )
    FROM ApplicationStageHistory hist
    JOIN hist.application app
    JOIN app.client cust
    JOIN app.bank bank
    LEFT JOIN ApplicationAgencyAssignment assign 
        ON assign.application.id = app.id
    LEFT JOIN assign.agency agency
    WHERE hist.status = com.bankbroker.loanapp.entity.enums.ApplicationHistoryStatus.SITE_VISIT_COMPLETED
      AND app.bankId IN :bankIds
    ORDER BY hist.createdDate DESC
""")
    List<DashboardLatestApplicationResponse> getManagerLatestCompleted(
            List<Long> bankIds
    );


    @Query("""
    SELECT new com.bankbroker.loanapp.dto.admin.DashboardLatestApplicationResponse(
        app.id,
        CONCAT(cust.firstName,' ',cust.lastName),
        bank.bankName,
        agency.agencyName,
        hist.createdDate
    )
    FROM ApplicationStageHistory hist
    JOIN hist.application app
    JOIN app.client cust
    JOIN app.bank bank
    LEFT JOIN ApplicationAgencyAssignment assign 
        ON assign.application.id = app.id
    LEFT JOIN assign.agency agency
    WHERE hist.status = com.bankbroker.loanapp.entity.enums.ApplicationHistoryStatus.REJECTED
      AND app.bankId IN :bankIds
    ORDER BY hist.createdDate DESC
""")
    List<DashboardLatestApplicationResponse> getManagerLatestRejected(
            List<Long> bankIds
    );

}
