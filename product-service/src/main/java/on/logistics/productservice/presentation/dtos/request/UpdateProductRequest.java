package on.logistics.productservice.presentation.dtos.request;

import java.util.UUID;
import on.logistics.productservice.application.dto.UpdateProductRequestDto;

public record UpdateProductRequest(UUID productId, String productName, Long productQuantity,
                                   Long productPrice,
                                   Long bundleSize) {

    public static UpdateProductRequestDto from(UUID productId,
        UpdateProductRequest updateProductRequest) {
        return new UpdateProductRequestDto(productId, updateProductRequest.productName,
            updateProductRequest.productQuantity, updateProductRequest.productPrice,
            updateProductRequest.bundleSize);
    }
}
