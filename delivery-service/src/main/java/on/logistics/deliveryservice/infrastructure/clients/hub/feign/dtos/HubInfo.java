package on.logistics.deliveryservice.infrastructure.clients.hub.feign.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

public record HubInfo(String id, @JsonProperty("hubName") String name,
                      @JsonProperty("hubType") String type, String address, String latitude,
                      String longitude) {

}