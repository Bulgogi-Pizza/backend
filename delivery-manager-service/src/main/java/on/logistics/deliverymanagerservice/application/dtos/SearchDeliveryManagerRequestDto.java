package on.logistics.deliverymanagerservice.application.dtos;

import jakarta.servlet.http.HttpServletRequest;
import lombok.Builder;
import org.springframework.data.domain.Pageable;

@Builder
public record SearchDeliveryManagerRequestDto(
    String keyword,
    String hubType,
    String deliveryType,
    Pageable pageable,
    HttpServletRequest passportRequest
) {

    public static SearchDeliveryManagerRequestDto of(String keyword, String hubType,
        String deliveryType, Pageable pageable, HttpServletRequest passportRequest) {
        return SearchDeliveryManagerRequestDto.builder()
            .keyword(keyword)
            .hubType(hubType)
            .deliveryType(deliveryType)
            .pageable(pageable)
            .passportRequest(passportRequest)
            .build();
    }
}
