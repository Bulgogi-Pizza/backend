package on.logistics.hubtransitservice.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import on.logistics.hubtransitservice.global.exception.ExceptionCode;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum HubTransitExceptionCode implements ExceptionCode {
    HUB_NOT_FOUND(HttpStatus.NOT_FOUND, "허브를 찾을 수 없습니다."),
    HUB_TRANSIT_NOT_FOUND(HttpStatus.NOT_FOUND, "허브 간 이동정보를 찾을 수 없습니다."),
    HUB_NAME_IS_NULL(HttpStatus.BAD_REQUEST, "허브명은 필수 입력 사항입니다."),
    MAX_LENGTH_EXCEEDED(HttpStatus.BAD_REQUEST, "허브명은 100자를 초과할 수 없습니다."),
    ROUTE_NOT_FOUND(HttpStatus.NOT_FOUND, "경로를 찾을 수 없습니다."),
    ROUTE_INVALID(HttpStatus.BAD_REQUEST, "유효하지 않은 경로입니다"),
    ROUTE_JSON_PARSE_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "경로 파싱 도중 오류가 발생했습니다.");

    private final HttpStatus httpStatus;
    private final String message;
}
