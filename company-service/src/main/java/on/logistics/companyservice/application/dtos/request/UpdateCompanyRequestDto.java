package on.logistics.companyservice.application.dtos.request;

import on.logistics.companyservice.presentation.dtos.request.UpdateCompanyRequest;

public record UpdateCompanyRequestDto(String companyName, String companyAddress) {

    public static UpdateCompanyRequestDto from(UpdateCompanyRequest dto) {
        return new UpdateCompanyRequestDto(dto.companyName(), dto.companyAddress());
    }
}
