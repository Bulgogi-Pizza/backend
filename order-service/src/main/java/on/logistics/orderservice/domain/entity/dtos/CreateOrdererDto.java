package on.logistics.orderservice.domain.entity.dtos;

import java.util.UUID;
import on.logistics.orderservice.infrastructure.clients.company.feign.dtos.GetCompanyResponse;
import on.logistics.orderservice.domain.entity.Order;

public record CreateOrdererDto(
    UUID companyId,
    String name,
    Order order
) {

  public static CreateOrdererDto of(
      UUID companyId,
      GetCompanyResponse responseDto,
      Order order
  ) {
    return new CreateOrdererDto(
        companyId,
        responseDto.companyName(),
        order
    );
  }
}
