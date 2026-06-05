package com.bankbroker.loanapp.service.site_visit.impl;

import com.bankbroker.loanapp.dto.site_visit.SiteVisitPropertyValuationBuaRequestDto;
import com.bankbroker.loanapp.dto.site_visit.SiteVisitPropertyValuationBuaResponseDto;
import com.bankbroker.loanapp.entity.site_visit.SiteVisitPropertyValuationBua;
import com.bankbroker.loanapp.entity.site_visit.SiteVisitPropertyValuationBuaLevel;
import com.bankbroker.loanapp.exception.ResourceNotFoundException;
import com.bankbroker.loanapp.mapper.site_visit.SiteVisitPropertyValuationBuaMapper;
import com.bankbroker.loanapp.repository.site_visit.SiteVisitPropertyValuationBuaRepository;
import com.bankbroker.loanapp.service.site_visit.api.SiteVisitPropertyValuationBuaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class SiteVisitPropertyValuationBuaServiceImpl
        implements SiteVisitPropertyValuationBuaService {

    private final SiteVisitPropertyValuationBuaRepository repository;
    private final SiteVisitPropertyValuationBuaMapper mapper;

    @Override
    @Transactional
    public SiteVisitPropertyValuationBuaResponseDto save(
            String applicationId,
            SiteVisitPropertyValuationBuaRequestDto requestDto) {

        // Upsert — find existing or create new
        SiteVisitPropertyValuationBua entity = repository
                .findByApplicationId(applicationId)
                .orElseGet(() -> {
                    SiteVisitPropertyValuationBua newEntity =
                            new SiteVisitPropertyValuationBua();
                    newEntity.setApplicationId(applicationId);
                    return newEntity;
                });

        // Update scalar fields (totalBuaAmount)
        mapper.updateEntityFromDto(requestDto, entity);

        // Rebuild levels — clear old, orphanRemoval deletes them from DB
        entity.getLevels().clear();

        if (requestDto.getLevels() != null) {
            requestDto.getLevels().forEach(levelDto -> {
                SiteVisitPropertyValuationBuaLevel level =
                        mapper.toLevelEntity(levelDto);
                level.setPropertyValuationBua(entity); // set back-reference
                entity.getLevels().add(level);

            });
        }

        SiteVisitPropertyValuationBua saved = repository.save(entity);
        log.info("Saved SiteVisitPropertyValuationBua for applicationId={}",
                applicationId);

        return mapper.toResponseDto(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public SiteVisitPropertyValuationBuaResponseDto getByApplicationId(
            String applicationId) {

        SiteVisitPropertyValuationBua entity = repository
                .findByApplicationId(applicationId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "SiteVisitPropertyValuationBua",  // entity
                        "applicationId",                   // field
                        applicationId                      // value
                ));

        return mapper.toResponseDto(entity);
    }
}