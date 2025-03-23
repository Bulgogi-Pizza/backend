package on.logistics.slackservice.exception;

import on.logistics.slackservice.global.exception.CustomException;
import on.logistics.slackservice.global.exception.ExceptionCode;

public class SlackException extends CustomException {

    public SlackException(ExceptionCode exceptionCode) {
        super(exceptionCode);
    }
}
