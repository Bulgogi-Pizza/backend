package on.logistics.productservice.global.exception;


import lombok.Getter;
import on.logistics.companyservice.global.exception.ExceptionCode;

@Getter
public class CustomException extends RuntimeException {

    private final ExceptionCode exception;

    public CustomException(ExceptionCode e) {
        super(e.getMessage());
        this.exception = e;
    }
}