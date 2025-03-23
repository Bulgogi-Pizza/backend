package on.logistics.companyservice.presentation.dtos.request;

import jakarta.validation.constraints.NotNull;
import java.util.UUID;

public record UpdateCompanyHubRequest(@NotNull(message = "허브 ID는 필수 입력 조건입니다.") UUID managedHubId) {

}
