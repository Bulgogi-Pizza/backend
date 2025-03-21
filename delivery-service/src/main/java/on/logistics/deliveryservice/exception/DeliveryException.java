package on.logistics.deliveryservice.exception;

import on.logistics.deliveryservice.global.exception.CustomException;
import on.logistics.deliveryservice.global.exception.ExceptionCode;

public class DeliveryException extends CustomException {

    public DeliveryException(ExceptionCode exceptionCode) {
        super(exceptionCode);
    }
}
