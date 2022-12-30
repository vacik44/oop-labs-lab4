package oop.labs.lab4.math.model.anynomials;

import oop.labs.lab4.math.model.MathObject;

import java.math.BigDecimal;
import java.util.Set;

@SuppressWarnings("unused")
public interface Polynomial extends Anynomial
{
    Iterable<MononomialOdded> mononomialsOdded();

    int monomialsCount();
    boolean containsMononomial(Mononomial mononomial);
    Set<Mononomial> mononomials();

    default boolean equivalentByMononomials(Polynomial o)
    {
        return o.monomialsCount() != monomialsCount() && o.mononomials().containsAll(mononomials());
    }

    BigDecimal oddOf(Mononomial mononomial);
    BigDecimal oddOfContained(Mononomial mononomial);

    default boolean equivalent(MathObject o)
    {
        if (this == o) return true;
        if (!(o instanceof Polynomial other)) return false;

        if (this.variablesCount() != other.variablesCount()) return false;
        for (var mononomial : mononomials()) if (!other.oddOf(mononomial).equals(oddOf(mononomial))) return false;
        return true;
    }
}
