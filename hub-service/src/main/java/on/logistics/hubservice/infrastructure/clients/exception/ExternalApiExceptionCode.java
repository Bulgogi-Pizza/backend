package on.logistics.hubservice.infrastructure.clients.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import on.logistics.hubservice.global.exception.ExceptionCode;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ExternalApiExceptionCode implements ExceptionCode {

    MAP_NOT_FOUND(HttpStatus.NOT_FOUND, "Map not found"),
    MAP_BAD_REQUEST(HttpStatus.BAD_REQUEST, "Map bad request"),
    MAP_CLIENT_ERROR(HttpStatus.BAD_REQUEST, "Map client error"),
    MAP_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "Map server error"),
    WRONG_RESPONSE_TYPE(HttpStatus.INTERNAL_SERVER_ERROR, "Wrong response type"),
    ;

    private final HttpStatus httpStatus;
    private final String message;
}
