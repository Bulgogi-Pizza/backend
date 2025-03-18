package on.logistics.deliveryservice.domain.dto;

import java.util.UUID;
import on.logistics.deliveryservice.application.dtos.request.CreateDeliveryHubInfoDto;
import on.logistics.deliveryservice.application.dtos.request.CreateDeliveryRequestDto;
import on.logistics.deliveryservice.application.dtos.request.CreateDeliveryUserInfoDto;

public record CreateDeliveryDto(UUID orderId, UUID startHubId, UUID endHubId, String destination,
                                String recipient, String recipientSlackEmail) {

    public static CreateDeliveryDto from(CreateDeliveryRequestDto request,
        CreateDeliveryHubInfoDto hubInfo, CreateDeliveryUserInfoDto userInfo) {
        return new CreateDeliveryDto(request.orderId(), request.startHubId(), hubInfo.endHubId(),
            request.description(), userInfo.recipient(), userInfo.recipientSlackEmail());
    }

}
