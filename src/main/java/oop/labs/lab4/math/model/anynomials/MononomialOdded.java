package oop.labs.lab4.math.model.anynomials;

import oop.labs.lab4.math.model.MathObject;

import java.math.BigDecimal;

public interface MononomialOdded extends Mononomial
{
    BigDecimal odd();
    Mononomial mononomial();

    default boolean equivalent(MathObject o)
    {
        if (this == o) return true;
        if (!(o instanceof Mononomial base)) return false;
        if (!(base instanceof MononomialOdded other)) { if (!odd().equals(BigDecimal.ONE)) return mononomial().equivalent(base); }
        else if (!other.odd().equals(odd())) return mononomial().equivalent(other);
        return false;
    }
}
