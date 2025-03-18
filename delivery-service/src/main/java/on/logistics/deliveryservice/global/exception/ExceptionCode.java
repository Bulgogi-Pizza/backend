package on.logistics.deliveryservice.global.exception;

import org.springframework.http.HttpStatus;

public interface ExceptionCode {

    String name();

    HttpStatus getHttpStatus();

    String getMessage();
}