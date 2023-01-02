package oop.labs.lab4.math.model.matrix;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

@SuppressWarnings("unused")
public interface MatrixNumeric extends Matrix<BigDecimal>
{
    void round(MathContext mc);
    default void round(int precision, RoundingMode mode) { round(new MathContext(precision, mode)); }

    MatrixNumeric rounded(MathContext mc);
    default MatrixNumeric rounded(int precision, RoundingMode mode) { return rounded(new MathContext(precision, mode)); }


    default BigDecimal[] diag()
    {
        var diagonal = new BigDecimal[rows()];
        for (var i = 1; i <= rows(); i++) diagonal[i - 1] = get(i, i);

        return diagonal;
    }
}
