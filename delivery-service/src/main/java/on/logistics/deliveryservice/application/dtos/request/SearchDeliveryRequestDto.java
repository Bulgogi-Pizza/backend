package on.logistics.deliveryservice.application.dtos.request;

import java.util.UUID;
import on.logistics.deliveryservice.domain.enums.DeliveryStatus;
import org.springframework.data.domain.Pageable;

public record SearchDeliveryRequestDto(String destination, String recipient, DeliveryStatus status,
                                       UUID companyDeliveryManagerId,
                                       Pageable pageable) {

    public static SearchDeliveryRequestDto from(String destination, String recipient,
        DeliveryStatus status, UUID companyDeliveryManagerId,
        Pageable pageable) {
        return new SearchDeliveryRequestDto(destination, recipient, status,
            companyDeliveryManagerId, pageable);
    }
}
