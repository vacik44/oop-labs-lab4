package oop.labs.lab4.service.math.model;

import java.math.BigDecimal;
import java.util.*;

public abstract class NumMatrix implements NumericMatrix
{
    protected final List<List<BigDecimal>> elements;


    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (!(o instanceof NumMatrix numMatrix)) return false;
        return elements.equals(numMatrix.elements);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(elements);
    }


    protected NumMatrix(List<List<BigDecimal>> elements)
    {
        this.elements = elements;
    }


    protected static List<List<BigDecimal>> buildIdentityMatrix(int size)
    {
        var source = new ArrayList<List<BigDecimal>>(size);

        for (var i = 0; i < size; i++)
        {
            var row = new ArrayList<BigDecimal>(size);
            for (var j = 0; j < size; j++)
                row.add(BigDecimal.ZERO);

            row.set(i, BigDecimal.ONE);

            source.add(row);
        }

        return source;
    }


    protected static List<List<BigDecimal>> buildMatrix()
    {
        return new ArrayList<>(0);
    }

    protected static List<List<BigDecimal>> buildMatrix(int rows, int cols, BigDecimal init)
    {
        var matrix = new ArrayList<List<BigDecimal>>(rows);

        for (var i = 0; i < rows; i++)
        {
            var row = new ArrayList<BigDecimal>(cols);

            for (var j = 0; j < cols; j++)
                row.add(init);

            matrix.add(row);
        }

        return matrix;
    }

    protected static List<List<BigDecimal>> buildMatrix(BigDecimal[][] source)
    {
        var matrix = new ArrayList<List<BigDecimal>>(source.length);

        for (var srcRow : source)
        {
            var row = new ArrayList<BigDecimal>(srcRow.length);
            row.addAll(Arrays.asList(srcRow));
            matrix.add(row);
        }

        return matrix;
    }

    protected static <TRow extends Iterable<BigDecimal>> List<List<BigDecimal>> buildMatrix(Iterable<TRow> source)
    {
        var matrix = new ArrayList<List<BigDecimal>>();

        for (var srcRow : source)
        {
            var row = new ArrayList<BigDecimal>();
            for (var element : srcRow) row.add(element);
            matrix.add(row);
        }

        return matrix;
    }

    protected static List<List<BigDecimal>> buildMatrix(NumericMatrix other)
    {
        var matrix = new ArrayList<List<BigDecimal>>(other.rows());

        for (var i = 1; i <= other.rows(); i++)
        {
            var content = new ArrayList<BigDecimal>(other.cols());

            for (var element : other.row(i))
                content.add(element);

            matrix.add(content);
        }

        return matrix;
    }


    @Override
    public int[] size()
    {
        return new int[] {rows(), cols()};
    }

    @Override
    public int rows()
    {
        return elements.size();
    }

    @Override
    public int cols()
    {
        return rows() == 0 ? 0 : elements.get(0).size();
    }


    @Override
    public BigDecimal get(int row, int col)
    {
        return elements.get(row - 1).get(col - 1);
    }


    @Override
    public Iterable<BigDecimal> row(int index)
    {
        return Collections.unmodifiableList(elements.get(index - 1));
    }

    @Override
    public Iterable<BigDecimal> col(int index)
    {
        var column = new ArrayList<BigDecimal>(rows());
        var c = index - 1;

        for (var row : elements)
            column.add(row.get(c));

        return Collections.unmodifiableList(column);
    }


    protected List<List<BigDecimal>> buildDotSource(NumericMatrix other)
    {
        if (this.cols() != other.rows()) throw new IllegalArgumentException("Operands sizes mismatch. Unable to multiply");

        var resultSource = new ArrayList<List<BigDecimal>>(this.rows());

        for (var i = 1; i <= this.rows(); i++)
        {
            var row = new ArrayList<BigDecimal>(other.cols());

            for (var j = 1; j <= other.cols(); j++)
            {
                var sum = BigDecimal.ZERO;
                for (var k = 1; k <= this.cols(); k++) sum = sum.add(this.get(i, k).multiply(other.get(k, j)));
                row.add(sum);
            }

            resultSource.add(row);
        }

        return resultSource;
    }
}
