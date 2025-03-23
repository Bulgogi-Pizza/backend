package on.logistics.productservice.presentation.dtos.request;

import jakarta.validation.constraints.Min;

public record UpdateReduceProductQuantityRequest(
    @Min(value = 1, message = "재고 수량 감소는 한 개 미만일 수 없습니다.") Long productQuantity) {

}