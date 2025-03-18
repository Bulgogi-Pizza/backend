package on.logistics.orderservice.application.clients.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import on.logistics.orderservice.global.exception.ExceptionCode;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ExternalApiExceptionCode implements ExceptionCode {

  COMPANY_NOT_FOUND(HttpStatus.NOT_FOUND, "Company not found"),
  COMPANY_BAD_REQUEST(HttpStatus.BAD_REQUEST, "Company bad request"),
  COMPANY_CLIENT_ERROR(HttpStatus.BAD_REQUEST, "Company client error"),
  COMPANY_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "Company server error"),
  WRONG_RESPONSE_TYPE(HttpStatus.INTERNAL_SERVER_ERROR, "Wrong response type"),
  ;

  private final HttpStatus httpStatus;
  private final String message;
}
