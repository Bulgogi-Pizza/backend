package on.logistics.deliveryservice.infrastructure.clients.hub.feign.dtos;

public record HubInfo(String id, String name, String type, String address, String latitude,
                      String longitude) {

}