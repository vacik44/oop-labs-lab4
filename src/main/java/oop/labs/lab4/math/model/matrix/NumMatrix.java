package oop.labs.lab4.math.model.matrix;

import com.fasterxml.jackson.annotation.JsonProperty;
import oop.labs.lab4.math.model.MathObject;

import java.math.BigDecimal;
import java.util.*;

@SuppressWarnings("unused")
public abstract class NumMatrix implements MatrixNumeric
{
    @JsonProperty("elements") protected final List<List<BigDecimal>> elements;


    @Override
    public boolean equivalent(MathObject o)
    {
        if (this == o) return true;
        if (!(o instanceof Matrix<?> other)) return false;

        if (!Arrays.equals(size(), other.size())) return false;
        for (var i = 1; i <= rows(); i++)
            for (var j = 1; j <= cols(); j++)
                if (!other.get(i, j).equals(get(i, j)))
                    return false;

        return true;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        NumMatrix numMatrix = (NumMatrix) o;

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


    protected static List<List<BigDecimal>> buildReducedMatrix(MatrixNumeric origin, int dropRow, int dropCol)
    {
        var source = new ArrayList<List<BigDecimal>>(origin.rows() - 1);

        for (var i = 1; i <= origin.rows(); i++)
        {
            var row = new ArrayList<BigDecimal>(origin.cols() - 1);

            if (i != dropRow)
                for (var j = 1; j <= origin.cols(); j++)
                    if (j != dropCol)
                        row.add(origin.get(i, j));

            source.add(row);
        }

        return source;
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

    protected static List<List<BigDecimal>> buildMatrix(MatrixNumeric other)
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


    @Override public String toString() { return elements.toString(); }
}
