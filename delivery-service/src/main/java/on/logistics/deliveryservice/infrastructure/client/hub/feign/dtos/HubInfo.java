package on.logistics.deliveryservice.infrastructure.client.hub.feign.dtos;

public record HubInfo(String id, String name, String type, String address, String latitude,
                      String longitude) {

}