package oop.labs.lab4.math.model.anynomials;

import oop.labs.lab4.math.model.MathExpressionable;
import oop.labs.lab4.math.model.MathObject;
import oop.labs.lab4.math.model.simplets.VariableDefinition;

import java.util.Set;

@SuppressWarnings("unused")
public interface Anynomial extends MathObject, MathExpressionable
{
    Integer power();

    int variablesCount();
    boolean containsVariable(VariableDefinition variable);
    Set<VariableDefinition> variables();

    default boolean equivalentByVariables(Anynomial o)
    {
        return (o.variablesCount() == variablesCount() && o.variables().containsAll(variables()));
    }
}
