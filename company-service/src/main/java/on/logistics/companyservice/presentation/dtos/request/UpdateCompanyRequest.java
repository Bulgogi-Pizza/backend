package on.logistics.companyservice.presentation.dtos.request;

import jakarta.validation.constraints.NotBlank;

public record UpdateCompanyRequest(@NotBlank String companyName,
                                   @NotBlank String companyAddress) {

}