package on.logistics.orderservice.application.service.dtos.get.all;

import java.util.UUID;
import on.logistics.orderservice.global.enums.AuthRole;
import org.springframework.data.domain.Pageable;

public record SearchOrderPageRequestDto(
    Pageable pageable,
    UUID userId,
    AuthRole userRole,
    UUID ordererUserId,
    String ordererUserNickname,
    UUID ordererCompanyId,
    String ordererCompanyName,
    UUID vendorCompanyId,
    String vendorCompanyName
) {

  public static SearchOrderPageRequestDto from(
      Pageable pageable,
      UUID userId,
      AuthRole userRole,
      UUID ordererUserId,
      String ordererUserNickname,
      UUID ordererCompanyId,
      String ordererCompanyName,
      UUID vendorCompanyId,
      String vendorCompanyName
  ) {
    return new SearchOrderPageRequestDto(
        pageable,
        userId,
        userRole,
        ordererUserId,
        ordererUserNickname,
        ordererCompanyId,
        ordererCompanyName,
        vendorCompanyId,
        vendorCompanyName
    );
  }
}
