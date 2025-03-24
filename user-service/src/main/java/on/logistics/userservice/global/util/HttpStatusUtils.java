package on.logistics.userservice.global.util;

import lombok.NoArgsConstructor;

@NoArgsConstructor(access = lombok.AccessLevel.PRIVATE)
public class HttpStatusUtils {

    public static boolean is2xxSuccessful(int statusCode) {
        return statusCode >= 200 && statusCode < 300;
    }

    public static boolean is4xxClientError(int statusCode) {
        return statusCode >= 400 && statusCode < 500;
    }

    public static boolean isResponseBadRequest(int status) {
        return status == 400;
    }

    public static boolean isResponseNotFound(int status) {
        return status == 404;
    }

    public static boolean is5xxServerError(int statusCode) {
        return statusCode >= 500 && statusCode < 600;
    }

}