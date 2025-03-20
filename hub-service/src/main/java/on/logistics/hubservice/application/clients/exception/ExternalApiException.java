package on.logistics.hubservice.application.clients.exception;

import on.logistics.hubservice.global.exception.CustomException;

public class ExternalApiException extends CustomException {

    public ExternalApiException(ExternalApiExceptionCode e) {
        super(e);
    }

    public static class ExternalApiNotFoundException extends ExternalApiException {

        public ExternalApiNotFoundException() {
            super(ExternalApiExceptionCode.MAP_NOT_FOUND);
        }
    }

    public static class ExternalApiBadRequestException extends ExternalApiException {

        public ExternalApiBadRequestException() {
            super(ExternalApiExceptionCode.MAP_BAD_REQUEST);
        }
    }

    public static class ExternalApiClientException extends ExternalApiException {

        public ExternalApiClientException() {
            super(ExternalApiExceptionCode.MAP_CLIENT_ERROR);
        }

    }

    public static class ExternalApiServerException extends ExternalApiException {

        public ExternalApiServerException() {
            super(ExternalApiExceptionCode.MAP_SERVER_ERROR);
        }
    }

    public static class WrongResponseTypeApiException extends ExternalApiException {

        public WrongResponseTypeApiException() {
            super(ExternalApiExceptionCode.WRONG_RESPONSE_TYPE);
        }
    }
}
