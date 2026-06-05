package com.bankbroker.loanapp.dto.site_visit;

import lombok.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SiteVisitPropertyValuationBuaRequestDto {

    private List<BuaLevelRequestDto> levels;
    private Double totalBuaAmount;

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class BuaLevelRequestDto {
        private Long levelId;
        private String levelType;
        private Integer levelOrder;
        private Double areaConsidered;
        private Double ratePerSqFt;
        private Double totalAmount;
    }
}