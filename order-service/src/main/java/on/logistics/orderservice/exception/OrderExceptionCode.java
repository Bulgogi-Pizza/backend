package on.logistics.orderservice.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import on.logistics.orderservice.global.exception.ExceptionCode;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum OrderExceptionCode implements ExceptionCode {

    OUT_OF_STOCK_PRODUCT(HttpStatus.BAD_REQUEST, "주문하신 상품의 재고가 부족합니다."),
    ORDER_NOT_FOUND(HttpStatus.NOT_FOUND, "주문을 찾을 수 없습니다."),
    VENDOR_ORDER_NOT_FOUND(HttpStatus.NOT_FOUND, "판매자 주문을 찾을 수 없습니다."),
    ORDER_PRODUCT_NOT_FOUND(HttpStatus.NOT_FOUND, "주문 상품을 찾을 수 없습니다."),
    ORDER_ACCESS_DENIED(HttpStatus.FORBIDDEN, "권한이 없는 엔드포인트로의 접근이 거부되었습니다."),
    ;

    private final HttpStatus httpStatus;
    private final String message;
}
