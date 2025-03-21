package on.logistics.deliveryservice.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import on.logistics.deliveryservice.global.exception.ExceptionCode;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum DeliveryExceptionCode implements ExceptionCode {

    DELIVERY_RECIPIENT_IS_NULL(HttpStatus.BAD_REQUEST, "수령인 이름은 필수 입력 값입니다."),
    DELIVERY_RECIPIENT_SLACK_EMAIL_IS_NULL(HttpStatus.BAD_REQUEST, "수령인 슬랙 이메일은 필수 입력 값입니다."),
    DELIVERY_DESTINATION_IS_NULL(HttpStatus.BAD_REQUEST, "배송지는 필수 입력 값입니다."),
    DELIVERY_NOT_FOUND(HttpStatus.NOT_FOUND, "배송 ID를 찾을 수 없습니다."),
    DELIVERY_START_HUB_NOT_FOUND(HttpStatus.BAD_REQUEST, "스타트 허브를 찾을 수 없어 배송을 생성할 수 없습니다.");
    private final HttpStatus httpStatus;
    private final String message;

}
