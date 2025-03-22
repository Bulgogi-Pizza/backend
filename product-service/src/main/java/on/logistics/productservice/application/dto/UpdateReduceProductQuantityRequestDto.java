package on.logistics.productservice.application.dto;

import jakarta.servlet.http.HttpServletRequest;
import java.util.UUID;
import on.logistics.productservice.presentation.dtos.request.UpdateReduceProductQuantityRequest;

public record UpdateReduceProductQuantityRequestDto(UUID productId, Long productQuantity,
                                                    HttpServletRequest httpServletRequest) {

    public static UpdateReduceProductQuantityRequestDto from(UUID productId,
        UpdateReduceProductQuantityRequest updateReduceProductQuantityRequest,
        HttpServletRequest httpServletRequest) {
        return new UpdateReduceProductQuantityRequestDto(productId,
            updateReduceProductQuantityRequest.productQuantity(), httpServletRequest);
    }
}
