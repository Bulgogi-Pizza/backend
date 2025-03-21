package on.logistics.aiservice.application.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import on.logistics.aiservice.application.service.dtos.GenerateShippingDeadlineRequestDto;
import on.logistics.aiservice.application.service.dtos.GenerateShippingDeadlineRequestDto.Product;
import on.logistics.aiservice.infrastructure.clients.dtos.ShippingDeadlineResponseDto;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AIServiceImpl implements AIService {

    private final AIApiService AIApiService;

    public ShippingDeadlineResponseDto generateShippingDeadline(
        GenerateShippingDeadlineRequestDto requestDto
    ) {
        log.info("generateShippingDeadline(): {}", requestDto);

        String prompt = generateShippingDeadlinePrompt(requestDto);
        return AIApiService.chat(prompt);
    }

    private String generateShippingDeadlinePrompt(GenerateShippingDeadlineRequestDto requestDto) {
        StringBuilder sb = new StringBuilder();
        String data = "[배송 데이터]\n"
            + getProductData(requestDto.products())
            + "- 출발 허브: " + requestDto.startHubName() + "\n"
            + "- 도착 허브: " + requestDto.endHubName() + "\n"
            + "- 도착지: " + requestDto.destination() + "\n"
            + "- 도착 희망 시한 (yyyy년 MM월 dd일 HH시): " + requestDto.arrivalDeadline() + "\n\n";

        String role = "당신은 물류 및 배송 최적화 전문가입니다. "
            + "다음 배송 데이터를 분석하여 도착 시한을 맞추기 위한 "
            + "최종 발송 시한(출발 허브에서 출발해야 하는 마감시간)을 결정해 주세요.\n\n";

        String request = "[분석 요청 사항]\n"
            + "1. 허브 간 이동 시간과 각 허브에서의 처리 시간을 고려해 주세요\n"
            + "2. 배송 경로상 발생할 수 있는 지연 요소(교통 상황, 날씨 등)를 고려한 버퍼 시간을 포함해 주세요\n"
            + "3. 상품의 종류와 수량에 따른 처리 시간 차이를 반영해 주세요\n"
            + "4. 도착지까지의 최종 배송에 필요한 시간을 계산해 주세요\n"
            + "5. 각 단계별 소요 시간을 명확히 표시해 주세요\n\n"
            + "또한 다음 변수들을 고려하여 판단해 주세요:\n"
            + "- 주말/공휴일 여부에 따른 처리 시간 차이\n"
            + "- 날씨 조건(비, 눈, 폭염 등)이 배송 시간에 미치는 영향\n";

        String responseType = "[응답 형식]\n"
            + "응답 형식은 무조건 YYYY-MM-DDTHH:MM:SS 형식을 따라야 합니다.\n"
            + "이외의 형식은 인식하지 않으며, 이외의 정보는 필요 없습니다.\n"
            + "- 최종 발송 시한: Java의 LocalDateTime 형식 [YYYY-MM-DDTHH:MM:SS]\n\n";

        return sb
            .append(data)
            .append(role)
            .append(request)
            .append(responseType)
            .toString();
    }

    private String getProductData(List<Product> products) {
        StringBuilder sb = new StringBuilder();
        sb.append("  [상품 리스트]\n");
        for (Product product : products) {
            sb
                .append("  - 상품: ").append(product.name())
                .append(", 수량: ").append(product.quantity())
                .append("\n");
        }
        return sb.toString();
    }
}
