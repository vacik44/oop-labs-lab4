package oop.labs.lab4;

public class ApplicationException extends RuntimeException
{
    public ApplicationException() {}
    public ApplicationException(String msg) { super(msg); }
    public ApplicationException(Throwable cause) { super(cause); }
}
