package on.logistics.hubservice.application.service;

import java.math.BigDecimal;
import lombok.RequiredArgsConstructor;
import on.logistics.hubservice.domain.entity.Hub;
import on.logistics.hubservice.domain.repository.HubRepository;
import on.logistics.hubservice.presentation.dtos.request.CreateHubRequestDto;
import on.logistics.hubservice.presentation.dtos.response.CreateHubResponse;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HubService {

    private final HubRepository hubRepository;

    public CreateHubResponse createHub(CreateHubRequestDto requestDto) {
        // TODO: 추후 네이버 API로 실제 좌표로 수정해야함
        BigDecimal latitude = new BigDecimal("37.5563");
        BigDecimal longitude = new BigDecimal("126.9707");
        Hub hub = Hub.createOf(requestDto, latitude, longitude);
        Hub savedHub = hubRepository.save(hub);
        return CreateHubResponse.of(savedHub.getId());
    }
}
