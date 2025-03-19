package on.logistics.deliveryservice.infrastructure.querydsl;

import on.logistics.deliveryservice.application.dtos.request.SearchDeliveryRecordRequestDto;
import on.logistics.deliveryservice.domain.entity.DeliveryRecord;
import org.springframework.data.domain.Page;

public interface DeliveryRecordQueryRepository {

    Page<DeliveryRecord> searchDeliveryRecord(SearchDeliveryRecordRequestDto requestDto);
}
