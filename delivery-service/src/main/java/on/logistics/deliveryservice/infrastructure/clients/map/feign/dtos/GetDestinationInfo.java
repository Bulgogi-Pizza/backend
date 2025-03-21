package on.logistics.deliveryservice.infrastructure.clients.map.feign.dtos;

public record GetDestinationInfo(String roadAddress, String jibunAddress, String longitude,
                                 String latitude) {

}
