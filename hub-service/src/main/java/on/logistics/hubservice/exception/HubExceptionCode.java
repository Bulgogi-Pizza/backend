package on.logistics.hubservice.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import on.logistics.hubservice.global.exception.ExceptionCode;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum HubExceptionCode implements ExceptionCode {

    HUB_NAME_IS_NULL(HttpStatus.BAD_REQUEST, "허브 이름은 필수 입력 값입니다"),
    HUB_TYPE_INVALID(HttpStatus.BAD_REQUEST, "허브 타입이 잘못된 값입니다"),
    HUB_ADDRESS_IS_NULL(HttpStatus.BAD_REQUEST, "허브 주소는 필수 입력 값입니다"),
    HUB_NOT_FOUND(HttpStatus.NOT_FOUND, "해당 허브를 찾을 수 없습니다");

    private final HttpStatus httpStatus;
    private final String message;
}
