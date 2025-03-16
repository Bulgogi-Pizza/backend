package org.onlogistics.mapservice.exception;

import org.onlogistics.mapservice.global.exception.CustomException;

public class MapException extends CustomException {

    public MapException(MapExceptionCode mapExceptionCode) {
        super(mapExceptionCode);
    }

    public static class InvalidResponseException extends MapException {

        public InvalidResponseException() {
            super(MapExceptionCode.INVALID_RESPONSE);
        }

    }

    public static class ExternalApiCallException extends MapException {

        public ExternalApiCallException() {
            super(MapExceptionCode.EXTERNAL_API_ERROR);
        }

    }

}
