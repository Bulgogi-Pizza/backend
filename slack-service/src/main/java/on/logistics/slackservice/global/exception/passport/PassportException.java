package on.logistics.slackservice.global.exception.passport;

import on.logistics.slackservice.global.exception.CustomException;
import on.logistics.slackservice.global.exception.ExceptionCode;

public class PassportException extends CustomException {

    public PassportException(ExceptionCode exceptionCode) {
        super(exceptionCode);
    }
}