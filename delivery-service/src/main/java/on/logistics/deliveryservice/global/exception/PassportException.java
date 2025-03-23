package on.logistics.deliveryservice.global.exception;

public class PassportException extends CustomException {

    public PassportException(ExceptionCode exceptionCode) {
        super(exceptionCode);
    }
}