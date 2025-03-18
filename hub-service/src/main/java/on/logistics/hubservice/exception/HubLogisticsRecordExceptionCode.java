package on.logistics.hubservice.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import on.logistics.hubservice.global.exception.ExceptionCode;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum HubLogisticsRecordExceptionCode implements ExceptionCode {

    HUB_LOGISTICS_RECORD_NOT_FOUND(HttpStatus.NOT_FOUND, "해당 허브 물류 기록을 찾을 수 없습니다");

    private final HttpStatus httpStatus;
    private final String message;
}
