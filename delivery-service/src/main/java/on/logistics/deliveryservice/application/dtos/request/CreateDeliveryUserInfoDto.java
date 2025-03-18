package on.logistics.deliveryservice.application.dtos.request;

public record CreateDeliveryUserInfoDto(String recipient, String recipientSlackEmail) {

    public static CreateDeliveryUserInfoDto of(String recipient, String recipientSlackEmail) {
        return new CreateDeliveryUserInfoDto(recipient, recipientSlackEmail);
    }

}
