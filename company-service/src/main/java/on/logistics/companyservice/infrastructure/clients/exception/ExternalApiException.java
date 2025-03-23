package on.logistics.companyservice.infrastructure.clients.exception;

import on.logistics.companyservice.global.exception.CustomException;

public class ExternalApiException extends CustomException {

    public ExternalApiException(ExternalApiExceptionCode e) {
        super(e);
    }

    public static class ExternalApiNotFoundException extends ExternalApiException {

        public ExternalApiNotFoundException() {
            super(ExternalApiExceptionCode.NOT_FOUND);
        }
    }

    public static class ExternalApiBadRequestException extends ExternalApiException {

        public ExternalApiBadRequestException() {
            super(ExternalApiExceptionCode.BAD_REQUEST);
        }
    }

    public static class ExternalApiClientException extends ExternalApiException {

        public ExternalApiClientException() {
            super(ExternalApiExceptionCode.CLIENT_ERROR);
        }

    }

    public static class ExternalApiServerException extends ExternalApiException {

        public ExternalApiServerException() {
            super(ExternalApiExceptionCode.SERVER_ERROR);
        }
    }

    public static class WrongResponseTypeApiException extends ExternalApiException {

        public WrongResponseTypeApiException() {
            super(ExternalApiExceptionCode.WRONG_RESPONSE_TYPE);
        }
    }
}