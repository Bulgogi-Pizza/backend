package on.logistics.deliveryservice.application.dtos;

public record DeliveryUserInfoDto(String recipient, String recipientSlackEmail) {

    public static DeliveryUserInfoDto of(String recipient, String recipientSlackEmail) {
        return new DeliveryUserInfoDto(recipient, recipientSlackEmail);
    }

}
