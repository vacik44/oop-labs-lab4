package oop.labs.lab4.math.model.matrix;

import oop.labs.lab4.math.model.MathObject;

import java.util.Arrays;

public interface Matrix<TElement> extends MathObject
{
    int[] size();

    int rows();
    int cols();

    default boolean isSquare() { return rows() == cols(); }


    Matrix<TElement> angularMinorMatrix(int power);


    TElement get(int row, int col);
    Matrix<TElement> set(int row, int col, TElement value);

    Iterable<TElement> row(int index);
    Iterable<TElement> col(int index);

    Matrix<TElement> fillRow(int index, Iterable<TElement> source);
    Matrix<TElement> fillCol(int index, Iterable<TElement> source);

    Matrix<TElement> fillRow(int index, TElement[] source);
    Matrix<TElement> fillCol(int index, TElement[] source);

    Matrix<TElement> fill(TElement[][] source);
    <TRow extends Iterable<TElement>> Matrix<TElement> fill(Iterable<TRow> source);


    default boolean equivalent(MathObject o)
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
}
