package on.logistics.hubtransitservice.global.exception.passport;

import on.logistics.hubtransitservice.global.exception.CustomException;
import on.logistics.hubtransitservice.global.exception.ExceptionCode;

public class PassportException extends CustomException {

    public PassportException(ExceptionCode exceptionCode) {
        super(exceptionCode);
    }
}