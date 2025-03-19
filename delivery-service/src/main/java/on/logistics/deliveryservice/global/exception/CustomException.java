package on.logistics.deliveryservice.global.exception;


import lombok.Getter;
import on.logistics.deliveryservice.infrastructure.client.exception.ExternalApiExceptionCode;

@Getter
public class CustomException extends RuntimeException {

    private final ExceptionCode exception;

    public CustomException(ExternalApiExceptionCode e) {
        super(e.getMessage());
        this.exception = e;
    }
}