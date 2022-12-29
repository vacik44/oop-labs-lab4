package oop.labs.lab4.service.math.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import java.math.BigDecimal;

@JsonRootName("polynomial")
public final class QPolynomialImmuatble extends QPolynomial
{
    @JsonCreator
    private QPolynomialImmuatble(@JsonProperty("odds") MatrixNumeric odds)
    {
        super(odds);
    }

    @JsonCreator
    public static QPolynomialImmuatble of(@JsonProperty("expression") String expression)
    {
        return new QPolynomialImmuatble(new NumMatrixImmutable(parseOdds(expression)));
    }


    @Override public Polynomial setOdd(BigDecimal value, int... position) { throw new UnsupportedOperationException(); }
}
