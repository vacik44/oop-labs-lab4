package oop.labs.lab4.service.math.model;

import java.math.BigDecimal;

public interface Polynomial
{
    String getExpression();


    int getVariablesCount();


    BigDecimal getOdd(int... position);
    Polynomial setOdd(BigDecimal value, int... position);
}
