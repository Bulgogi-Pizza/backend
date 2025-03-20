package on.logistics.authservice.exception;

import on.logistics.authservice.global.exception.CustomException;
import on.logistics.authservice.global.exception.ExceptionCode;

public class HashException extends CustomException {

    public HashException(ExceptionCode exceptionCode) {
        super(exceptionCode);
    }

}
