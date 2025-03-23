package on.logistics.deliverymanagerservice.presentation.dtos.response;

import lombok.Builder;

@Builder
public record ValidDeliveryManagerResponse(
    boolean isExist
) {

    public static ValidDeliveryManagerResponse of(boolean isExist) {
        return ValidDeliveryManagerResponse.builder()
            .isExist(isExist)
            .build();
    }
}
