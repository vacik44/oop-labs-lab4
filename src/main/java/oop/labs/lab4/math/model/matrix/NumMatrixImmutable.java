package oop.labs.lab4.math.model.matrix;

import com.fasterxml.jackson.annotation.*;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.Collections;
import java.util.List;

@JsonTypeName("numMatrix")
@JsonIncludeProperties("elements")
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "@type")
@SuppressWarnings("unused")
public final class NumMatrixImmutable extends NumMatrix
{
    @Override public boolean isImmutable() { return true; }


    private static List<List<BigDecimal>> makeImmutable(List<List<BigDecimal>> matrix)
    {
        matrix.replaceAll(Collections::unmodifiableList);
        return Collections.unmodifiableList(matrix);
    }


    @JsonCreator
    private static NumMatrixImmutable instance(@JsonProperty("elements") List<List<BigDecimal>> elements)
    {
        return new NumMatrixImmutable(elements, false);
    }

    private NumMatrixImmutable(List<List<BigDecimal>> elements, boolean ignored)
    {
        super(makeImmutable(elements));
    }


    public NumMatrixImmutable() { this(buildMatrix(), false); }
    public NumMatrixImmutable(int rows, int cols, BigDecimal init) { this(buildMatrix(rows, cols, init), false); }
    public NumMatrixImmutable(BigDecimal[][] matrix) { this(buildMatrix(matrix), false); }
    public <TRow extends Iterable<BigDecimal>> NumMatrixImmutable(Iterable<TRow> source) { this(buildMatrix(source), false); }
    public NumMatrixImmutable(MatrixNumeric other) { this(buildMatrix(other), false); }


    public static NumMatrixImmutable immutable(NumMatrixMutable matrix) { return NumMatrixImmutable.instance(matrix.elements); }
    public static NumMatrixImmutable eye(int size) { return NumMatrixImmutable.instance(buildIdentityMatrix(size)); }

    @Override public NumMatrixImmutable angularMinorMatrix(int power) { return NumMatrixImmutable.instance(buildAngularMinorMatrix(power)); }
    @Override public NumMatrixImmutable rounded(MathContext mc) { return NumMatrixImmutable.instance(buildRoundedMatrix(mc)); }


    @Override public void round(MathContext mc) { throw new UnsupportedOperationException(); }

    @Override public Matrix<BigDecimal> set(int row, int col, BigDecimal value) { throw new UnsupportedOperationException(); }

    @Override public Matrix<BigDecimal> fillRow(int index, Iterable<BigDecimal> source) { throw new UnsupportedOperationException(); }
    @Override public Matrix<BigDecimal> fillCol(int index, Iterable<BigDecimal> source) { throw new UnsupportedOperationException(); }

    @Override public Matrix<BigDecimal> fillRow(int index, BigDecimal[] source) { throw new UnsupportedOperationException(); }
    @Override public Matrix<BigDecimal> fillCol(int index, BigDecimal[] source) { throw new UnsupportedOperationException(); }

    @Override public Matrix<BigDecimal> fill(BigDecimal[][] source) { throw new UnsupportedOperationException(); }
    @Override public <TRow extends Iterable<BigDecimal>> Matrix<BigDecimal> fill(Iterable<TRow> source) { throw new UnsupportedOperationException(); }
}
