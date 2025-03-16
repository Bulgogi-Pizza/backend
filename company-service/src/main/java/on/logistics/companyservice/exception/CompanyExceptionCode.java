package on.logistics.companyservice.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import on.logistics.companyservice.global.exception.ExceptionCode;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum CompanyExceptionCode implements ExceptionCode {
    COMPANY_NAME_IS_NULL(HttpStatus.BAD_REQUEST, "업체 이름은 필수 입력 값입니다"),
    COMPANY_NAME_MAX_LENGTH(HttpStatus.BAD_REQUEST, "업체의 최대 이름은 100자를 초과할 수 없습니다."),
    COMPANY_ADDRESS_IS_NULL(HttpStatus.BAD_REQUEST, "업체 주소는 필수 입력 값입니다.");
    private final HttpStatus httpStatus;
    private final String message;
}