package on.logistics.orderservice.application.service.dtos.get.all;

import java.util.UUID;
import org.springframework.data.domain.Pageable;

public record GetOrdererPageByOrdererUserIdRequestDto(
    UUID ordererUserId,
    Pageable pageable
) {

  public static GetOrdererPageByOrdererUserIdRequestDto from(
      UUID ordererUserId,
      Pageable pageable
  ) {
    return new GetOrdererPageByOrdererUserIdRequestDto(ordererUserId, pageable);
  }
}
