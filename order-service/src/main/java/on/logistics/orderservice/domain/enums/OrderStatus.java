package on.logistics.orderservice.domain.enums;

public enum OrderStatus {
  ORDER_CREATED,
  SHIPPED,
  IN_TRANSIT,
  DELIVERED,
  CANCELLED,
  RETURNED,
  ;

  public static boolean isAbleToCancel(OrderStatus status) {
    return status == ORDER_CREATED;
  }
}
