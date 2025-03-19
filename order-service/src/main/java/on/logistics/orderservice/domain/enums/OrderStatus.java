package on.logistics.orderservice.domain.enums;

public enum OrderStatus {
  ORDER_CREATED,
  SHIPPED,
  IN_TRANSIT,
  DELIVERED,
  CANCELLED,
  RETURNED,
  ;

  public static boolean isBeforeShipped(OrderStatus status) {
    return status == ORDER_CREATED;
  }
}
