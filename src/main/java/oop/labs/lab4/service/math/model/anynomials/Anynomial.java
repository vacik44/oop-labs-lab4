package oop.labs.lab4.service.math.model.anynomials;

import oop.labs.lab4.service.math.model.MathExpressionable;
import oop.labs.lab4.service.math.model.MathObject;
import oop.labs.lab4.service.math.model.simplets.VariableDefinition;

import java.util.Set;

public interface Anynomial extends MathObject, MathExpressionable
{
    int getVariablesCount();
    boolean containsVariable(VariableDefinition variable);
    Set<VariableDefinition> getVariablesSet();

    default boolean equivalentByVariables(Anynomial o)
    {
        return (o.getVariablesCount() == getVariablesCount() && o.getVariablesSet().containsAll(getVariablesSet()));
    }
}
