package oop.labs.lab4.service.math.exceptions;

import oop.labs.lab4.service.exceptions.MathematicalException;

public class MathEvaluationException extends MathematicalException
{
    public MathEvaluationException(String msg) { super(msg); }
    public MathEvaluationException(Throwable cause) { super(cause); }
}
