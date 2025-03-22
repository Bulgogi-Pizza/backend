package on.logistics.hubservice.application.service;

import lombok.RequiredArgsConstructor;
import on.logistics.hubservice.application.dtos.request.CreateHubManagerRequestDto;
import on.logistics.hubservice.domain.entity.HubManager;
import on.logistics.hubservice.domain.repository.HubManagerRepository;
import on.logistics.hubservice.presentation.dtos.response.CreateHubManagerResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class HubManagerService {

    private final HubManagerRepository hubManagerRepository;

    @Transactional
    public CreateHubManagerResponse createHubManager(CreateHubManagerRequestDto requestDto) {
        HubManager hubManager = HubManager.create(requestDto);
        HubManager savedHubManager = hubManagerRepository.save(hubManager);
        return CreateHubManagerResponse.of(savedHubManager.getId());
    }
}
