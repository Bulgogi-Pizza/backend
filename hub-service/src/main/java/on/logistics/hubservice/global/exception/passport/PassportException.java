package on.logistics.hubservice.global.exception.passport;

import on.logistics.hubservice.global.exception.CustomException;
import on.logistics.hubservice.global.exception.ExceptionCode;

public class PassportException extends CustomException {

    public PassportException(ExceptionCode exceptionCode) {
        super(exceptionCode);
    }
}
