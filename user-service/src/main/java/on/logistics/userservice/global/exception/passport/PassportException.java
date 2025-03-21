package on.logistics.userservice.global.exception.passport;


import on.logistics.userservice.global.exception.CustomException;
import on.logistics.userservice.global.exception.ExceptionCode;

public class PassportException extends CustomException {

    public PassportException(ExceptionCode exceptionCode) {
        super(exceptionCode);
    }
}
