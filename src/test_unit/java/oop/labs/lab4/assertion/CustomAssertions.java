package oop.labs.lab4.assertion;

import oop.labs.lab4.assertion.asserts.AnynomialAssert;
import oop.labs.lab4.assertion.asserts.MathObjectAssert;
import oop.labs.lab4.assertion.asserts.MatrixAssert;
import oop.labs.lab4.math.model.MathObject;
import oop.labs.lab4.math.model.anynomials.Anynomial;
import oop.labs.lab4.math.model.matrix.Matrix;
import java.math.BigDecimal;

public class CustomAssertions
{
    public static <TMatrix extends Matrix<TElement>, TElement> MatrixAssert<TMatrix, TElement> assertThat(TMatrix actual, Class<TElement> type)
    {
        return new MatrixAssert<>(actual, type);
    }

    public static <TMatrix extends Matrix<BigDecimal>> MatrixAssert<TMatrix, BigDecimal> assertThat(TMatrix actual)
    {
        return assertThat(actual, BigDecimal.class);
    }


    public static <TAnynomial extends Anynomial> AnynomialAssert<TAnynomial> assertThat(TAnynomial actual)
    {
        return new AnynomialAssert<>(actual);
    }


    public static <TObject extends MathObject> MathObjectAssert<TObject> assertThat(TObject actual)
    {
        return new MathObjectAssert<>(actual);
    }
}
