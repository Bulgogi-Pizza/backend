package on.logistics.authservice.exception;

import on.logistics.authservice.global.exception.CustomException;
import on.logistics.authservice.global.exception.ExceptionCode;

public class PassportException extends CustomException {

    public PassportException(ExceptionCode exceptionCode) {
        super(exceptionCode);
    }
}
