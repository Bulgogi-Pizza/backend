package on.logistics.deliveryservice.application.dtos.request;

import jakarta.servlet.http.HttpServletRequest;
import java.util.UUID;
import on.logistics.deliveryservice.domain.enums.DeliveryStatus;
import org.springframework.data.domain.Pageable;

public record SearchDeliveryRequestDto(String destination, String recipient, DeliveryStatus status,
                                       UUID companyDeliveryManagerId,
                                       Pageable pageable,
                                       HttpServletRequest httpServletRequest) {

    public static SearchDeliveryRequestDto from(String destination, String recipient,
        DeliveryStatus status, UUID companyDeliveryManagerId,
        Pageable pageable, HttpServletRequest httpServletRequest) {
        return new SearchDeliveryRequestDto(destination, recipient, status,
            companyDeliveryManagerId, pageable, httpServletRequest);
    }
}
