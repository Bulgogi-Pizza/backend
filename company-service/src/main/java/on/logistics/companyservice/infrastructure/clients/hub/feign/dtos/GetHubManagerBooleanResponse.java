package on.logistics.companyservice.infrastructure.clients.hub.feign.dtos;

public record GetHubManagerBooleanResponse(Boolean isExist) {

    public static GetHubManagerBooleanResponse of(Boolean isExist) {
        return new GetHubManagerBooleanResponse(isExist);
    }

}
