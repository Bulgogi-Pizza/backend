package on.logistics.hubtransitservice.infrastructure.clients.deliverymanager;

import java.util.UUID;

public interface DeliveryManagerServiceClient {

    UUID assignDeliveryManager(UUID deliveryId, UUID nextHubId, String nextDestinationType);

}
