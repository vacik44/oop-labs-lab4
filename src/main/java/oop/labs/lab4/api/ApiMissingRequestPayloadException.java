package oop.labs.lab4.api;

public class ApiMissingRequestPayloadException extends ApiException
{
    public ApiMissingRequestPayloadException() {}
    public ApiMissingRequestPayloadException(String msg) { super(msg); }
    public ApiMissingRequestPayloadException(Throwable cause) { super(cause); }


    public static String throwIfMissing(String payload) throws ApiMissingRequestPayloadException
    {
        return throwIfMissing(payload, "Request payload expected but missing.");
    }

    public static String throwIfMissing(String payload, String message) throws ApiMissingRequestPayloadException
    {
        if (payload == null) throw new ApiMissingRequestPayloadException(message);
        return payload;
    }
}
