package on.logistics.hubservice.presentation.dtos.request;

import java.util.List;

public record RetrievalLogisticsRequest(
    List<String> retrievalLogisticsIds
) {

}
