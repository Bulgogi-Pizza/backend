package on.logistics.productservice.application.dto;

import jakarta.servlet.http.HttpServletRequest;
import java.util.UUID;
import on.logistics.productservice.presentation.dtos.request.CreateProductRequest;

public record CreateProductRequestDto(String productName, UUID companyId, Long productQuantity,
                                      Long productPrice, Long bundleSize,
                                      HttpServletRequest httpServletRequest) {

    public static CreateProductRequestDto from(CreateProductRequest createProductRequest,
        HttpServletRequest httpServletRequest) {
        return new CreateProductRequestDto(createProductRequest.productName(),
            createProductRequest.companyId(), createProductRequest.productQuantity(),
            createProductRequest.productPrice(), createProductRequest.bundleSize(),
            httpServletRequest);
    }
}
