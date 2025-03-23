package on.logistics.slackservice.global.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Response;
import java.io.IOException;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import on.logistics.slackservice.global.presentation.dtos.CommonResponse;
import on.logistics.slackservice.infrastructure.clients.exception.ExternalApiException.ExternalApiBadRequestException;
import on.logistics.slackservice.infrastructure.clients.exception.ExternalApiException.ExternalApiClientException;
import on.logistics.slackservice.infrastructure.clients.exception.ExternalApiException.ExternalApiNotFoundException;
import on.logistics.slackservice.infrastructure.clients.exception.ExternalApiException.ExternalApiServerException;
import on.logistics.slackservice.infrastructure.clients.exception.ExternalApiException.WrongResponseTypeApiException;

@NoArgsConstructor(access = lombok.AccessLevel.PRIVATE)
@Slf4j
public class FeignClientResponseUtils {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static <T> T getBody(Response response, Class<T> responseType) {
        validateResponseStatus(response);
        return parseResponseBody(response, responseType);
    }

    public static void validateResponseStatus(Response response) {
        int statusCode = response.status();
        if (HttpStatusUtils.isResponseNotFound(statusCode)) {
            throw new ExternalApiNotFoundException();
        }
        if (HttpStatusUtils.isResponseBadRequest(statusCode)) {
            throw new ExternalApiBadRequestException();
        }
        if (HttpStatusUtils.is4xxClientError(statusCode)) {
            throw new ExternalApiClientException();
        }
        if (HttpStatusUtils.is5xxServerError(statusCode)) {
            throw new ExternalApiServerException();
        }
    }

    public static <T> T parseResponseBody(Response response, Class<T> responseType) {
        log.info("응답 바디 파싱");
        try {
            CommonResponse commonResponse = objectMapper.readValue(
                response.body().asInputStream(),
                objectMapper.getTypeFactory().constructType(CommonResponse.class));
            log.info("응답: {}", commonResponse);
            if (commonResponse == null) {
                return null;
            }
            return objectMapper.readValue(
                objectMapper.writeValueAsString(commonResponse.data()),
                responseType
            );
        } catch (IOException e) {
            log.error("잘못된 응답 형식입니다.", e);
            throw new WrongResponseTypeApiException();
        }
    }
}
