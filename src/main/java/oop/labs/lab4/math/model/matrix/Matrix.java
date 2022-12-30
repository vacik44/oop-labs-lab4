package oop.labs.lab4.math.model.matrix;

import oop.labs.lab4.math.model.MathObject;

@SuppressWarnings("unused")
public interface Matrix<TElement> extends MathObject
{
    int[] size();

    int rows();
    int cols();

    Matrix<TElement> reduced(int dropRow, int dropCol);

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
}
