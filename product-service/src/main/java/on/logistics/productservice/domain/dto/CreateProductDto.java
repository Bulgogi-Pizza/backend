package on.logistics.productservice.domain.dto;

import java.util.UUID;

public record CreateProductDto(String productName, UUID companyId, UUID managedHubId,
                               Long productQuantity, Long productPrice, Long bundleSize) {

    public static CreateProductDto from(String productName, UUID companyId, UUID managedHubId,
        Long productQuantity, Long productPrice, Long bundleSize) {
        return new CreateProductDto(productName, companyId, managedHubId, productQuantity,
            productPrice, bundleSize);
    }
}
