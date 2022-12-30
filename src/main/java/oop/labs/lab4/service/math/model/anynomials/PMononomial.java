package oop.labs.lab4.service.math.model.anynomials;

import oop.labs.lab4.service.math.model.simplets.VariableDefinition;

import java.math.BigDecimal;
import java.util.Map;

public final class PMononomial
{
    private BigDecimal odd;
    private Map<VariableDefinition, Integer> variables;


    @Override
    public boolean equivalent(Object o)
    {
        if (this == o) return true;
        if (!(o instanceof Mononomial other)) return false;

        if (!odd.equals(other.odd)) return false;
        return variables.equals(other.variables);
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PMononomial that = (PMononomial) o;

        if (!odd.equals(that.odd)) return false;
        return variables.equals(that.variables);
    }

    @Override
    public int hashCode()
    {
        int result = odd.hashCode();
        result = 31 * result + variables.hashCode();
        return result;
    }
}
