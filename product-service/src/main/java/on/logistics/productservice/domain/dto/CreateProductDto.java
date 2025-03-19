package on.logistics.productservice.domain.dto;

import java.util.UUID;
import on.logistics.productservice.application.dto.CreateProductRequestDto;
import on.logistics.productservice.infrastructure.clients.company.feign.dtos.GetCompanyInfo;

public record CreateProductDto(String productName, UUID companyId, UUID managedHubId,
                               Long productQuantity, Long productPrice, Long bundleSize) {

    public static CreateProductDto from(CreateProductRequestDto requestDto,
        GetCompanyInfo getCompanyInfo) {
        return new CreateProductDto(requestDto.productName(), requestDto.companyId(),
            getCompanyInfo.managedHubId(), requestDto.productQuantity(), requestDto.productPrice(),
            requestDto.bundleSize());
    }
}
