package com.bankbroker.loanapp.dto.site_visit;


import lombok.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SiteVisitPropertyValuationBuaResponseDto {

    private Long id;
    private String applicationId;
    private Double totalBuaAmount;
    private List<BuaLevelResponseDto> levels;

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class BuaLevelResponseDto {
        private Long id;
        private Long levelId;
        private String levelType;
        private Integer levelOrder;
        private Double areaConsidered;
        private Double ratePerSqFt;
        private Double totalAmount;
    }
}