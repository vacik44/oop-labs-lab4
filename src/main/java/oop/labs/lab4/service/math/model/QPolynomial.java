package oop.labs.lab4.service.math.model;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public abstract class QPolynomial implements PolynomialQuadratic
{
    @JsonProperty("odds")
    protected final MatrixNumeric odds;


    protected QPolynomial(MatrixNumeric odds)
    {
        this.odds = odds;
    }


    @Override public int getVariablesCount() { return odds.rows(); }
    @Override public MatrixNumeric getOdds() { return odds; }

    @Override public BigDecimal getOdd(int... position)
    {
        if (position.length == 2) return odds.get(position[0], position[1]);
        throw new IllegalArgumentException("Incorrect number of coordinates for quadratic polynomial");
    }


    protected static BigDecimal[][] parseOdds(String expression)
    {
        return null;
    }


    @Override
    @JsonGetter("expression")
    public String getExpression()
    {
        var builder = new StringBuilder();

        for (var i = 1; i <= odds.rows(); i++)
            for (var j = 1; j <= odds.cols(); j++)
            {
                var odd = odds.get(i, j);
                builder.append(odd.signum() < 0 || builder.isEmpty() ? odd.toString() : "+".concat(odd.toString()));
                builder.append('x').append(i).append(i == j ? "^2" : String.format("x%d", j));
            }

        return builder.toString();
    }

    @Override public String toString() { return getExpression(); }
}
