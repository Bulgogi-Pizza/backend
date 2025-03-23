package on.logistics.hubtransitservice.application;

import jakarta.servlet.http.HttpServletRequest;
import java.util.UUID;
import on.logistics.hubtransitservice.application.dtos.request.CreateHubTransitRequestDto;
import on.logistics.hubtransitservice.application.dtos.request.GetNextHubRequestDto;
import on.logistics.hubtransitservice.application.dtos.request.InboundHubTransitRequestDto;
import on.logistics.hubtransitservice.application.dtos.request.UpdateHubTransitRequestDto;
import on.logistics.hubtransitservice.presentation.dtos.response.CreateHubTransitResponse;
import on.logistics.hubtransitservice.presentation.dtos.response.GetHubTransitResponse;
import on.logistics.hubtransitservice.presentation.dtos.response.GetNextHubResponse;
import on.logistics.hubtransitservice.presentation.dtos.response.SearchHubTransitResponse;
import on.logistics.hubtransitservice.presentation.dtos.response.UpdateHubTransitResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface HubTransitService {

    CreateHubTransitResponse createHubTransit(CreateHubTransitRequestDto requestDto,
        HttpServletRequest httpServletRequest);

    void processInboundHubTransit(InboundHubTransitRequestDto requestDto,
        HttpServletRequest httpServletRequest);

    GetHubTransitResponse getHubTransit(UUID transitId);

    Page<SearchHubTransitResponse> searchHubTransit(String keyword, Pageable pageable);

    GetNextHubResponse getNextHubTransit(GetNextHubRequestDto requestDto);

    UpdateHubTransitResponse updateHubTransit(UpdateHubTransitRequestDto requestDto);

    void deleteHubTransit(UUID transitId);

}
