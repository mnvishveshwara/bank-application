package com.bankbroker.loanapp.mapper.site_visit;

import com.bankbroker.loanapp.dto.site_visit.SiteVisitPropertyValuationBuaRequestDto;
import com.bankbroker.loanapp.dto.site_visit.SiteVisitPropertyValuationBuaResponseDto;
import com.bankbroker.loanapp.entity.site_visit.SiteVisitPropertyValuationBua;
import com.bankbroker.loanapp.entity.site_visit.SiteVisitPropertyValuationBuaLevel;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface SiteVisitPropertyValuationBuaMapper {

    // ── Request → Entity ──────────────────────────────────────────────────

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "applicationId", ignore = true)
    @Mapping(target = "levels", ignore = true)
    SiteVisitPropertyValuationBua toEntity(SiteVisitPropertyValuationBuaRequestDto dto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "propertyValuationBua", ignore = true)
    SiteVisitPropertyValuationBuaLevel toLevelEntity(
            SiteVisitPropertyValuationBuaRequestDto.BuaLevelRequestDto dto);

    // ── Entity → Response ─────────────────────────────────────────────────

    SiteVisitPropertyValuationBuaResponseDto toResponseDto(SiteVisitPropertyValuationBua entity);

    SiteVisitPropertyValuationBuaResponseDto.BuaLevelResponseDto toLevelResponseDto(
            SiteVisitPropertyValuationBuaLevel entity);

    // ── Update existing entity from request ───────────────────────────────

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "applicationId", ignore = true)
    @Mapping(target = "levels", ignore = true)
    void updateEntityFromDto(SiteVisitPropertyValuationBuaRequestDto dto,
                             @MappingTarget SiteVisitPropertyValuationBua entity);
}