package on.logistics.authservice.exception;

import on.logistics.authservice.global.exception.CustomException;
import on.logistics.authservice.global.exception.ExceptionCode;

public class JwtException extends CustomException {

    public JwtException(ExceptionCode exceptionCode) {
        super(exceptionCode);
    }

}
