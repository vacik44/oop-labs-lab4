package oop.labs.lab4.data;

import oop.labs.lab4.ApplicationException;

@SuppressWarnings("unused")
public class DataAccessException extends ApplicationException
{
    public DataAccessException() {}
    public DataAccessException(String msg) { super(msg); }
    public DataAccessException(Throwable cause) { super(cause); }
}
