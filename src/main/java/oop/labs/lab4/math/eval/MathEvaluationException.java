package oop.labs.lab4.math.eval;

import oop.labs.lab4.math.MathematicalException;

@SuppressWarnings("unused")
public class MathEvaluationException extends MathematicalException
{
    public MathEvaluationException() {}
    public MathEvaluationException(String msg) { super(msg); }
    public MathEvaluationException(Throwable cause) { super(cause); }
}
