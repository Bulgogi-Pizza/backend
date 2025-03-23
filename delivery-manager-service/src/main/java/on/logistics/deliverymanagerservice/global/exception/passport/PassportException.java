package on.logistics.deliverymanagerservice.global.exception.passport;

import on.logistics.deliverymanagerservice.global.exception.CustomException;
import on.logistics.deliverymanagerservice.global.exception.ExceptionCode;

public class PassportException extends CustomException {

    public PassportException(ExceptionCode exceptionCode) {
        super(exceptionCode);
    }
}
