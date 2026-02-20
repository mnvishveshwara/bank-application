//package com.bankbroker.loanapp.service.site_visit.impl;
//
//import com.bankbroker.loanapp.dto.site_visit.*;
//import com.bankbroker.loanapp.entity.core.AdminUser;
//import com.bankbroker.loanapp.entity.core.LoanApplication;
//import com.bankbroker.loanapp.entity.site_visit.*;
//import com.bankbroker.loanapp.mapper.site_visit.*;
//import com.bankbroker.loanapp.repository.core.LoanApplicationRepository;
//import com.bankbroker.loanapp.repository.site_visit.SiteVisitPropertyValueAssessmentAmenitiesRepository;
//import com.bankbroker.loanapp.repository.stage.ApplicationAgencyAssignmentRepository;
//import com.bankbroker.loanapp.service.site_visit.api.SiteVisitPropertyValueAssessmentAmenitiesService;
//import com.bankbroker.loanapp.util.SecurityUtil;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.time.LocalDateTime;
//import java.util.ArrayList;
//
//@Service
//@RequiredArgsConstructor
//@Transactional
//public class SiteVisitPropertyValueAssessmentAmenitiesServiceImpl
//        implements SiteVisitPropertyValueAssessmentAmenitiesService {
//
//    private final LoanApplicationRepository loanRepo;
//    private final SiteVisitPropertyValueAssessmentAmenitiesRepository amenitiesRepo;
//    private final SiteVisitPropertyValueAssessmentAmenityItemMapper itemMapper;
//    private final SiteVisitPropertyValueAssessmentAmenitiesMapper mapper;
//    private final SecurityUtil securityUtil;
//    private final ApplicationAgencyAssignmentRepository agencyAssignmentRepo;
//
//    @Override
//    @Transactional
//    public SiteVisitPropertyValueAssessmentAmenitiesResponse saveSiteVisitAmenities(
//            String applicationId,
//            SiteVisitPropertyValueAssessmentAmenitiesRequest request) {
//
//        AdminUser user = securityUtil.getLoggedInAdmin();
//
//        LoanApplication app = loanRepo.findById(applicationId)
//                .orElseThrow(() -> new RuntimeException("Application not found"));
//
//        SiteVisitPropertyValueAssessmentAmenities amenities =
//                amenitiesRepo.findByApplication(app)
//                        .orElseGet(() ->
//                                SiteVisitPropertyValueAssessmentAmenities.builder()
//                                        .application(app)
//                                        .createdBy(user)
//                                        .createdDate(LocalDateTime.now())
//                                        .items(new ArrayList<>()) // IMPORTANT
//                                        .build()
//                        );
//
//        amenities.setUpdatedBy(user);
//        amenities.setUpdatedDate(LocalDateTime.now());
//
//        // Clear existing collection (DO NOT replace it)
//        amenities.getItems().clear();
//
//        double total = 0;
//
//        for (SiteVisitPropertyValueAssessmentAmenityItemRequest ir : request.getItems()) {
//
//            SiteVisitPropertyValueAssessmentAmenityItem item =
//                    itemMapper.toEntity(ir);
//
//            // Owning side MUST be set
//            item.setAmenities(amenities);
//
//            total += ir.getAmenityValue() == null ? 0 : ir.getAmenityValue();
//
//            // Add to managed collection
//            amenities.getItems().add(item);
//        }
//
//        amenities.setTotalAmenitiesValue(total);
//
//        SiteVisitPropertyValueAssessmentAmenities saved =
//                amenitiesRepo.save(amenities);
//
//        SiteVisitPropertyValueAssessmentAmenitiesResponse response =
//                mapper.toResponse(saved);
//
//        response.setItems(
//                saved.getItems()
//                        .stream()
//                        .map(itemMapper::toResponse)
//                        .toList()
//        );
//
//        return response;
//    }
//
//
//    @Override
//    @Transactional(readOnly = true)
//    public SiteVisitPropertyValueAssessmentAmenitiesResponse getSiteVisitAmenities(
//            String applicationId) {
//
//        LoanApplication app = loanRepo.findById(applicationId)
//                .orElseThrow(() -> new RuntimeException("Application not found"));
//
//        SiteVisitPropertyValueAssessmentAmenities amenities =
//                amenitiesRepo.findByApplication(app)
//                        .orElseThrow(() ->
//                                new RuntimeException("Amenities not found")
//                        );
//
//        SiteVisitPropertyValueAssessmentAmenitiesResponse response =
//                mapper.toResponse(amenities);
//
//        response.setItems(
//                amenities.getItems()
//                        .stream()
//                        .map(itemMapper::toResponse)
//                        .toList()
//        );
//
//        return response;
//    }
//}

package com.bankbroker.loanapp.service.site_visit.impl;

