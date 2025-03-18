package on.logistics.orderservice.infrastructure.clients.exception;

import on.logistics.orderservice.global.exception.CustomException;

public class ExternalApiException extends CustomException {

  public ExternalApiException(ExternalApiExceptionCode e) {
    super(e);
  }

  public static class ExternalApiNotFoundException extends ExternalApiException {

    public ExternalApiNotFoundException() {
      super(ExternalApiExceptionCode.COMPANY_NOT_FOUND);
    }
  }

  public static class ExternalApiBadRequestException extends ExternalApiException {

    public ExternalApiBadRequestException() {
      super(ExternalApiExceptionCode.COMPANY_BAD_REQUEST);
    }
  }

  public static class ExternalApiClientException extends ExternalApiException {

    public ExternalApiClientException() {
      super(ExternalApiExceptionCode.COMPANY_CLIENT_ERROR);
    }

  }

  public static class ExternalApiServerException extends ExternalApiException {

    public ExternalApiServerException() {
      super(ExternalApiExceptionCode.COMPANY_SERVER_ERROR);
    }
  }

  public static class WrongResponseTypeApiException extends ExternalApiException {

    public WrongResponseTypeApiException() {
      super(ExternalApiExceptionCode.WRONG_RESPONSE_TYPE);
    }
  }
}
