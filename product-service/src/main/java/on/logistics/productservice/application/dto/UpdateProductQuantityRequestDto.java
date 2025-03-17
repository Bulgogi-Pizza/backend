package on.logistics.productservice.application.dto;

import java.util.UUID;

public record UpdateProductQuantityRequestDto(UUID productId, Long quantity) {

}
