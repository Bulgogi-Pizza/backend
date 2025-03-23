package on.logistics.deliveryservice.application.dtos.request;

import jakarta.servlet.http.HttpServletRequest;
import java.util.UUID;
import on.logistics.deliveryservice.presentation.dtos.request.UpdateDeliveryRequest;

public record UpdateDeliveryRequestDto(UUID deliveryId, String destination,
                                       HttpServletRequest httpServletRequest) {

    public static UpdateDeliveryRequestDto from(UUID deliveryId, UpdateDeliveryRequest request,
        HttpServletRequest httpServletRequest) {
        return new UpdateDeliveryRequestDto(deliveryId,
            request.destination(), httpServletRequest);
    }

}
