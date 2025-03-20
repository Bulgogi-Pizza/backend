package on.logistics.orderservice.global.enums;

public enum AuthRole {
    MASTER,
    HUB_MANAGER,
    DELIVERY_MANAGER,
    COMPANY_MANAGER,
    ;

    public static boolean isAllowedSearchingOtherUserOrders(AuthRole role) {
        return role != null
            && (role.equals(MASTER) || role.equals(HUB_MANAGER) || role.equals(DELIVERY_MANAGER));
    }
}
