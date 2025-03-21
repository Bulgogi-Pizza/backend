package on.logistics.aiservice.exception;

import on.logistics.aiservice.global.exception.CustomException;

public class AIException extends CustomException {

    public AIException(AIExceptionCode e) {
        super(e);
    }
}
