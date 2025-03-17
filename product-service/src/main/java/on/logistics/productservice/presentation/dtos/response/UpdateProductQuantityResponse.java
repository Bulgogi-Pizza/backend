package on.logistics.productservice.presentation.dtos.response;

import java.util.UUID;

public record UpdateProductQuantityResponse(UUID productId) {

    public static UpdateProductQuantityResponse of(UUID productId) {
        return new UpdateProductQuantityResponse(productId);
    }

}
