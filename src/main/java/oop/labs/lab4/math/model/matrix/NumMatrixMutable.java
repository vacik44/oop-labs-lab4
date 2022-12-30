package oop.labs.lab4.math.model.matrix;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import java.math.BigDecimal;
import java.util.List;

@JsonRootName("numMatrix")
@JsonIncludeProperties("elements")
@SuppressWarnings("unused")
public class NumMatrixMutable extends NumMatrix
{
    @Override public boolean isImmutable() { return false; }


    public NumMatrixMutable()
    {
        super(buildMatrix());
    }


    @JsonCreator
    protected static NumMatrixMutable instance(@JsonProperty("elements") List<List<BigDecimal>> elements)
    {
        return new NumMatrixMutable(elements, false);
    }

    protected NumMatrixMutable(List<List<BigDecimal>> elements, boolean ignored)
    {
        super(elements);
    }


    public NumMatrixMutable(int rows, int cols, BigDecimal init) { this(buildMatrix(rows, cols, init), false); }
    public NumMatrixMutable(BigDecimal[][] matrix) { this(buildMatrix(matrix), false); }
    public <TRow extends Iterable<BigDecimal>> NumMatrixMutable(Iterable<TRow> source) { this(buildMatrix(source), false); }
    public NumMatrixMutable(MatrixNumeric other) { this(buildMatrix(other), false); }


    @Override public NumMatrixMutable reduced(int dropRow, int dropCol) { return new NumMatrixMutable(buildReducedMatrix(this, dropCol, dropRow), false); }
    public static NumMatrixMutable eye(int size) { return NumMatrixMutable.instance(buildIdentityMatrix(size)); }


    @Override
    public Matrix<BigDecimal> set(int row, int col, BigDecimal value)
    {
        elements.get(row - 1).set(col - 1, value);
        return this;
    }


    @Override
    public NumMatrixMutable fillRow(int index, Iterable<BigDecimal> source)
    {
        var row = elements.get(index - 1);

        var i = 0;
        for (var element: source)
        {
            row.set(i, element);
            i++;
        }

        return this;
    }

    @Override
    public NumMatrixMutable fillCol(int index, Iterable<BigDecimal> source)
    {
        var i = 0;
        var c = index - 1;

        for (var element: source)
        {
            elements.get(i).set(c, element);
            i++;
        }

        return this;
    }


    @Override
    public NumMatrixMutable fillRow(int index, BigDecimal[] source)
    {
        var row = elements.get(index - 1);

        var i = 0;
        for (var element: source)
        {
            row.set(i, element);
            i++;
        }

        return this;
    }

    @Override
    public NumMatrixMutable fillCol(int index, BigDecimal[] source)
    {
        var i = 0;
        var c = index - 1;

        for (var element: source)
        {
            elements.get(i).set(c, element);
            i++;
        }

        return this;
    }


    @Override
    @SuppressWarnings("DuplicatedCode")
    public NumMatrixMutable fill(BigDecimal[][] source)
    {
        var i = 0;

        for (var srcRow : source)
        {
            var row = elements.get(i);
            var j = 0;

            for (var element : srcRow)
            {
                row.set(j, element);
                j++;
            }

            i++;
        }

        return this;
    }

    @Override
    @SuppressWarnings("DuplicatedCode")
    public <TRow extends Iterable<BigDecimal>> NumMatrixMutable fill(Iterable<TRow> source)
    {
        var i = 0;

        for (var srcRow : source)
        {
            var row = elements.get(i);
            var j = 0;

            for (var element : srcRow)
            {
                row.set(j, element);
                j++;
            }

            i++;
        }

        return this;
    }
}