package on.logistics.hubservice.presentation.dtos.request;

import jakarta.validation.constraints.NotBlank;

public record UpdateHubRequest(
    @NotBlank String hubName,
    @NotBlank String hubType,
    @NotBlank String hubAddress
) {

}
