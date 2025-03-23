package on.logistics.productservice.application.dto;

import jakarta.servlet.http.HttpServletRequest;
import java.util.UUID;
import on.logistics.productservice.presentation.dtos.request.UpdateIncreaseProductQuantityRequest;

public record UpdateIncreaseProductQuantityRequestDto(UUID productId, Long productQuantity,
                                                      HttpServletRequest httpServletRequest) {

    public static UpdateIncreaseProductQuantityRequestDto from(UUID productId,
        UpdateIncreaseProductQuantityRequest updateIncreaseProductQuantityRequest,
        HttpServletRequest httpServletRequest) {
        return new UpdateIncreaseProductQuantityRequestDto(productId,
            updateIncreaseProductQuantityRequest.productQuantity(), httpServletRequest);
    }
}
