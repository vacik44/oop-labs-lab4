package oop.labs.lab4.math.model.matrix;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.*;

public abstract class NumMatrix implements MatrixNumeric
{
    @JsonProperty("elements") protected final List<List<BigDecimal>> elements;


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


    protected List<List<BigDecimal>> buildAngularMinorMatrix(int power)
    {
        var source = new ArrayList<List<BigDecimal>>(power);

        for (var originRow: elements.subList(0, power))
        {
            var srcRow = new ArrayList<BigDecimal>(power);
            srcRow.addAll(originRow.subList(0, power));
            source.add(srcRow);
        }

        return source;
    }
    protected List<List<BigDecimal>> buildRoundedMatrix(MathContext mc)
    {
        return elements.stream().map(row -> row.stream().map(val -> val.round(mc)).toList()).toList();
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


    @Override public int[] size() { return new int[] {rows(), cols()};}

    @Override public int rows() { return elements.size(); }
    @Override public int cols() { return rows() == 0 ? 0 : elements.get(0).size(); }


    @Override public BigDecimal get(int row, int col) { return elements.get(row - 1).get(col - 1); }


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
