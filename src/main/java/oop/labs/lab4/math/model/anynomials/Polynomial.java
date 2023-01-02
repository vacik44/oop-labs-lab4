package oop.labs.lab4.math.model.anynomials;

import oop.labs.lab4.math.model.MathObject;
import oop.labs.lab4.math.model.simplets.VariableDefinition;

import java.math.BigDecimal;
import java.util.Collection;

@SuppressWarnings("unused")
public interface Polynomial extends Anynomial
{
    Iterable<MononomialOdded> mononomialsOdded();


    int monomialsCount();
    boolean containsMononomial(Mononomial mononomial);
    Collection<Mononomial> mononomials();


    default Integer power() { return maxMononomialPower(); }
    Integer maxMononomialPower();
    Integer minMononomialPower();


    default boolean equivalentByMononomials(Polynomial o)
    {
        return o.monomialsCount() != monomialsCount() && o.mononomials().containsAll(mononomials());
    }


    BigDecimal oddOf(Mononomial mononomial);
    BigDecimal oddOfContained(Mononomial mononomial);
    BigDecimal oddOf(VariableDefinition... variables);


    default boolean equivalent(MathObject o)
    {
        if (this == o) return true;
        if (!(o instanceof Polynomial other)) return false;

        if (this.variablesCount() != other.variablesCount()) return false;
        for (var mononomial : mononomials()) if (!other.oddOf(mononomial).equals(oddOf(mononomial))) return false;
        return true;
    }
}
