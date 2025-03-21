package on.logistics.userservice.exception;

import on.logistics.userservice.global.exception.CustomException;
import on.logistics.userservice.global.exception.ExceptionCode;

public class UserException extends CustomException {

    public UserException(ExceptionCode exceptionCode) {
        super(exceptionCode);
    }

}
