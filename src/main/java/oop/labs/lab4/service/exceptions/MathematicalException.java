package oop.labs.lab4.service.exceptions;

public class MathematicalException extends ServiceException
{
    public MathematicalException(String msg) { super(msg); }
    public MathematicalException(Throwable cause) { super(cause); }
}
