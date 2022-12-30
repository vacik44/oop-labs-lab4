package oop.labs.lab4.math.model.anynomials;

import oop.labs.lab4.math.model.MathObject;
import oop.labs.lab4.math.model.simplets.VariableDefinition;

@SuppressWarnings("unused")
public interface Mononomial extends Anynomial
{
    Integer power();

    Integer powerOfContained(VariableDefinition variable);
    Integer powerOf(VariableDefinition variable);

    default boolean equivalent(MathObject o)
    {
        if (this == o) return true;
        if (!(o instanceof Mononomial other)) return false;

        if (this.variablesCount() != other.variablesCount()) return false;
        for (var variable : variables()) if (!other.powerOf(variable).equals(powerOf(variable))) return false;
        return true;
    }
}
