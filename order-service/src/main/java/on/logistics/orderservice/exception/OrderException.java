package on.logistics.orderservice.exception;

import on.logistics.orderservice.global.exception.CustomException;

public class OrderException extends CustomException {

    public OrderException(OrderExceptionCode orderExceptionCode) {
        super(orderExceptionCode);
    }

    public static class OutOfStockProductOrderException extends OrderException {

        public OutOfStockProductOrderException() {
            super(OrderExceptionCode.OUT_OF_STOCK_PRODUCT);
        }
    }

    public static class OrderNotFoundException extends OrderException {

        public OrderNotFoundException() {
            super(OrderExceptionCode.ORDER_NOT_FOUND);
        }
    }

    public static class VendorOrderNotFoundException extends OrderException {

        public VendorOrderNotFoundException() {
            super(OrderExceptionCode.VENDOR_ORDER_NOT_FOUND);
        }
    }

    public static class OrderProductNotFoundException extends OrderException {

        public OrderProductNotFoundException() {
            super(OrderExceptionCode.ORDER_PRODUCT_NOT_FOUND);
        }
    }
}
