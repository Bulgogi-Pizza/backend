package on.logistics.productservice.presentation.dtos.response;

import java.util.UUID;

public record UpdateProductQuantityResponse(UUID productId) {

    public static UpdateProductQuantityResponse from(UUID productId) {
        return new UpdateProductQuantityResponse(productId);
    }

}
