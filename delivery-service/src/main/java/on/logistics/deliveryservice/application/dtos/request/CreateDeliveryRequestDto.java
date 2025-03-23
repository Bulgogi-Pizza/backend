package on.logistics.deliveryservice.application.dtos.request;

import jakarta.servlet.http.HttpServletRequest;
import java.util.UUID;
import on.logistics.deliveryservice.presentation.dtos.request.CreateDeliveryRequest;

public record CreateDeliveryRequestDto(UUID orderId, String destination, UUID startHubId,
                                       HttpServletRequest httpServletRequest) {

    public static CreateDeliveryRequestDto from(CreateDeliveryRequest createDeliveryRequest,
        HttpServletRequest httpServletRequest) {
        return new CreateDeliveryRequestDto(createDeliveryRequest.orderId(),
            createDeliveryRequest.destination(), createDeliveryRequest.startHubId(),
            httpServletRequest);
    }

}
