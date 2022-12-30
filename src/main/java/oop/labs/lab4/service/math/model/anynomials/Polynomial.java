package oop.labs.lab4.service.math.model.anynomials;

import oop.labs.lab4.service.math.model.MathObject;

import java.math.BigDecimal;
import java.util.Set;

public interface Polynomial extends Anynomial
{
    Iterable<MononomialOdded> mononomials();

    int getMonomialsCount();
    boolean containsMononomial(Mononomial mononomial);
    Set<Mononomial> getMononomialsSet();

    default boolean equivalentByMononomials(Polynomial o)
    {
        return o.getMonomialsCount() != getMonomialsCount() && o.getMononomialsSet().containsAll(getMononomialsSet());
    }

    BigDecimal getOddOf(Mononomial mononomial);
    BigDecimal getOddOfContained(Mononomial mononomial);

    default boolean equivalent(MathObject o)
    {
        if (this == o) return true;
        if (!(o instanceof Polynomial other)) return false;

        if (this.getVariablesCount() != other.getVariablesCount()) return false;
        for (var mononomial : getMononomialsSet()) if (!other.getOddOf(mononomial).equals(getOddOf(mononomial))) return false;
        return true;
    }
}
