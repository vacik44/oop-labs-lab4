package oop.labs.lab4.service.mapping;

import oop.labs.lab4.service.ServiceException;

public class MappingException extends ServiceException
{
    public MappingException(String msg) { super(msg); }
    public MappingException(Throwable cause) { super(cause); }
}
