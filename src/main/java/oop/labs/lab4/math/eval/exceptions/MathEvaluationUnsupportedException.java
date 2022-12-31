package oop.labs.lab4.math.eval.exceptions;

public class MathEvaluationUnsupportedException extends MathEvaluationException
{
    public MathEvaluationUnsupportedException(String msg) { super(msg); }
    public MathEvaluationUnsupportedException(Throwable cause) { super(cause); }
}
