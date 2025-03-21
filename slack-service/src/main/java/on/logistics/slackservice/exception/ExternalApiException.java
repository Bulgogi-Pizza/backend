package on.logistics.slackservice.exception;

import on.logistics.slackservice.global.exception.CustomException;

public class ExternalApiException extends CustomException {

    public ExternalApiException(ExternalApiExceptionCode e) {
        super(e);
    }

    public static class ExternalApiNotFoundException extends ExternalApiException {

        public ExternalApiNotFoundException() {
            super(ExternalApiExceptionCode.HUB_NOT_FOUND);
        }
    }

    public static class ExternalApiBadRequestException extends ExternalApiException {

        public ExternalApiBadRequestException() {
            super(ExternalApiExceptionCode.HUB_BAD_REQUEST);
        }
    }

    public static class ExternalApiClientException extends ExternalApiException {

        public ExternalApiClientException() {
            super(ExternalApiExceptionCode.HUB_CLIENT_ERROR);
        }

    }

    public static class ExternalApiServerException extends ExternalApiException {

        public ExternalApiServerException() {
            super(ExternalApiExceptionCode.HUB_SERVER_ERROR);
        }
    }

    public static class WrongResponseTypeApiException extends ExternalApiException {

        public WrongResponseTypeApiException() {
            super(ExternalApiExceptionCode.WRONG_RESPONSE_TYPE);
        }
    }
}