import com.bankbroker.loanapp.dto.site_visit.*;
import com.bankbroker.loanapp.entity.core.AdminUser;
import com.bankbroker.loanapp.entity.core.LoanApplication;
import com.bankbroker.loanapp.entity.enums.AssignmentType;
import com.bankbroker.loanapp.entity.enums.Role;
import com.bankbroker.loanapp.entity.site_visit.*;
import com.bankbroker.loanapp.exception.ResourceNotFoundException;
import com.bankbroker.loanapp.mapper.site_visit.*;
import com.bankbroker.loanapp.repository.core.LoanApplicationRepository;
import com.bankbroker.loanapp.repository.site_visit.SiteVisitPropertyValueAssessmentAmenitiesRepository;
import com.bankbroker.loanapp.repository.stage.ApplicationAgencyAssignmentRepository;
import com.bankbroker.loanapp.service.site_visit.api.SiteVisitPropertyValueAssessmentAmenitiesService;
import com.bankbroker.loanapp.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;

@Slf4j
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
    private final ApplicationAgencyAssignmentRepository agencyAssignmentRepo;

    @Override
    @Transactional
    public SiteVisitPropertyValueAssessmentAmenitiesResponse saveSiteVisitAmenities(
            String applicationId,
            SiteVisitPropertyValueAssessmentAmenitiesRequest request) {

        AdminUser user = securityUtil.getLoggedInAdmin();
        Role role = user.getRole();

        // 1. Role Validation
        if (role != Role.AGENCY_VALUATOR && role != Role.BANK_VALUATOR) {
            throw new RuntimeException("Unauthorized: Only assigned valuators can save amenity assessments");
        }

        LoanApplication app = loanRepo.findById(applicationId)
                .orElseThrow(() -> new ResourceNotFoundException("LoanApplication", "id", applicationId));

        // 2. Assignment Validation (Internal vs. Agency)
        validateAssignment(app, user);

        SiteVisitPropertyValueAssessmentAmenities amenities = amenitiesRepo.findByApplication(app)
                .orElseGet(() -> SiteVisitPropertyValueAssessmentAmenities.builder()
                        .application(app)
                        .createdBy(user)
                        .createdDate(LocalDateTime.now())
                        .items(new ArrayList<>())
                        .build());

        amenities.setUpdatedBy(user);
        amenities.setUpdatedDate(LocalDateTime.now());

        // 3. Update Items (Orphan Removal safe clear)
        amenities.getItems().clear();
        double total = 0;

        for (SiteVisitPropertyValueAssessmentAmenityItemRequest ir : request.getItems()) {
            SiteVisitPropertyValueAssessmentAmenityItem item = itemMapper.toEntity(ir);
            item.setAmenities(amenities);
            total += (ir.getAmenityValue() == null) ? 0.0 : ir.getAmenityValue();
            amenities.getItems().add(item);
        }

        amenities.setTotalAmenitiesValue(total);
        SiteVisitPropertyValueAssessmentAmenities saved = amenitiesRepo.save(amenities);

        return buildResponse(saved);
    }

    /**
     * Shared validation to check if the user is authorized for this specific application
     */
    private void validateAssignment(LoanApplication app, AdminUser user) {
        boolean isAssigned = false;

        if (app.getAssignmentType() == AssignmentType.INTERNAL) {
            isAssigned = app.getInternalValuator() != null &&
                    app.getInternalValuator().getId().equals(user.getId());
        } else if (app.getAssignmentType() == AssignmentType.AGENCY) {
            isAssigned = agencyAssignmentRepo.existsByApplicationAndAgency(app, user.getAgency());
        }

        if (!isAssigned) {
            log.warn("Unauthorized access attempt on app {} by user {}", app.getId(), user.getId());
            throw new RuntimeException("Access Denied: You are not authorized to edit this application.");
        }
    }

    @Override
    @Transactional(readOnly = true)
    public SiteVisitPropertyValueAssessmentAmenitiesResponse getSiteVisitAmenities(String applicationId) {
        LoanApplication app = loanRepo.findById(applicationId)
                .orElseThrow(() -> new ResourceNotFoundException("LoanApplication", "id", applicationId));

        SiteVisitPropertyValueAssessmentAmenities amenities = amenitiesRepo.findByApplication(app)
                .orElseThrow(() -> new RuntimeException("Amenities assessment not found"));

        return buildResponse(amenities);
    }

    private SiteVisitPropertyValueAssessmentAmenitiesResponse buildResponse(SiteVisitPropertyValueAssessmentAmenities entity) {
        SiteVisitPropertyValueAssessmentAmenitiesResponse response = mapper.toResponse(entity);
        response.setItems(entity.getItems().stream()
                .map(itemMapper::toResponse)
                .toList());
        return response;
    }
}