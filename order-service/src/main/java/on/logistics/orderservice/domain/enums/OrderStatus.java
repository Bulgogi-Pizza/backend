package on.logistics.orderservice.domain.enums;

public enum OrderStatus {
  ORDER_CREATED,
  SHIPPED,
  IN_TRANSIT,
  DELIVERED,
  CANCELLED,
  RETURNED,
  IN_RETURN,
  RETURN_REQUESTED;

  public static boolean isBeforeShipped(OrderStatus status) {
    return status == ORDER_CREATED;
  }

  public static boolean isAfterDelivered(OrderStatus status) {
    return status == DELIVERED || status == RETURNED || status == CANCELLED;
  }
}
