package on.logistics.deliveryservice.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import on.logistics.deliveryservice.global.exception.ExceptionCode;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum DeliveryRecordExceptionCode implements ExceptionCode {
    DELIVERY_RECORD_NOT_FOUND(HttpStatus.NOT_FOUND, "배송 ID를 찾을 수 없습니다.");
    private final HttpStatus httpStatus;
    private final String message;
}
