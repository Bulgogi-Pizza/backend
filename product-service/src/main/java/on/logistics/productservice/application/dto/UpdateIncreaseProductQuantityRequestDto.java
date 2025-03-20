package on.logistics.productservice.application.dto;

import java.util.UUID;
import on.logistics.productservice.presentation.dtos.request.UpdateIncreaseProductQuantityRequest;

public record UpdateIncreaseProductQuantityRequestDto(UUID productId, Long productQuantity) {

    public static UpdateIncreaseProductQuantityRequestDto from(UUID productId,
        UpdateIncreaseProductQuantityRequest updateIncreaseProductQuantityRequest) {
        return new UpdateIncreaseProductQuantityRequestDto(productId,
            updateIncreaseProductQuantityRequest.productQuantity());
    }
}
