package oop.labs.lab4.service.exceptions;

public class ServiceException extends Exception
{
    public ServiceException(String msg) { super(msg); }
    public ServiceException(Throwable cause) { super(cause); }
}
