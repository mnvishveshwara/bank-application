package com.bankbroker.loanapp.dto.site_visit;

import com.bankbroker.loanapp.entity.enums.SiteVisitImageCategory;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SiteVisitImagesUploadRequest {

    private SiteVisitImageCategory category;
    private List<MultipartFile> files;
}