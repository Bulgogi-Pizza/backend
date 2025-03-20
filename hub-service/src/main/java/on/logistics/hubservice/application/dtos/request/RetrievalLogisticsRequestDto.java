package on.logistics.hubservice.application.dtos.request;

import java.util.List;
import java.util.UUID;
import lombok.Builder;
import on.logistics.hubservice.presentation.dtos.request.RetrievalLogisticsRequest;

@Builder
public record RetrievalLogisticsRequestDto(
    UUID hubId,
    List<UUID> retrievalLogisticsIds
) {

    public static RetrievalLogisticsRequestDto of(UUID hubId, RetrievalLogisticsRequest request) {
        List<UUID> logisticsUuids = request.retrievalLogisticsIds().stream()
            .map(UUID::fromString)
            .toList();
        return RetrievalLogisticsRequestDto.builder()
            .hubId(hubId)
            .retrievalLogisticsIds(logisticsUuids)
            .build();
    }
}
