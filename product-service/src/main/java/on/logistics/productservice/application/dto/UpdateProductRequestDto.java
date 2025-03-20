package on.logistics.productservice.application.dto;

import java.util.UUID;
import on.logistics.productservice.presentation.dtos.request.UpdateProductRequest;

public record UpdateProductRequestDto(UUID productId, String productName, Long productQuantity,
                                      Long productPrice,
                                      Long bundleSize) {

    public static UpdateProductRequestDto from(UUID productId,
        UpdateProductRequest updateProductRequest) {
        return new UpdateProductRequestDto(productId, updateProductRequest.productName(),
            updateProductRequest.productQuantity(), updateProductRequest.productPrice(),
            updateProductRequest.bundleSize());
    }

}
