package com.bankbroker.loanapp.repository.core;

import com.bankbroker.loanapp.dto.admin.DashboardLatestApplicationResponse;
import com.bankbroker.loanapp.dto.admin.DashboardMonthlyTrendResponse;
import com.bankbroker.loanapp.dto.admin.DashboardStatusSummaryResponse;
import com.bankbroker.loanapp.entity.core.Customer;
import com.bankbroker.loanapp.entity.core.LoanApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LoanApplicationRepository extends JpaRepository<LoanApplication, String> {
    List<LoanApplication> findByClient(Customer client);

    @Query("""
        SELECT a 
        FROM LoanApplication a
        JOIN ApplicationAgencyAssignment aa ON aa.application = a
        WHERE aa.agency.id = :agencyId
    """)
    List<LoanApplication> findApplicationsByAgencyId(Long agencyId);

    List<LoanApplication> findByClient_Id(String customerId);

    List<LoanApplication> findApplicationsByValuatorId(String valuatorId);


    // ✅ MANAGER STATUS COUNTS
    @Query("""
    SELECT new com.bankbroker.loanapp.dto.admin.DashboardStatusSummaryResponse(
        app.status,
        COUNT(app)
    )
    FROM LoanApplication app
    WHERE app.bankId IN :bankIds
    GROUP BY app.status
""")
    List<DashboardStatusSummaryResponse> getManagerStatusSummary(
            List<Long> bankIds
    );


    // ✅ MANAGER MONTHLY TREND (LAST 30 DAYS)
    @Query("""
    SELECT new com.bankbroker.loanapp.dto.admin.DashboardMonthlyTrendResponse(
        DAY(app.createdDate),
        COUNT(app)
    )
    FROM LoanApplication app
    WHERE MONTH(app.createdDate) = MONTH(CURRENT_DATE)
      AND app.bankId IN :bankIds
    GROUP BY DAY(app.createdDate)
    ORDER BY DAY(app.createdDate)
""")
    List<DashboardMonthlyTrendResponse> getManagerMonthlyTrends(
            List<Long> bankIds
    );




    // ✅ MANAGER COMPLETED APPLICATIONS (LATEST Completed)
    @Query("""
    SELECT new com.bankbroker.loanapp.dto.admin.DashboardLatestApplicationResponse(
        app.id,
        app.client.firstName,
        app.bank.bankName,
        agency.agencyName,
        app.updatedDate
    )
    FROM LoanApplication app
    LEFT JOIN ApplicationAgencyAssignment assign 
        ON assign.application.id = app.id
    LEFT JOIN AgencyMaster agency 
        ON agency.id = assign.agency.id
    WHERE app.status = 'SITE_VISIT_COMPLETED'
      AND app.bankId IN :bankIds
    ORDER BY app.updatedDate DESC
""")
    List<DashboardLatestApplicationResponse> getManagerLatestCompleted(
            List<Long> bankIds
    );



    // ✅ MANAGER REJECTED APPLICATIONS (LATEST Rejected)
    @Query("""
    SELECT new com.bankbroker.loanapp.dto.admin.DashboardLatestApplicationResponse(
        app.id,
        app.client.firstName,
        app.bank.bankName,
        'NA',
        app.updatedDate
    )
    FROM LoanApplication app
    WHERE app.status = 'REJECTED'
      AND app.bankId IN :bankIds
    ORDER BY app.updatedDate DESC
""")
    List<DashboardLatestApplicationResponse> getManagerLatestRejected(
            List<Long> bankIds
    );



    List<LoanApplication> findByBankIdIn(List<Long> bankIds);
}
