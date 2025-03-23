package on.logistics.productservice.presentation.dtos.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public record UpdateProductRequest(@NotBlank(message = "상품 이름은 필수 입력 값입니다.") String productName,
                                   @Min(value = -1, message = "상품 수량은 음수로 지정될 수 없습니다.") Long productQuantity,
                                   @Min(value = 1, message = "상품 가격은 0원 이하일 수 없습니다.") Long productPrice,
                                   @Min(value = 0, message = "번들 사이즈는 0원 이하일 수 없습니다.") Long bundleSize) {

}
