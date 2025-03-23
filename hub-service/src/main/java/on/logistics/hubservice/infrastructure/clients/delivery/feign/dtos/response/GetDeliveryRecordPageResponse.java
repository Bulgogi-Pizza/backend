package on.logistics.hubservice.infrastructure.clients.delivery.feign.dtos.response;

import java.util.List;

public record GetDeliveryRecordPageResponse(
    List<GetDeliveryRecordResponse> content,
    boolean last,
    int totalPages,
    int totalElements
) {

}
