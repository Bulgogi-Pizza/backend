package on.logistics.orderservice.global.exception.passport;

import on.logistics.orderservice.global.exception.CustomException;
import on.logistics.orderservice.global.exception.ExceptionCode;

public class PassportException extends CustomException {

    public PassportException(PassportExceptionCode exceptionCode) {
        super(exceptionCode);
    }

    public static class  PassportRetrievalFailedException extends PassportException {
        public PassportRetrievalFailedException() {
            super(PassportExceptionCode.PASSPORT_RETRIEVAL_FAILED);
        }
    }

    public static class PassportValidationFailedException extends PassportException {
        public PassportValidationFailedException() {
            super(PassportExceptionCode.PASSPORT_VALIDATION_FAILED);
        }
    }
}
