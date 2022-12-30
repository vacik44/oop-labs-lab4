package oop.labs.lab4.service;

import oop.labs.lab4.ApplicationException;

public class ServiceException extends ApplicationException
{
    public ServiceException(String msg) { super(msg); }
    public ServiceException(Throwable cause) { super(cause); }
}
