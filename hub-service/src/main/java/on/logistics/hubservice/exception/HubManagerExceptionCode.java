package on.logistics.hubservice.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import on.logistics.hubservice.global.exception.ExceptionCode;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum HubManagerExceptionCode implements ExceptionCode {

    HUB_MANAGER_NOT_FOUND(HttpStatus.NOT_FOUND, "Hub manager not found"),
    ;

    private final HttpStatus httpStatus;
    private final String message;
}
