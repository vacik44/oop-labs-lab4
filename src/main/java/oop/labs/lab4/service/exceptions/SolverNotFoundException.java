package oop.labs.lab4.service.exceptions;

public class SolverNotFoundException extends ApplicationClassifiedException
{
    public SolverNotFoundException() {}
    public SolverNotFoundException(String msg) { super(msg); }
    public SolverNotFoundException(Throwable cause) { super(cause); }

    public static SolverNotFoundException forId(String solverId)
    {
        return new SolverNotFoundException(String.format("Solver '%s' does not exist.", solverId));
    }
}
