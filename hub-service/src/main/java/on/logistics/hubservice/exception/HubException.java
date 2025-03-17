package on.logistics.hubservice.exception;

import on.logistics.hubservice.global.exception.CustomException;
import on.logistics.hubservice.global.exception.ExceptionCode;

public class HubException extends CustomException {

    public HubException(ExceptionCode exceptionCode) {
        super(exceptionCode);
    }
}
