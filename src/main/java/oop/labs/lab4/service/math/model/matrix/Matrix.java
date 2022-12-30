package oop.labs.lab4.service.math.model.matrix;

import oop.labs.lab4.service.math.model.MathObject;

public interface Matrix<TElement> extends MathObject
{
    int[] size();

    int rows();
    int cols();

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
