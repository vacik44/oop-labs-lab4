package oop.labs.lab4.service.math.model.anynomials;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import oop.labs.lab4.service.math.model.matrix.MatrixNumeric;
import oop.labs.lab4.service.math.model.matrix.NumMatrixImmutable;

import java.math.BigDecimal;

@JsonRootName("polynomial")
public final class QPolynomialImmuatble extends QPolynomial
{
    public QPolynomialImmuatble(MatrixNumeric odds)
    {
        this(new NumMatrixImmutable(odds));
    }

    public QPolynomialImmuatble(NumMatrixImmutable odds)
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
