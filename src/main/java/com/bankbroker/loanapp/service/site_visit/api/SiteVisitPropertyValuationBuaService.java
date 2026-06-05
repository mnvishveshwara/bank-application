package com.bankbroker.loanapp.service.site_visit.api;

import com.bankbroker.loanapp.dto.site_visit.SiteVisitPropertyValuationBuaRequestDto;
import com.bankbroker.loanapp.dto.site_visit.SiteVisitPropertyValuationBuaResponseDto;

public interface SiteVisitPropertyValuationBuaService {

    SiteVisitPropertyValuationBuaResponseDto save(String applicationId,
                                                  SiteVisitPropertyValuationBuaRequestDto requestDto);

    SiteVisitPropertyValuationBuaResponseDto getByApplicationId(String applicationId);
}