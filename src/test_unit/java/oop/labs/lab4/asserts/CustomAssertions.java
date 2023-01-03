package oop.labs.lab4.asserts;

import oop.labs.lab4.math.model.matrix.Matrix;

import java.math.BigDecimal;

public class CustomAssertions
{
    public static <TElement> MatrixAssert<TElement> assertThat(Matrix<TElement> actual, Class<TElement> type)
    {
        return new MatrixAssert<TElement>(actual, type);
    }

    public static MatrixAssert<BigDecimal> assertThat(Matrix<BigDecimal> actual)
    {
        return assertThat(actual, BigDecimal.class);
    }
}
