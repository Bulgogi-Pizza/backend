package on.logistics.deliveryservice.exception;

import on.logistics.deliveryservice.global.exception.CustomException;
import on.logistics.deliveryservice.global.exception.ExceptionCode;

public class DeliveryRecordException extends CustomException {

    public DeliveryRecordException(ExceptionCode exceptionCode) {
        super(exceptionCode);
    }
}
