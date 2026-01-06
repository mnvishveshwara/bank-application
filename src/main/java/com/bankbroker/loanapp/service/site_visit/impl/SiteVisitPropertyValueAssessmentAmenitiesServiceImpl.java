package com.bankbroker.loanapp.service.site_visit.impl;

import com.bankbroker.loanapp.dto.site_visit.*;
import com.bankbroker.loanapp.entity.core.AdminUser;
import com.bankbroker.loanapp.entity.core.LoanApplication;
import com.bankbroker.loanapp.entity.site_visit.*;
import com.bankbroker.loanapp.mapper.site_visit.*;
import com.bankbroker.loanapp.repository.core.LoanApplicationRepository;
import com.bankbroker.loanapp.repository.site_visit.SiteVisitPropertyValueAssessmentAmenitiesRepository;
import com.bankbroker.loanapp.service.site_visit.api.SiteVisitPropertyValueAssessmentAmenitiesService;
import com.bankbroker.loanapp.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;

@Service
@RequiredArgsConstructor
@Transactional
public class SiteVisitPropertyValueAssessmentAmenitiesServiceImpl
        implements SiteVisitPropertyValueAssessmentAmenitiesService {

    private final LoanApplicationRepository loanRepo;
    private final SiteVisitPropertyValueAssessmentAmenitiesRepository amenitiesRepo;
    private final SiteVisitPropertyValueAssessmentAmenityItemMapper itemMapper;
    private final SiteVisitPropertyValueAssessmentAmenitiesMapper mapper;
    private final SecurityUtil securityUtil;

    @Override
    @Transactional
    public SiteVisitPropertyValueAssessmentAmenitiesResponse saveSiteVisitAmenities(
            String applicationId,
            SiteVisitPropertyValueAssessmentAmenitiesRequest request) {

        AdminUser user = securityUtil.getLoggedInAdmin();

        LoanApplication app = loanRepo.findById(applicationId)
                .orElseThrow(() -> new RuntimeException("Application not found"));

        SiteVisitPropertyValueAssessmentAmenities amenities =
                amenitiesRepo.findByApplication(app)
                        .orElseGet(() ->
                                SiteVisitPropertyValueAssessmentAmenities.builder()
                                        .application(app)
                                        .createdBy(user)
                                        .createdDate(LocalDateTime.now())
                                        .items(new ArrayList<>()) // IMPORTANT
                                        .build()
                        );

        amenities.setUpdatedBy(user);
        amenities.setUpdatedDate(LocalDateTime.now());

        // Clear existing collection (DO NOT replace it)
        amenities.getItems().clear();

        double total = 0;

        for (SiteVisitPropertyValueAssessmentAmenityItemRequest ir : request.getItems()) {

            SiteVisitPropertyValueAssessmentAmenityItem item =
                    itemMapper.toEntity(ir);

            // Owning side MUST be set
            item.setAmenities(amenities);

            total += ir.getAmenityValue() == null ? 0 : ir.getAmenityValue();

            // Add to managed collection
            amenities.getItems().add(item);
        }

        amenities.setTotalAmenitiesValue(total);

        SiteVisitPropertyValueAssessmentAmenities saved =
                amenitiesRepo.save(amenities);

        SiteVisitPropertyValueAssessmentAmenitiesResponse response =
                mapper.toResponse(saved);

        response.setItems(
                saved.getItems()
                        .stream()
                        .map(itemMapper::toResponse)
                        .toList()
        );

        return response;
    }


    @Override
    @Transactional(readOnly = true)
    public SiteVisitPropertyValueAssessmentAmenitiesResponse getSiteVisitAmenities(
            String applicationId) {

        LoanApplication app = loanRepo.findById(applicationId)
                .orElseThrow(() -> new RuntimeException("Application not found"));

        SiteVisitPropertyValueAssessmentAmenities amenities =
                amenitiesRepo.findByApplication(app)
                        .orElseThrow(() ->
                                new RuntimeException("Amenities not found")
                        );

        SiteVisitPropertyValueAssessmentAmenitiesResponse response =
                mapper.toResponse(amenities);

        response.setItems(
                amenities.getItems()
                        .stream()
                        .map(itemMapper::toResponse)
                        .toList()
        );

        return response;
    }
}
