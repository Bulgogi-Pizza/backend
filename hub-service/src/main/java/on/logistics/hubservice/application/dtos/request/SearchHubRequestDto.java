package on.logistics.hubservice.application.dtos.request;

import lombok.Builder;
import on.logistics.hubservice.domain.entity.HubType;
import org.springframework.data.domain.Pageable;

@Builder
public record SearchHubRequestDto(
    String keyword,
    HubType type,
    Pageable pageable
) {

    public static SearchHubRequestDto of(String keyword, HubType type, Pageable pageable) {
        return SearchHubRequestDto.builder()
            .keyword(keyword)
            .type(type)
            .pageable(pageable)
            .build();
    }
}
