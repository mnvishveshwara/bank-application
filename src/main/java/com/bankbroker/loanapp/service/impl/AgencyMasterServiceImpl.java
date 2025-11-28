package com.bankbroker.loanapp.service.impl;

import com.bankbroker.loanapp.dto.master.AgencyMasterRequest;
import com.bankbroker.loanapp.dto.master.AgencyMasterResponse;
import com.bankbroker.loanapp.entity.AgencyMaster;
import com.bankbroker.loanapp.repository.AgencyMasterRepository;
import com.bankbroker.loanapp.service.AgencyMasterService;
import com.bankbroker.loanapp.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class AgencyMasterServiceImpl implements AgencyMasterService {

    private final AgencyMasterRepository repository;

    @Override
    public AgencyMasterResponse createAgency(AgencyMasterRequest req) {

        if (repository.existsByAgencyName(req.getAgencyName())) {
            throw new IllegalArgumentException("Agency name already exists.");
        }

        String admin = getLoggedInAdminId();
        log.info("Admin Id: " + admin);
        AgencyMaster agency = AgencyMaster.builder()
                .agencyName(req.getAgencyName())
                .contactName(req.getContactName())
                .contactNumber(req.getContactNumber())
                .streetLine1(req.getStreetLine1())
                .streetLine2(req.getStreetLine2())
                .pinCode(req.getPinCode())
                .city(req.getCity())
                .state(req.getState())
                .latitude(req.getLatitude())
                .longitude(req.getLongitude())
                .mapURL(req.getMapURL())
                .createdBy(admin)
                .updatedBy(admin)
                .build();

        agency = repository.save(agency);
        return toResponse(agency);
    }

    @Override
    public AgencyMasterResponse updateAgency(Long id, AgencyMasterRequest req) {
        AgencyMaster agency = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("AgencyMaster", "id", id));

        String admin = getLoggedInAdminId();

        agency.setAgencyName(req.getAgencyName());
        agency.setContactName(req.getContactName());
        agency.setContactNumber(req.getContactNumber());
        agency.setStreetLine1(req.getStreetLine1());
        agency.setStreetLine2(req.getStreetLine2());
        agency.setPinCode(req.getPinCode());
        agency.setCity(req.getCity());
        agency.setState(req.getState());
        agency.setLatitude(req.getLatitude());
        agency.setLongitude(req.getLongitude());
        agency.setMapURL(req.getMapURL());
        agency.setUpdatedBy(admin);

        agency = repository.save(agency);
        return toResponse(agency);
    }

    @Override
    public AgencyMasterResponse getAgency(Long id) {
        AgencyMaster agency = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("AgencyMaster", "id", id));
        return toResponse(agency);
    }

    @Override
    public List<AgencyMasterResponse> getAllAgencies() {
        return repository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    @Override
    public void deleteAgency(Long id) {
        if (!repository.existsById(id))
            throw new ResourceNotFoundException("AgencyMaster", "id", id);
        repository.deleteById(id);
    }

    private AgencyMasterResponse toResponse(AgencyMaster a) {
        return AgencyMasterResponse.builder()
                .id(a.getId())
                .agencyName(a.getAgencyName())
                .contactName(a.getContactName())
                .contactNumber(a.getContactNumber())
                .streetLine1(a.getStreetLine1())
                .streetLine2(a.getStreetLine2())
                .pinCode(a.getPinCode())
                .city(a.getCity())
                .state(a.getState())
                .latitude(a.getLatitude())
                .longitude(a.getLongitude())
                .mapURL(a.getMapURL())
                .createdAt(a.getCreatedAt())
                .updatedAt(a.getUpdatedAt())
                .createdBy(a.getCreatedBy())
                .updatedBy(a.getUpdatedBy())
                .build();
    }

    // 🔥 Reuse your existing method from security context
    private String getLoggedInAdminId() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }
}