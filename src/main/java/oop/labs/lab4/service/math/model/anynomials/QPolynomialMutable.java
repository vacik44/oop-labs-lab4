package oop.labs.lab4.service.math.model.anynomials;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import oop.labs.lab4.service.math.model.matrix.MatrixNumeric;
import oop.labs.lab4.service.math.model.matrix.NumMatrixMutable;

import java.math.BigDecimal;

/*public class QPolynomialMutable extends QPolynomial
{
    public QPolynomialMutable(MatrixNumeric odds)
    {
        this(new NumMatrixMutable(odds));
    }

    public QPolynomialMutable(NumMatrixMutable odds)
    {
        super(odds);
    }

    @JsonCreator
    public static QPolynomialMutable of(@JsonProperty("expression") String expression)
    {
        return new QPolynomialMutable(new NumMatrixMutable(parseOdds(expression)));
    }


    @Override public QPolynomialMutable setOdd(BigDecimal value, int... position)
    {
        if (position.length == 2)
        {
            odds.set(position[0], position[1], value);
            return this;
        }
        else throw new IllegalArgumentException("Incorrect number of coordinates for quadratic polynomial");
    }
}*/
