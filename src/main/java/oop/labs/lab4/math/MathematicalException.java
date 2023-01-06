package oop.labs.lab4.math;

@SuppressWarnings("unused")
public class MathematicalException extends RuntimeException
{
    public MathematicalException() {}
    public MathematicalException(String msg) { super(msg); }
    public MathematicalException(Throwable cause) { super(cause); }
}
