package oop.labs.lab4.api;

import oop.labs.lab4.ApplicationException;

public class ApiException extends ApplicationException
{
    public ApiException() {}
    public ApiException(String msg) { super(msg); }
    public ApiException(Throwable cause) { super(cause); }
}
