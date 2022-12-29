package oop.labs.lab4.service.math.exceptions;

import oop.labs.lab4.service.exceptions.ServiceException;

public class MathModelException extends ServiceException
{
    public MathModelException(String msg) { super(msg); }
    public MathModelException(Throwable cause) { super(cause); }
}
