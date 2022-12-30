package oop.labs.lab4.service.math.model.anynomials;

import oop.labs.lab4.service.math.model.MathObject;

import java.math.BigDecimal;

public interface MononomialOdded extends Mononomial
{
    BigDecimal getOdd();
    Mononomial getMononomial();

    default boolean equivalent(MathObject o)
    {
        if (this == o) return true;
        if (!(o instanceof Mononomial base)) return false;
        if (!(base instanceof MononomialOdded other)) { if (!getOdd().equals(BigDecimal.ONE)) return getMononomial().equivalent(base); }
        else if (!other.getOdd().equals(getOdd())) return getMononomial().equivalent(other);
        return false;
    }
}
