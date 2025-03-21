package on.logistics.slackservice.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import on.logistics.slackservice.global.exception.ExceptionCode;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum SlackExceptionCode implements ExceptionCode {
    MESSAGE_SEND_FAILED(HttpStatus.INTERNAL_SERVER_ERROR, "슬랙 메세지 전송 도중 오류가 발생했습니다."),
    UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "권한이 없습니다."),
    SLACK_MESSAGE_NOT_FOUND(HttpStatus.NOT_FOUND, "슬랙 메세지를 찾을 수 없습니다.");

    private final HttpStatus httpStatus;
    private final String message;
}
