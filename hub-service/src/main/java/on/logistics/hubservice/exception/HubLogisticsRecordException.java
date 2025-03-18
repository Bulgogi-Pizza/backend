package on.logistics.hubservice.exception;

import on.logistics.hubservice.global.exception.CustomException;
import on.logistics.hubservice.global.exception.ExceptionCode;

public class HubLogisticsRecordException extends CustomException {

    public HubLogisticsRecordException(ExceptionCode exceptionCode) {
        super(exceptionCode);
    }
}
