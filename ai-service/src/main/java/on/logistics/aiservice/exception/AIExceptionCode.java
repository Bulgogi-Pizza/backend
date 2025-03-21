package on.logistics.aiservice.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import on.logistics.aiservice.global.exception.ExceptionCode;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum AIExceptionCode implements ExceptionCode {

    ;

    private final HttpStatus httpStatus;
    private final String message;
}
