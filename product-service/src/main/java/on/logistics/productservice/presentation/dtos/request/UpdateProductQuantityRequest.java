package on.logistics.productservice.presentation.dtos.request;

import jakarta.validation.constraints.Min;
import java.util.UUID;
import on.logistics.productservice.application.dto.UpdateProductQuantityRequestDto;

public record UpdateProductQuantityRequest(
    @Min(value = 1, message = "재고 수량은 한 개 미만일 수 없습니다.") Long quantity) {

    public static UpdateProductQuantityRequestDto from(UUID productId,
        UpdateProductQuantityRequest updateProductQuantityRequest) {
        return new UpdateProductQuantityRequestDto(productId,
            updateProductQuantityRequest.quantity());
    }

}
