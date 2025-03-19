package on.logistics.orderservice.domain.repository.dtos;

import java.util.UUID;
import on.logistics.orderservice.application.service.dtos.get.all.SearchOrderPageRequestDto;
import on.logistics.orderservice.global.enums.AuthRole;
import org.springframework.data.domain.Pageable;

public record SearchOrderPageDto(
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

  public static SearchOrderPageDto from(SearchOrderPageRequestDto requestDto) {
    return new SearchOrderPageDto(
        requestDto.pageable(),
        requestDto.userId(),
        requestDto.userRole(),
        requestDto.ordererUserId(),
        requestDto.ordererUserNickname(),
        requestDto.ordererCompanyId(),
        requestDto.ordererCompanyName(),
        requestDto.vendorCompanyId(),
        requestDto.vendorCompanyName()
    );
  }
}
