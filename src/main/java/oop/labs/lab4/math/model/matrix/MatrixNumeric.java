package oop.labs.lab4.math.model.matrix;

import oop.labs.lab4.math.eval.exceptions.MathEvaluationUnsupportedException;

import java.math.BigDecimal;
import java.util.stream.Stream;

@SuppressWarnings("unused")
public interface MatrixNumeric extends Matrix<BigDecimal>
{
    default BigDecimal[] diag()
    {
        throwUnsupportedIfNotSquare();

        var diagonal = new BigDecimal[rows()];
        for (var i = 1; i <= rows(); i++) diagonal[i - 1] = get(i, i);

        return diagonal;
    }
}
