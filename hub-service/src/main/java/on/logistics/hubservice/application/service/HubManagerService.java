package on.logistics.hubservice.application.service;

import java.util.UUID;
import lombok.RequiredArgsConstructor;
import on.logistics.hubservice.application.dtos.GetHubManagerIdResponseDto;
import on.logistics.hubservice.application.dtos.request.CreateHubManagerRequestDto;
import on.logistics.hubservice.application.dtos.request.ValidHubManagerRequestDto;
import on.logistics.hubservice.domain.entity.HubManager;
import on.logistics.hubservice.domain.repository.HubManagerRepository;
import on.logistics.hubservice.exception.HubManagerException.HubManagerNotFoundException;
import on.logistics.hubservice.global.util.PassportUtil;
import on.logistics.hubservice.presentation.dtos.response.CreateHubManagerResponse;
import on.logistics.hubservice.presentation.dtos.response.ValidHubManagerResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class HubManagerService {

    private final HubManagerRepository hubManagerRepository;
    private final PassportUtil passportUtil;

    @Transactional
    public CreateHubManagerResponse createHubManager(CreateHubManagerRequestDto requestDto
    ) {
        HubManager hubManager = HubManager.create(requestDto);
        HubManager savedHubManager = hubManagerRepository.save(hubManager);
        return CreateHubManagerResponse.of(savedHubManager.getId());
    }

    @Transactional
    public ValidHubManagerResponse validHubManager(ValidHubManagerRequestDto requestDto) {
        boolean isExistHubManager = hubManagerRepository.findByUserIdAndHubId(requestDto.userId(),
            requestDto.hubId()).isPresent();
        return ValidHubManagerResponse.of(isExistHubManager);
    }

    public GetHubManagerIdResponseDto getHubManagerId(UUID hubId) {
        HubManager hubManager = hubManagerRepository.findByHubId(hubId)
            .orElseThrow(HubManagerNotFoundException::new);
        return new GetHubManagerIdResponseDto(hubManager.getId());
    }
}
