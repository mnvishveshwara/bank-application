package com.bankbroker.loanapp.service.site_visit.impl;


import com.bankbroker.loanapp.dto.site_visit.SiteVisitPropertyValueAssessmentRequest;
import com.bankbroker.loanapp.dto.site_visit.SiteVisitPropertyValueAssessmentResponse;
import com.bankbroker.loanapp.entity.core.LoanApplication;
import com.bankbroker.loanapp.entity.site_visit.SiteVisitPropertyValueAssessment;
import com.bankbroker.loanapp.mapper.site_visit.SiteVisitPropertyValueAssessmentMapper;
import com.bankbroker.loanapp.repository.core.LoanApplicationRepository;
import com.bankbroker.loanapp.repository.site_visit.SiteVisitPropertyValueAssessmentRepository;
import com.bankbroker.loanapp.service.site_visit.api.SiteVisitPropertyValueAssessmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class SiteVisitPropertyValueAssessmentServiceImpl
        implements SiteVisitPropertyValueAssessmentService {

    private final SiteVisitPropertyValueAssessmentRepository repository;
    private final LoanApplicationRepository loanApplicationRepository;
    private final SiteVisitPropertyValueAssessmentMapper mapper;

    @Override
    public SiteVisitPropertyValueAssessmentResponse saveOrUpdate(
            String applicationId,
            SiteVisitPropertyValueAssessmentRequest request) {

        LoanApplication application = loanApplicationRepository.findById(applicationId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid application id"));

        SiteVisitPropertyValueAssessment entity =
                repository.findByApplication_Id(applicationId)
                        .orElseGet(() -> {
                            SiteVisitPropertyValueAssessment e = mapper.toEntity(request);
                            e.setApplication(application);
                            return e;
                        });

        mapper.updateEntity(request, entity);

        SiteVisitPropertyValueAssessment saved = repository.save(entity);
        return mapper.toResponse(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public SiteVisitPropertyValueAssessmentResponse getByApplicationId(
            String applicationId) {

        SiteVisitPropertyValueAssessment entity = repository.findByApplication_Id(applicationId)
                .orElseThrow(() -> new IllegalArgumentException("Property value assessment not found"));

        return mapper.toResponse(entity);
    }
}