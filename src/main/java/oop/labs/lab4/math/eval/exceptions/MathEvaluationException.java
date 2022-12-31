package oop.labs.lab4.math.eval.exceptions;

import oop.labs.lab4.math.MathematicalException;

public class MathEvaluationException extends MathematicalException
{
    public MathEvaluationException(String msg) { super(msg); }
    public MathEvaluationException(Throwable cause) { super(cause); }
}
