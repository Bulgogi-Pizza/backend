package on.logistics.hubservice.infrastructure.clients.map.feign.dtos;

public record GetGeocodeResponse(
    String roadAddress,
    String jibunAddress,
    String longitude,
    String latitude
) {

}
