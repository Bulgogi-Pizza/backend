package on.logistics.hubservice.application.dtos.request;

import java.util.List;
import java.util.UUID;
import lombok.Builder;
import on.logistics.hubservice.presentation.dtos.request.StorageLogisticsRequest;

@Builder
public record StorageLogisticsRequestDto(
    UUID hubId,
    List<UUID> storageLogisticsIds
) {

    public static StorageLogisticsRequestDto of(UUID hubId, StorageLogisticsRequest request) {
        List<UUID> logisticsUuids = request.storageLogisticsIds().stream()
            .map(UUID::fromString)
            .toList();
        return StorageLogisticsRequestDto.builder()
            .hubId(hubId)
            .storageLogisticsIds(logisticsUuids)
            .build();
    }
}
