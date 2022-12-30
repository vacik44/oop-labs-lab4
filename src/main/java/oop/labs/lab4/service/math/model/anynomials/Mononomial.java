package oop.labs.lab4.service.math.model.anynomials;

import oop.labs.lab4.service.math.model.MathExpressionable;
import oop.labs.lab4.service.math.model.MathObject;
import oop.labs.lab4.service.math.model.simplets.VariableDefinition;

import java.math.BigDecimal;

public interface Mononomial extends MathObject, MathExpressionable
{
    BigDecimal getOdd();

    boolean contains(VariableDefinition variable);

    int powerOfContained(VariableDefinition variable);
    int powerOf(VariableDefinition variable);
}
