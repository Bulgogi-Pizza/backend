package on.logistics.productservice.application.dto;

import java.util.UUID;
import on.logistics.productservice.presentation.dtos.request.UpdateReduceProductQuantityRequest;

public record UpdateReduceProductQuantityRequestDto(UUID productId, Long productQuantity) {

    public static UpdateReduceProductQuantityRequestDto from(UUID productId,
        UpdateReduceProductQuantityRequest updateReduceProductQuantityRequest) {
        return new UpdateReduceProductQuantityRequestDto(productId,
            updateReduceProductQuantityRequest.productQuantity());
    }
}
