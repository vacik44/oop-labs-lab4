package oop.labs.lab4.service.math.model.anynomials;

import oop.labs.lab4.service.math.model.MathExpressionable;
import oop.labs.lab4.service.math.model.MathObject;
import oop.labs.lab4.service.math.model.simplets.VariableDefinition;

import java.math.BigDecimal;

public interface Mononomial extends Anynomial
{
    Integer getPowerOfContained(VariableDefinition variable);
    Integer getPowerOf(VariableDefinition variable);

    default boolean equivalent(MathObject o)
    {
        if (this == o) return true;
        if (!(o instanceof Mononomial other)) return false;

        if (this.getVariablesCount() != other.getVariablesCount()) return false;
        for (var variable : getVariablesSet()) if (!other.getPowerOf(variable).equals(getPowerOf(variable))) return false;
        return true;
    }
}
