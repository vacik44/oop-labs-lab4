package oop.labs.lab4.service.math.model;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

public final class NumMatrixImmutable extends NumMatrix
{
    private static List<List<BigDecimal>> makeImmutable(List<List<BigDecimal>> matrix)
    {
        matrix.replaceAll(Collections::unmodifiableList);
        return Collections.unmodifiableList(matrix);
    }


    private NumMatrixImmutable(boolean ignored, List<List<BigDecimal>> elements)
    {
        super(makeImmutable(elements));
    }


    public NumMatrixImmutable()
    {
        super(makeImmutable(buildMatrix()));
    }

    public NumMatrixImmutable(int rows, int cols, BigDecimal init)
    {
        super(makeImmutable(buildMatrix(rows, cols, init)));
    }

    public NumMatrixImmutable(BigDecimal[][] matrix)
    {
        super(makeImmutable(buildMatrix(matrix)));
    }

    public <TRow extends Iterable<BigDecimal>> NumMatrixImmutable(Iterable<TRow> source)
    {
        super(makeImmutable(buildMatrix(source)));
    }

    public NumMatrixImmutable(NumericMatrix other)
    {
        super(makeImmutable(buildMatrix(other)));
    }


    public static NumMatrixImmutable eye(int size)
    {
        return new NumMatrixImmutable(false, buildIdentityMatrix(size));
    }

    public static NumMatrixImmutable immutable(NumMatrixMutable matrix)
    {
        return new NumMatrixImmutable(false, matrix.elements);
    }


    @Override
    public NumericMatrix dot(NumericMatrix other)
    {
        return new NumMatrixImmutable(false, buildDotSource(other));
    }


    @Override public Matrix<BigDecimal> set(int row, int col, BigDecimal value) { throw new UnsupportedOperationException(); }

    @Override public Matrix<BigDecimal> fillRow(int index, Iterable<BigDecimal> source) { throw new UnsupportedOperationException(); }
    @Override public Matrix<BigDecimal> fillCol(int index, Iterable<BigDecimal> source) { throw new UnsupportedOperationException(); }

    @Override public Matrix<BigDecimal> fillRow(int index, BigDecimal[] source) { throw new UnsupportedOperationException(); }
    @Override public Matrix<BigDecimal> fillCol(int index, BigDecimal[] source) { throw new UnsupportedOperationException(); }

    @Override public Matrix<BigDecimal> fill(BigDecimal[][] source) { throw new UnsupportedOperationException(); }
    @Override public <TRow extends Iterable<BigDecimal>> Matrix<BigDecimal> fill(Iterable<TRow> source) { throw new UnsupportedOperationException(); }
}
