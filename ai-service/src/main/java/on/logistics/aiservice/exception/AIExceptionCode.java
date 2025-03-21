package on.logistics.aiservice.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import on.logistics.aiservice.global.exception.ExceptionCode;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum AIExceptionCode implements ExceptionCode {

    AI_SERVICE_INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "AI 서비스 응답이 없습니다."),;

    private final HttpStatus httpStatus;
    private final String message;
}
