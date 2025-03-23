package on.logistics.hubservice.exception;

import on.logistics.hubservice.global.exception.CustomException;
import on.logistics.hubservice.global.exception.ExceptionCode;

public class HubManagerException extends CustomException {

    public HubManagerException(ExceptionCode e) {
        super(e);
    }

    public static class HubManagerNotFoundException extends HubManagerException {
        public HubManagerNotFoundException() {
            super(HubManagerExceptionCode.HUB_MANAGER_NOT_FOUND);
        }
    }
}
