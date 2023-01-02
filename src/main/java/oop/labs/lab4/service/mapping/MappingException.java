package oop.labs.lab4.service.mapping;

import oop.labs.lab4.ApplicationException;

public class MappingException extends ApplicationException
{
    public MappingException() {}
    public MappingException(String msg) { super(msg); }
    public MappingException(Throwable cause) { super(cause); }
}
