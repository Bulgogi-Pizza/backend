package on.logistics.authservice.presentation.dtos;

public record AuthValidateResponse (
    String passportId
) {
    public static AuthValidateResponse of(String passportId) {
        return new AuthValidateResponse(passportId);
    }
}
