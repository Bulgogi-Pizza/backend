package on.logistics.hubtransitservice.infrastructure.clients.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import on.logistics.hubtransitservice.global.exception.ExceptionCode;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ExternalApiExceptionCode implements ExceptionCode {

    HUB_NOT_FOUND(HttpStatus.NOT_FOUND, "Hub not found"),
    HUB_BAD_REQUEST(HttpStatus.BAD_REQUEST, "Hub bad request"),
    HUB_CLIENT_ERROR(HttpStatus.BAD_REQUEST, "Hub client error"),
    HUB_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "Hub server error"),
    HUB_PARSING_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "Hub parsing error"),
    WRONG_RESPONSE_TYPE(HttpStatus.INTERNAL_SERVER_ERROR, "Wrong response type"),
    ;

    private final HttpStatus httpStatus;
    private final String message;
}