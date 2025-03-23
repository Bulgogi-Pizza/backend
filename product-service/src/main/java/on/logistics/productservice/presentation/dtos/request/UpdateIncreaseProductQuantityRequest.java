package on.logistics.productservice.presentation.dtos.request;

import jakarta.validation.constraints.Min;

public record UpdateIncreaseProductQuantityRequest(
    @Min(value = 1, message = "재고 증가는 1개 미만일 수 없습니다.") Long productQuantity) {

}