package oop.labs.lab4.service.exceptions;

@SuppressWarnings("unused")
public class EvaluationFailedException extends ApplicationClassifiedException
{
    public EvaluationFailedException() {}
    public EvaluationFailedException(String msg) { super(msg); }
    public EvaluationFailedException(Throwable cause) { super(cause); }
}
