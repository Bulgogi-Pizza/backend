package on.logistics.hubtransitservice.exception;

import on.logistics.hubtransitservice.global.exception.CustomException;
import on.logistics.hubtransitservice.global.exception.ExceptionCode;

public class HubTransitException extends CustomException {

    public HubTransitException(ExceptionCode exceptionCode) {
        super(exceptionCode);
    }

}
