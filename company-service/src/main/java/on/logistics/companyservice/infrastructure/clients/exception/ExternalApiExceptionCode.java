package on.logistics.companyservice.infrastructure.clients.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import on.logistics.companyservice.global.exception.ExceptionCode;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ExternalApiExceptionCode implements ExceptionCode {

    NOT_FOUND(HttpStatus.NOT_FOUND, "not found"),
    BAD_REQUEST(HttpStatus.BAD_REQUEST, "bad request"),
    CLIENT_ERROR(HttpStatus.BAD_REQUEST, "client error"),
    SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "server error"),
    WRONG_RESPONSE_TYPE(HttpStatus.INTERNAL_SERVER_ERROR, "response type"),
    ;

    private final HttpStatus httpStatus;
    private final String message;
}
