package on.logistics.authservice.exception;

import on.logistics.authservice.global.exception.CustomException;
import on.logistics.authservice.global.exception.ExceptionCode;

public class AuthException extends CustomException {

    public AuthException(ExceptionCode exceptionCode) {
        super(exceptionCode);
    }

}
