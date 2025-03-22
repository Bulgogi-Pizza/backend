package on.logistics.productservice.application.dto;

import jakarta.servlet.http.HttpServletRequest;
import java.util.UUID;
import on.logistics.productservice.presentation.dtos.request.UpdateProductRequest;

public record UpdateProductRequestDto(UUID productId, String productName, Long productQuantity,
                                      Long productPrice,
                                      Long bundleSize,
                                      HttpServletRequest httpServletRequest) {

    public static UpdateProductRequestDto from(UUID productId,
        UpdateProductRequest updateProductRequest, HttpServletRequest httpServletRequest) {
        return new UpdateProductRequestDto(productId, updateProductRequest.productName(),
            updateProductRequest.productQuantity(), updateProductRequest.productPrice(),
            updateProductRequest.bundleSize(), httpServletRequest);
    }

}
