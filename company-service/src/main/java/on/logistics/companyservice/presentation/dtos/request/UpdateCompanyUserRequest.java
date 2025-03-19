package on.logistics.companyservice.presentation.dtos.request;

import jakarta.validation.constraints.NotNull;
import java.util.UUID;

public record UpdateCompanyUserRequest(@NotNull(message = "userId는 필수 입력 값입니다.") UUID userId) {

}
