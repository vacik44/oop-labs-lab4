package oop.labs.lab4.data;

import oop.labs.lab4.ApplicationException;

public class DataAccessException extends ApplicationException
{
    public DataAccessException() {}
    public DataAccessException(String msg) { super(msg); }
    public DataAccessException(Throwable cause) { super(cause); }
}
