package on.logistics.productservice.global.exception;

public class PassportException extends CustomException {

    public PassportException(ExceptionCode exceptionCode) {
        super(exceptionCode);
    }
}