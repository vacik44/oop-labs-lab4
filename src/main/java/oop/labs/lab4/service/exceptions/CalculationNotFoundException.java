package oop.labs.lab4.service.exceptions;

import java.math.BigInteger;

public class CalculationNotFoundException extends ApplicationClassifiedException
{
    public CalculationNotFoundException() {}
    public CalculationNotFoundException(String msg) { super(msg); }
    public CalculationNotFoundException(Throwable cause) { super(cause); }

    public static CalculationNotFoundException forId(BigInteger calculationId)
    {
        return new CalculationNotFoundException(String.format("Calculation with id %s not found in server repository.", calculationId));
    }
}
