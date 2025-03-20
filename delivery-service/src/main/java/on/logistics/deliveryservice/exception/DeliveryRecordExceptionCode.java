package on.logistics.deliveryservice.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import on.logistics.deliveryservice.global.exception.ExceptionCode;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum DeliveryRecordExceptionCode implements ExceptionCode {
    DELIVERY_RECORD_NOT_FOUND(HttpStatus.NOT_FOUND, "배송 기록 ID를 찾을 수 없습니다."),
    DELIVERY_RECORD_DELIVERY_STATUS_CANCEL(HttpStatus.BAD_REQUEST, "취소된 배송에 대한 배송 기록 상태 변경 요청입니다.");
    private final HttpStatus httpStatus;
    private final String message;
}
